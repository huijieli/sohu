package com.sohu.suc.request.sce.service;

import java.util.List;

import sce.proto.request.Request.InformMsg;
import sce.proto.request.Request.RequestMsg;
import sce.slice.request.MsgTypeEnum;
import sce.slice.request.RequestSceServerException;

/**
 * 普通消息SCE服务接口
 * 
 * @author linhao
 * @version Build 2012-5-16
 *
 */
public interface NormalMsgSceService {
	
	/**
	 * 创建通知
	 * @param informMsg 通知消息实例
	 * @return 数据ID
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	long createInform(InformMsg informMsg) throws RequestSceServerException;

	/**
	 * 批量创建通知
	 * @param informMsgList 通知消息实例列表
	 * @return 是否成功
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	boolean createInforms(List<InformMsg> informMsgList) throws RequestSceServerException;

	/**
	 * 删除通知
	 * @param id 数据ID
	 * @param passport 接收者的Passport
	 * @param msgType 消息类型
	 * @return 是否成功
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	boolean deleteInform(long id, String passport, MsgTypeEnum msgType) throws RequestSceServerException;
	
	/**
	 * 创建请求
	 * @param requestMsg 请求消息实例
	 * @return 数据ID
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	long createRequest(RequestMsg requestMsg) throws RequestSceServerException;

	/**
	 * 批量创建请求
	 * @param requestMsgList 请求消息实例列表
	 * @return 是否成功
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	boolean createRequests(List<RequestMsg> requestMsgList) throws RequestSceServerException;

	/**
	 * 删除请求
	 * @param id 数据ID
	 * @param passport 接收者的Passport
	 * @param msgType 消息类型
	 * @return 是否成功
	 * @throws RequestSceServerException 出错时抛出此异常
	 */
	boolean deleteRequest(long id, String passport, MsgTypeEnum msgType) throws RequestSceServerException;

}
