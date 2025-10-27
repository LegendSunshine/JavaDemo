package com.legend;

import com.legend.util.JsonUtils;

/**
 * @className JSONTestApp
 * @description:
 * @author legend
 * @date 2025/5/30 21:48
 * @version 1.0
 */
public class JSONTestApp {
    public static void main(String[] args) {
        String a ="1111";
        String s1 = JsonUtils.toJSONString(a);
        System.out.println(s1);
        String s = JsonUtils.toJavaObject(s1, String.class);
        System.out.println(s);
    }
}
