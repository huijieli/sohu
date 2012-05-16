package com.sohu.suc.request.sce.adapter;

import java.util.List;

import com.sohu.suc.request.sce.service.NormalMsgSceService;
import com.sohu.suc.sce.core.BaseAdapter;

import sce.proto.request.Request.InformMsg;
import sce.proto.request.Request.RequestMsg;
import sce.slice.request.MsgTypeEnum;
import sce.slice.request.NormalMsgServicePrx;
import sce.slice.request.NormalMsgServicePrxHelper;
import sce.slice.request.RequestSceServerError;

/**
 * 普通消息SCE服务的适配器
 * 
 * @author linhao
 * @version 1.0 Build 2012-3-30
 *
 */
public class NormalMsgSceServiceAdapter extends BaseAdapter implements NormalMsgSceService {
	
	@Override
	public long createInform(InformMsg informMsg) throws RequestSceServerError {
		if (informMsg == null) {
			String errorMsg = "The parameter 'informMsg' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().createInform(informMsg);
	}

	@Override
	public boolean createInforms(List<InformMsg> informMsgList) throws RequestSceServerError {
		if (informMsgList == null) {
			String errorMsg = "The parameter 'informMsgList' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (informMsgList.size() == 0) {
			String errorMsg = "The parameter 'informMsgList' is EMPTY!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().createInforms(informMsgList);
	}

	@Override
	public boolean deleteInform(long id, String passport, MsgTypeEnum msgType) throws RequestSceServerError {
		if (id <= 0) {
			String errorMsg = "The parameter 'id' is NOT positive number!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (passport == null || passport.length() == 0) {
			String errorMsg = "The parameter 'passport' is INVALID!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (msgType == null) {
			String errorMsg = "The parameter 'msgType' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().deleteInform(id, passport, msgType);
	}

	@Override
	public long createRequest(RequestMsg requestMsg) throws RequestSceServerError {
		if (requestMsg == null) {
			String errorMsg = "The parameter 'requestMsg' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().createRequest(requestMsg);
	}

	@Override
	public boolean createRequests(List<RequestMsg> requestMsgList) throws RequestSceServerError {
		if (requestMsgList == null) {
			String errorMsg = "The parameter 'requestMsgList' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (requestMsgList.size() == 0) {
			String errorMsg = "The parameter 'requestMsgList' is EMPTY!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().createRequests(requestMsgList);
	}

	@Override
	public boolean deleteRequest(long id, String passport, MsgTypeEnum msgType) throws RequestSceServerError {
		if (id <= 0) {
			String errorMsg = "The parameter 'id' is NOT positive number!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (passport == null || passport.length() == 0) {
			String errorMsg = "The parameter 'passport' is INVALID!";
			throw new RequestSceServerError(1, errorMsg);
		}
		if (msgType == null) {
			String errorMsg = "The parameter 'msgType' is NULL!";
			throw new RequestSceServerError(1, errorMsg);
		}
		return getService().deleteRequest(id, passport, msgType);
	}
	
	/**
	 * 获取服务
	 * @return 服务
	 */
	NormalMsgServicePrx getService() {
		return NormalMsgServicePrxHelper.uncheckedCast(getPrx("RequestSceNormalMsgService").ice_twoway().ice_timeout(3000));
	}



}
