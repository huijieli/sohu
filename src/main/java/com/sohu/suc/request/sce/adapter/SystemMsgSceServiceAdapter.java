package com.sohu.suc.request.sce.adapter;

import sce.proto.request.Request.SystemMsg;
import sce.slice.request.MsgTypeEnum;
import sce.slice.request.PagedPbSystemMsgList;
import sce.slice.request.RequestSceServerError;
import sce.slice.request.SystemMsgServicePrx;
import sce.slice.request.SystemMsgServicePrxHelper;

import com.sohu.suc.request.sce.service.SystemMsgSceService;
import com.sohu.suc.sce.core.BaseAdapter;

/**
 * 计数推送服务的适配器
 * 
 * @author linhao
 * @version  Build 2012-4-27
 *
 */
public class SystemMsgSceServiceAdapter extends BaseAdapter implements SystemMsgSceService {

	@Override
	public long createOne(SystemMsg systemMsg) throws RequestSceServerError {
		if (systemMsg == null) {
			String errorMsg = "The parameter 'systemMsg' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().createOne(systemMsg);
	}

	@Override
	public boolean delete(long id, MsgTypeEnum msgType) throws RequestSceServerError {
		if (id <= 0) {
			String errorMsg = "The parameter 'id' (" + id + ") is NOT positive number!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (msgType == null) {
			String errorMsg = "The parameter 'msgType' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().delete(id, msgType);
	}

	@Override
	public PagedPbSystemMsgList getList(MsgTypeEnum msgType, int start, int count) throws RequestSceServerError {
		if (msgType == null) {
			String errorMsg = "The parameter 'msgType' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (start < 0) {
			String errorMsg = "The parameter 'start' (" + start + ")  is NEGATIVE integer!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (count <= 0) {
			String errorMsg =  "The parameter 'count' (" + count + ") is NOT positive integer!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().getList(msgType, start, count);
	}

	@Override
	public SystemMsg getOne(long id, MsgTypeEnum msgType) throws RequestSceServerError {
		if (msgType == null) {
			String errorMsg = "The parameter 'msgType' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (id <= 0) {
			String errorMsg = "The parameter 'id ''" + id + "' is NOT positive integer!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().getOne(id, msgType);
	}

	@Override
	public boolean update(SystemMsg systemMsg) throws RequestSceServerError {
		if (systemMsg == null) {
			String errorMsg = "The parameter 'systemMsg' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().update(systemMsg);
	}
	
	/**
	 * 获取服务
	 * @return 服务
	 */
	SystemMsgServicePrx getService() {
		return SystemMsgServicePrxHelper.uncheckedCast(getPrx("RequestSceSystemMsgService").ice_twoway().ice_timeout(3000));
	}

}
