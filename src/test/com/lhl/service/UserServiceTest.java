package com.lhl.service;

import com.lhl.AbstractBaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
 * ----PROPAGATION_MANDATORY 如果存在一个事务，支持当前事务 如果没有一个活动的事务，则抛出异常
 * ----PROPAGATION_NOT_SUPPORTS 总是非事务执行，并挂起任何存在的事务
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
 * ----幻读：指当事务不是独立执行发生的一种现象，例如第一个事务对一个表中的数据进行了修改，这种修改涉及到表中的全部数据行。同时，第二个事务也修改这个表中的数据，这种修改是向表中插入一样新的数据。
 * 那么以后就会发生操作第一个事务的用户发现表中还有没有修改的数据行，这好像发生了幻觉一样。
 */

public class UserServiceTest extends AbstractBaseTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IUserService iUserService;


}
