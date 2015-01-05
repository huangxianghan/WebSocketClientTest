/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hxh.utils;

/**
 * 常用字符串处理类
 * @author 黄香翰
 */
public class StringUtil {
    
    /**
     * 判断字符串是否为空,会去掉头尾空格
     * @param strVal 字符串值
     * @return true不为空，false为空。
     */
    public static boolean hasLen(String strVal){
        return (strVal != null && !strVal.trim().equals("") && strVal.length()>0);
    }
}
