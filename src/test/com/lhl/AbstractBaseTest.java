package com.lhl;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lenovo on 2016/4/19.
 */
@ContextConfiguration(locations = {"classpath:application_core.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractBaseTest {
}
