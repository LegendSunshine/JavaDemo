package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @className App
 * @description:
 * @author legend
 * @date 2025/2/19 20:37
 * @version 1.0
 */
public class App {
    public boolean isMatch(String s, String p) {
        char[] pChars = p.toCharArray();
        char[] sChars = s.toCharArray();
        // 记录*的位置当遇到*p字符数组index不加直到s下个字符与上个字符不相等
        int temp = 0;
        boolean flag = false;
        if (!p.contains("*")) {
            if (pChars.length != sChars.length) {
                return false;
            }
        } else {
            for (int i = 0; i < sChars.length; i++) {
                if(i!= 0&& sChars[i]!=sChars[i-1]){
                    temp++;
                }
                if (sChars[i] == pChars[temp]) {
                    temp++;
                    flag = true;
                    continue;
                } else {
                    if (pChars[temp] == '*') {
                        flag = true;
                    } else if (pChars[temp] == '.') {
                        temp++;
                        flag = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.isMatch("abcabcccccc", "abcabcc*"));
    }
}