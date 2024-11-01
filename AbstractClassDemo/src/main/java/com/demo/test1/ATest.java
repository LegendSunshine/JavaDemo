package com.demo.test1;

import com.demo.test1.ITest1;

/**
 * className:ATest
 * description:
 *
 * @author legend
 * @since 2024/11/1
 */
public abstract class ATest implements ITest1 {
    /*
  * 1.当父类为抽象类 可以选择重写实现接口的方法
  * 2.如果父类实现了接口方法，那么子类可以不实现，因为子类继承了父类
  * 会继承父类的所有方法
  * 3.如果父类（抽象类）没有实现接口方法，则子类必须实现接口方法
    */
}
