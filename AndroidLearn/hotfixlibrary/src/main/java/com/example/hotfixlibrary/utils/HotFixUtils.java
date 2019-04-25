package com.example.hotfixlibrary.utils;

import android.content.Context;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * author : Administrator
 * date : 2019/4/21 0021 20:01
 * description :
 */
public class HotFixUtils {

    //可能存在多个有问题的dex
    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        loadedDex.clear();
    }

    /**
     * 加载补丁，使用默认目录：data/data/包名/files/odex
     *
     * @param context
     */
    public static void loadFixedDex(Context context) {

        File firDir = context.getDir(Contants.DEX_DIR, Context.MODE_PRIVATE);
        File[] listFiles = firDir.listFiles();
        for (File file : listFiles
                ) {
            String fileName = file.getName();
            if (file.getName().startsWith(Contants.DEX_PREFIX) &&
                    (file.getName().endsWith(Contants.DEX_SUFFIX)
                            || file.getName().endsWith(Contants.APK_SUFFIX)
                            || file.getName().endsWith(Contants.JAR_SUFFIX)
                            || file.getName().endsWith(Contants.ZIP_SUFFIX))) {
                // 存入集合
                loadedDex.add(file);
            }
        }
        createDexClassLoader(context, firDir);
    }

    private static void createDexClassLoader(Context context, File firDir) {

        //创建解压目录
        String optimizeDir = firDir.getAbsolutePath() + File.separator + Contants.OPTIMIZE_DEX_DIR;
        File fopt = new File(optimizeDir);
        if (!fopt.exists()) {
            fopt.mkdirs();
        }
        for (File dex : loadedDex
                ) {
            //自有的类加载器
            DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(), optimizeDir, null, context.getClassLoader());

            //每循环一次，修复一次(插桩)
            hotfix(classLoader, context);

        }
    }

    private static void hotfix(DexClassLoader classLoader, Context context) {
        //获取系统的PathClassLoader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();
        try {
            //获取自有的dexElements数组
            Object myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader));
            //获取系统的dexElements数组
            Object sysElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));
            //合并并且生成新的dexElements数组
            Object dexElements = ArraysUtils.combineArray(myElements, sysElements);
            //获取系统的PathList
            Object sysPathList = ReflectUtils.getPathList(pathClassLoader);
            //通过反射技术，将新的dexElements数组赋值给系统的PathList
            ReflectUtils.setField(sysPathList, sysPathList.getClass(), dexElements);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
