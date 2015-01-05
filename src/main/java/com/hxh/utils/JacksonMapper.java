/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hxh.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Administrator
 */
public class JacksonMapper {
    private static final ObjectMapper mapper = new ObjectMapper();
    private JacksonMapper(){}

    /**
     * 获取唯一的ObjectMapper实例
     * @return ObjectMapper对象实例
     */
    public static ObjectMapper getInstance(){
            return mapper;
    }
}
