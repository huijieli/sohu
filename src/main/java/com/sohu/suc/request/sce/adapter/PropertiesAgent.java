package com.sohu.suc.request.sce.adapter;

import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;

import com.sohu.suc.adventure.core.MyLogger;
import com.sohu.suc.adventure.core.MyLoggerFactory;

/**
 * 属性文件代理类
 * @author haolin
 */
public class PropertiesAgent {
	
	/**
	 * 日志对象
	 */
	private final MyLogger log = MyLoggerFactory.getLogger(this.getClass());
	
	/**
	 * 属性类实例引用
	 */
	private Properties properties = null;
	
	/**
	 * 构造方法
	 * @param bundleFilePath 属性文件路径
	 */
	public PropertiesAgent(String bundleFilePath) {
		try{
			properties = getPropertiesFromBundle(bundleFilePath);
		} catch(Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 与制定的属性文件绑定
	 * @param bundleFilePath 属性文件路径
	 * @return 依据属性文件内容填充的Properties实例
	 * @throws Exception 如果绑定失败则抛出此异常
	 */
	public Properties getPropertiesFromBundle(String bundleFilePath) throws Exception {
	    String key,value;
		Properties properties = new Properties();
		PropertyResourceBundle propertyResourceBundle = null;
		try {
			propertyResourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle(bundleFilePath);
		} catch (Exception e) {
			throw new Exception(e);
		}
		Enumeration<String> keys = propertyResourceBundle.getKeys();
	    while (keys.hasMoreElements()) {
	    	key = (String) keys.nextElement();
	    	value = propertyResourceBundle.getString(key);
	    	if (value != null) {
	    		properties.put(key, value);
	    	}
	    }
		return properties;
	}
	
	/**
	 * 根据属性键获取字符串形式的属性值。如果健不存在或值为空，则采用缺省属性值。
	 * @param key 属性键
	 * @param defaultValue 缺省属性值
	 * @return 属性值
	 */
	public String getStringProperty(String key, String defaultValue) {
		String value = null;
		if (properties != null) {
			value = properties.getProperty(key);
		}
		if (null == value || value.trim().length() == 0) {
			value = defaultValue;
		} else {
			value.trim();
		}
		return value;
	}
	  
	/**
	 * 根据属性键获取整数形式的属性值。如果健不存在或值为空，则采用缺省属性值。
	 * @param key 属性的键
	 * @param defaultValue 默认属性值
	 * @return 属性值
	 */
	public Integer getIntegerProperty(String key, int defaultValue) {
		Integer intValue = null;
		String stringValue = getStringProperty(key, null);
		try {
			intValue = Integer.valueOf(stringValue);
		} catch(NumberFormatException nfe) {
			//
		}
		if (intValue == null) {
			return  defaultValue;
		} else {
			return intValue;
		}
	}
	
	/**
	 * 根据属性键获取长整数形式的属性值。如果健不存在或值为空，则采用缺省属性值。
	 * @param key 属性的键
	 * @param defaultValue 默认属性值
	 * @return 属性值
	 */
	public Long getLongProperty(String key, long defaultValue) {
		Long longValue = null;
		String stringValue = getStringProperty(key, null);
		try {
			longValue = Long.valueOf(stringValue);
		} catch(NumberFormatException nfe) {
			//
		}
		if (longValue == null) {
			return  defaultValue;
		} else {
			return longValue;
		}
	}

}
