package com.gpc.buglystudy.base.utils;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesUtil {

	private final static String TAG = "PropertiesUtil";

	/**
	 * 读取配置文件，不存在则先创建配置文件，返回Properties对象
	 * @param file Properties文件的路径
	 * @return Properties
	 */
	public static Properties loadConfig(String file) {
		Properties properties = new Properties();
		try {
			File fil = new File(file);
			if (!fil.exists())
				fil.createNewFile();
			FileInputStream s = new FileInputStream(file);
			properties.load(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return properties;
	}
	

	/**
	 * 读配置信息
	 * @param file  配置文件路径
	 * @param key   需要键值
	 * @return      对应的值
	 */
    public static String getProperty(String file, String key) {
    	Properties p=loadConfig(file);
        return p.getProperty(key);  
    }  
  

	/**
	 * 写配置信息
	 * @param file  配置文件路径
	 * @param key   键值
	 * @param value 对应的值
	 *              comment是该条属性的注释
	 */
    public static void putProperty(String file, String key, String value) {
		//先加载可以已有配置文件，实现增加配置和修改。若直接Properties p=new Properties()则是覆盖写
    	Properties p= loadConfig(file);
        p.setProperty(key, value);
        FileOutputStream fos=null;
        try {  
            fos = new FileOutputStream(file);
            p.store(fos, null);  
        } catch (Exception e) {
        	e.printStackTrace();
        }finally{
			try {
				if (fos!=null) {
					fos.flush();
					FileDescriptor fd = fos.getFD();
					fd.sync();
					fos.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	       
		}  
    }  


}
