package com.lee.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

public class WebUtils {
    public static <T> T InsertToBean(Map map, T bean){
        try {
            BeanUtils.populate(bean, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String num,int defaultNo){
        try {
            return Integer.parseInt(num);
        } catch (NumberFormatException e) {
            return defaultNo;
        }

    }
    public static BigDecimal parseDecimal(String num, BigDecimal defaultNo){
        try {
            return BigDecimal.valueOf(Double.parseDouble(num));
        } catch (NumberFormatException e) {
            return defaultNo;
        }
    }
}
