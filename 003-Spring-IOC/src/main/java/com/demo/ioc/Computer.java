package com.demo.ioc;

import com.google.protobuf.ByteString.Output;

/**
 * 类名称:
 * 类描述:
 *
 * @author legend
 * @since 2023/7/8
 */
public class Computer {

    private Output output;

    public Computer(Output output) {
        this.output = output;
    }

    public void keyIn(String msg){

    }
}
