package com.sohu.suc.request.sce.service;

import sce.proto.request.Request.SystemMsg;
import sce.slice.request.MsgTypeEnum;
import sce.slice.request.PagedPbSystemMsgList;
import sce.slice.request.RequestSceServerError;

/**
 * 计数SCE服务接口
 * 
 * @author linhao
 * @version  Build 2012-4-27
 *
 */
public interface SystemMsgSceService {
	
	/**
	 * 创建系统消息
	 * @param systemMsg 系统消息实例
	 * @return 数据ID
	 * @throws RequestSceServerError 出错时抛出此异常
	 */
	long createOne(SystemMsg systemMsg) throws RequestSceServerError;

	/**
	 * 删除系统消息
	 * @param id 数据ID
	 * @param msgType 消息类型
	 * @return 是否成功
	 * @throws RequestSceServerError 出错时抛出此异常
	 */
	boolean delete(long id, MsgTypeEnum msgType) throws RequestSceServerError;

	/**
	 * 获取系统消息列表
	 * @param msgType 消息类型
	 * @param start 开始索引，以0开始
	 * @param count 获取数量
	 * @return 分页的系统消息列表
	 * @throws RequestSceServerError 出错时抛出此异常
	 */
	PagedPbSystemMsgList getList(MsgTypeEnum msgType, int start, int count) throws RequestSceServerError;

	/**
	 * 获取系统消息
	 * @param id 数据ID
	 * @param msgType 消息类型
	 * @return 系统消息
	 * @throws RequestSceServerError 出错时抛出此异常
	 */
	SystemMsg getOne(long id, MsgTypeEnum msgType) throws RequestSceServerError;

	/**
	 * 更新系统消息
	 * @param systemMsg 系统消息实例
	 * @return 是否成功
	 * @throws RequestSceServerError 出错时抛出此异常
	 */
	boolean update(SystemMsg systemMsg) throws RequestSceServerError;

}
