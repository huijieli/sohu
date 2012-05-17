package com.sohu.suc.request.sce.service;

import java.lang.reflect.Proxy;

import com.sohu.suc.request.sce.adapter.NormalMsgSceServiceAdapter;
import com.sohu.suc.request.sce.adapter.SystemMsgSceServiceAdapter;
import com.sohu.suc.sce.adapter.BlockCutterInvocationHandler;

/**
 * 奇遇系统SCE服务工厂
 * 
 * @author linhao
 * @version 1.0 Build 2012-3-30
 *
 */
public class RequestSceServiceFactory {
	
	/**
	 * 普通消息SCE服务
	 */
	private static volatile NormalMsgSceService normalMsgSceService = null;
	
	/**
	 * 系统消息SCE服务
	 */
	private static volatile SystemMsgSceService systemMsgSceService = null;
	
	public static NormalMsgSceService getNormalMsgSceService() {
		if (normalMsgSceService == null) {
            synchronized (NormalMsgSceService.class) {
                if (normalMsgSceService == null) {
                	normalMsgSceService = 
                			createAdapter(
                					NormalMsgSceService.class, 
                					new NormalMsgSceServiceAdapter(), 
                					"RequestSceNormalMsgService");
                }
            }
        }
		return normalMsgSceService;
	}
	
	public static SystemMsgSceService getSystemMsgSceService() {
		if (systemMsgSceService == null) {
            synchronized (SystemMsgSceService.class) {
                if (systemMsgSceService == null) {
                	systemMsgSceService = 
                			createAdapter(
                					SystemMsgSceService.class, 
                					new SystemMsgSceServiceAdapter(), 
                					"RequestSceSystemMsgService");
                }
            }
        }
		return systemMsgSceService;
	}
	
	
    /**
     * 创建适配器
     * @param c 类型
     * @param obj 实例
     * @param serviceName 服务名称
     * @return 适配器
     */
    private static <T> T createAdapter(Class<?> c, final T obj, final String serviceName) {
        return createAdapter(new Class[] { c }, obj, serviceName);
    }

    /**
     * 创建适配器
     * @param cs 类型数组
     * @param obj 实例
     * @param serviceName 服务名称
     * @return 适配器
     */
    @SuppressWarnings("unchecked")
    private static <T> T createAdapter(Class<?>[] cs, final T obj, final String serviceName) {
        for (Class<?> c : cs) {
            if (!c.isInterface()) throw new IllegalArgumentException("the class type " + c.getName() + " must be an interface");
        }
        return (T) Proxy.newProxyInstance(
        		RequestSceServiceFactory.class.getClassLoader(),
                cs,
                new BlockCutterInvocationHandler(serviceName, obj));
    }
	
}
