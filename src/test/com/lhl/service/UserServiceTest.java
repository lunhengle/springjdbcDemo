package com.lhl.service;

import com.lhl.AbstractBaseTest;
import com.lhl.entity.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lenovo on 2016/4/19.
 * 测测试针对spring 事物的处理
 * 1.默认情况下
 * ----只读属性 readonly = false意味着可以向数据库中插入数据或修改数据
 * ----事物传播属性 propagation 为 PROPAGATION.REQUIRED 如果存在一个事务，则支持当前事务，如果没有事务则开启一个新的事务
 * ----事务隔离级别 isolation 为 ISOLATION_DEFAULT 使用数据库的默认事务隔离级别
 * 2.关于事务传播属性
 * ----PROPAGATION_REQUIRED 如果存在一个事务，支持当前事务，如果没有事务则开启一个新的事务
 * ----PROPAGATION_SUPPORTS 如果存在一个事务，支持当前事务，如果没有事务则在非事务中执行
 * ----PROPAGATION_REQUIRES_NEW 总是开启一个事务 如果一个事务已经存在，则将存在的事务挂起
 * ----PROPAGATION_MANDATORY 如果存在一个事务，支持当前事务 如果没有一个活动的事务，则抛出异常 （必须在一个事务中运行。也就是说，只能被一个父事务调用。否则，就要抛出异常）
 * ----PROPAGATION_NOT_SUPPORTED 总是非事务执行，并挂起任何存在的事务
 * ----PROPAGATION_NEVER 总是非事务执行，如果存在事务则抛出异常
 * ----PROPAGATION_NESTED 如果一个活动的事务存在，则运行一个嵌套的事务中，如果没有事务，则按照PROPAGATION_REQUIRED 执行
 * 3.事务隔离级别
 * ----ISOLATION_DEFAULT 默认的隔离级别，使用数据库默认的事务隔离级别。另外四个与jdbc的隔离级别相对应
 * ----ISOLATION_READ_UNCOMMITTED 这是事务的最低的隔离级别，它允许另外一个事务可以看到这个事务中未提交的数据。这种隔离级别会产生脏读，不可重复读和幻读。
 * ----ISOLATION_READ_COMMITTED 保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据。这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻读。
 * ----ISOLATION_REPEATABLE_READ 这种事务隔离级别可以防止脏读，不可重复读。但是可能会出现幻读。它除了保证一个事务不能读取另外一个事务未提交的数据外，还保证避免下面情况产生(不可重复读)。
 * ----ISOLATION_SERIALIZABLE 这是花费代价最高但是可靠性最高的事务隔离级别。事务被处理为顺序执行。除了防止脏读，不可重复读，还避免了幻读。
 * 4.关于 脏读，不可重复读，幻读
 * ----脏读：指当一个事务正在访问数据，并且对数据进行了修改，而这种修改还没有被提交到数据库中，这时另外一个事务也访问这个数据，然后使用这个数据。
 * 因为这个数据是没有提交的数据，那么另外一个事务读到的这个数据是脏数据，依据脏数据所做的操作是可能不正确的。
 * ----不可重复读：指在一个事务内，多次读同一个数据。在这个事务还没有结束时，另外一个事务也访问该同一数据(修改同一数据)。
 * 那么，在第一个事务中的两次读取数据之间，由于第二个事务的修改，第一个事务两次读到的数据可能不一样。这样就发生了在一个事务内两次读到的数据是不一样的，因此称为不可重复读。
 * ----幻读：指当事务不是独立执行发生的一种现象，例如第一个事务对一个表中的数据进行了修改，这种修改涉及到表中的全部数据行。同时，第二个事务也修改这个表中的数据，这种修改是向表中插入一条新的数据。
 * 那么以后就会发生操作第一个事务的用户发现表中还有没有修改的数据行，这好像发生了幻觉一样。
 */

