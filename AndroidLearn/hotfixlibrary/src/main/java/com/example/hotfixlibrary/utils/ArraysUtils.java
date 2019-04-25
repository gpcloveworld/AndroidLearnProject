package com.example.hotfixlibrary.utils;

import java.lang.reflect.Array;

/**
 * author : Administrator
 * date : 2019/4/21 0021 19:58
 * description :
 */
public class ArraysUtils {

    public static Object appendArray(Object obj, Object obj2) {
        Class componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        Object newInstance = Array.newInstance(componentType, length + 1);
        Array.set(newInstance, 0, obj2);
        for (int i = 1; i < length + 1; i++) {
            Array.set(newInstance, i, Array.get(obj, i - 1));
        }
        return newInstance;
    }


    /**
     * 合并dexElements
     */
    public static Object combineArray(Object arrayLhs, Object arrayRhs) {
        Class<?> componentType = arrayLhs.getClass().getComponentType();
        // 得到左数组长度（补丁数组）
        int i = Array.getLength(arrayLhs);
        // 得到原dex数组长度
        int j = Array.getLength(arrayRhs);
        // 得到总数组长度（补丁数组+原dex数组）
        int k = i + j;
        // 创建一个类型为componentType，长度为k的新数组
        Object result = Array.newInstance(componentType, k);
        System.arraycopy(arrayLhs, 0, result, 0, i);
        System.arraycopy(arrayRhs, 0, result, i, j);
        return result;
    }
}