public class UserServiceTest extends AbstractBaseTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserService iUserService;

    /**
     * 目的：此测试例子 用来测试只读属性是否对级联调用的子方法 中是否也有作用.
     * 先决条件： 在 application_core.xml 中配置了全局事务 默认 时 readonly =true  ，add* modify* remove* 开头的除外。
     * 结论：如果 程序中配置 只读属性 那么只读属性只对直接调用的方法起作用，对于调用方法中 调用的其他方法不起作用。
     * 例如： 直接调用的方法是 modifyAndReadUser (符合 modify*) 子方法 odifyUsername(不符合 modify* 符合默认只读)  这样更新成功的。
     * 如果直接调用的方法是 odifyAndReadUser(不符合 modify*) 子方法 modifyUsername（符合 modify*） 这样更新不成功。
     * 此例中 第一组更新成功 第二组更新不成功。
     */
    @Test
    public void testReaOnly() {
        //更新成功
        iUserService.modifyUser("success", "88888", 3);
        //更新失败
        iUserService.odifyUser("error", "99999", 3);
    }

    /**
     * 目的：此测试例子 用来测试 在类中添加事务注解 判断是否能覆盖 系统中的只读属性.
     * 先决条件： 在 application_core.xml 中配置了全局事务 默认 时 readonly =true  ，add* modify* remove* 开头的除外。
     * 结论：如果在类中添加注解 将会覆盖 系统中关于事务的相关配置。
     * 例如：将不符合系统中配置更新条件的添加 事务注解 可以成功 更新数据 将符合系统中配置更新条件添加 只读属性(readonly=true) 不可以成功更新数据。
     * 此例中 第一组更新成功 第二组更新失败。
     */
    @Test
    public void testReadOnly1() {
        //更新成功
        iUserService.odifyUser1("success123", "777777", 3);
        //更新失败
        iUserService.modifyUser1("error123", "888888", 3);
    }

    /**
     * 目的：测试例子用来测试 整个系统中配置事务 类头配置事务 以及方法头配置事务 对多次更新数据时 中间出现异常情况 两次(或多次) 数据更新方法是否在一个事务中.
     * 先决条件： application_core.xml 配置事务注解扫描器 中配置的全局事务 去掉与否 类 @Transactional 注解 去掉与否 方法  @Transactional 注解 去掉与否
     * 结论：
     * >>>>>>1.去掉 全局事务配置 去掉 类头 @Transactional 注解 去掉 方法@Transactional 注解
     * >>>>>> 在异常出现之前 数据更新成功 在异常出现之后 数据更新失败 如果在正常项目中 会 出现在同一方法中 数据没有在一个事务中 前后不一致的情况。
     * >>>>>>2.加上 全局事务配置 去掉 类 @Transactional 注解 去掉 方法 @Transactional 注解
     * >>>>>> 在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>>3.去掉 全局事务配置 加上 类 @Transactional 注解 去掉 方法 @Transactional 注解
     * >>>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>>4.去掉 全局事务配置 去掉 类 @Transactional 注解 加上 方法 @Transactional 注解
     * >>>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * 事务配置的优先级 方法事务注解 > 类事务注解 > 配置文件中事务
     */
    @Test
    public void testTransactionalConfig() {
        iUserService.modifyUser2("lunhengle777777", "78979", 3);
    }

    /**
     * 目的：测试事务传播属性.
     * >>>>>根据 spring 定义的 7 个传播属性 propagation_required  propagation_supports propagation_requires_new propagation_not_supported propagation_never propagation_mandatory propagation_nested 不同定义
     * >>>>>对多次更新数据时出现异常情况 两次(多次) 数据更新 数据能否保持同步
     * 先决条件：系统application_core.xml配置文件中 启用事务注解扫描 将全局事务配置注释掉
     * 结论：
     * >>>>>1.被调用方法中设置事务传播属性是 propagation_required
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>2.被调用方法中设置事务传播属性是 propagation_requires_new
     * >>>>>2.1 调用方法中设置事务传播属性是 propagation_required
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>2.2 调用方法中 不设置事务属性
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>3.被调用方法中设置事务传播属性是 propagation_supports
     * >>>>>3.1调用方法设置事务属性是 propagation_required
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>3.2调用方法中不设置事务
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>4.被调用方法中设置事务传播属性是 propagation_not_supported
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>5.被调用方法中设置事务属性是 propagation_mandatory
     * >>>>>直接调用出现异常 必须在有事务的方法中调用带有此属性的方法
     * >>>>>6.被调用方法设置的事务属性是 propagation_never
     * >>>>> 在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * >>>>>标注：如果调用方法事务说明 默认就是以 propagation_required 传播级别执行。
     * >>>>>7.被调用方法设置事务属性是 propagation_nested
     * >>>>>在异常出现之前和异常出现之后 数据都没有更新成功 如果在正常项目中 不会 出现同一方法中 数据在一个事务中 数据前后不一致的情况。
     * <p>
     * 网上关于 事务传播的解释
     * -- PROPAGATION_REQUIRED
     * -- 加入当前已有事务；只有当前没有事务才起一个新的事务
     * 比如说，ServiceB.methodB的事务级别定义为PROPAGATION_REQUIRED, 那么由于ServiceA.methodA的时候，ServiceA.methodA已经起了事务，
     * 这时调用ServiceB.methodB，ServiceB.methodB看到自己已经运行在ServiceA.methodA的事务内部，就不再起新的事务。
     * 而假如ServiceA.methodA运行的时候发现自己没有在事务中，它就会为自己分配一个事务。
     * 这样，在ServiceA.methodA或者在ServiceB.methodB内的任何地方出现异常，事务都会被回滚。
     * -- PROPAGATION_SUPPORTS
     * -- 如果当前在事务中，即以事务的形式运行，如果当前不在一个事务中，那么就以非事务的形式运行
     * -- PROPAGATION_MANDATORY
     * -- 必须在一个事务中运行。也就是说，只能被一个父事务调用。否则，就要抛出异常。
     * -- PROPAGATION_REQUIRES_NEW
     * -- 比如我们设计ServiceA.methodA的事务级别为PROPAGATION_REQUIRED，ServiceB.methodB的事务级别为PROPAGATION_REQUIRES_NEW，
     * 那么当执行到ServiceB.methodB的时候，ServiceA.methodA所在的事务就会挂起，ServiceB.methodB会起一个新的事务，等待ServiceB.methodB的事务完成以后，
     * 它才继续执行。它与PROPAGATION_REQUIRED 的事务区别在于事务的回滚程度了。因为ServiceB.methodB是新起一个事务，那么就是存在两个不同的事务。
     * 如果ServiceB.methodB已经提交，那么ServiceA.methodA失败回滚，ServiceB.methodB是不会回滚的。如果ServiceB.methodB失败回滚，
     * 它抛出的异常被ServiceA.methodA捕获，ServiceA.methodA事务仍然可以提交。
     * -- PROPAGATION_NOT_SUPPORTED
     * -- 当前不支持事务。比如ServiceA.methodA的事务级别是PROPAGATION_REQUIRED ，而ServiceB.methodB的事务级别是PROPAGATION_NOT_SUPPORTED，
     * 那么当执行到ServiceB.methodB时，ServiceA.methodA的事务挂起，而它以非事务的状态运行完，再继续ServiceA.methodA的事务。
     * -- PROPAGATION_NEVER
     * -- 不能在事务中运行。假设ServiceA.methodA的事务级别是PROPAGATION_REQUIRED， 而ServiceB.methodB的事务级别是PROPAGATION_NEVER，那么ServiceB.methodB就要抛出异常了。
     * -- PROPAGATION_NESTED
     * -- 理解Nested的关键是savepoint。它与PROPAGATION_REQUIRES_NEW的区别是，PROPAGATION_REQUIRES_NEW另起一个事务，将会与它的父事务相互独立，
     * 而Nested的事务和它的父事务是相依的，它的提交是要等和它的父事务一块提交的。也就是说，如果父事务最后回滚，它也要回滚的。而Nested事务的好处是它有一个savepoint。
     * 也就是说ServiceB.methodB失败回滚，那么ServiceA.methodA也会回滚到savepoint点上，ServiceA.methodA可以选择另外一个分支，比如ServiceC.methodC，继续执行，来尝试完成自己的事务。
     * 但是这个事务并没有在EJB标准中定义。
     * 自己的总结：
     * propagation_required propagation_requires_new propagation_nested 的区别？
     * propagation_required 没有子事务和父事务之分 在一个事务中
     * propagation_requires_new 子事务和父事务是两个单独的事务 子事务失败 父事务不一定失败或者父事务失败子事务不一定失败
     * propagation_nested 有子事务和父事务 子事务和父事务在一个事务中 如果子事务或者父事务失败就一块失败
     */
    @Test
    public void testPropagation() {
        iUserService.modifyUser3("lunhengle44444", "77777", 3);
    }

    /**
     * 目的：测试事务隔离级别.
     * >>>> 如果读取在前修改在后 事务隔离级别不起作用
     * 先决条件：系统application_core.xml配置文件中 启用事务注解扫描 将全局事务配置注释掉
     * 1.service 层默认 在
     */
    @Test
    public void testIsolation() {
        List<User> userList = iUserService.readAndModifyUser("lunhengle77777", 3);
        for (User user : userList) {
            logger.info("id>>>>>>>>>" + user.getId() + ">>>>>>username>>>>>" + user.getUsername() + ">>>>>>password>>>>>" + user.getPassword());
        }
    }

}
