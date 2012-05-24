package com.sohu.suc.request.sce.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sce.proto.request.Request.InformMsg;
import sce.proto.request.Request.RequestMsg;
import sce.proto.request.Request.SystemMsg;
import sce.slice.request.MsgTypeEnum;
import sce.slice.request.PagedPbSystemMsgList;
import sce.slice.request.RequestSceServerException;

public class RequestSceServiceFactoryTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// 用于本地测试的设置 - begin
		System.setProperty("$suc$sce$RequestSceNormalMsgService", "RequestSceNormalMsgService:tcp -h localhost -p 12201");
		System.setProperty("$suc$sce$RequestSceSystemMsgService", "RequestSceSystemMsgService:tcp -h localhost  -p 12202");
		// 用于本地测试的设置 - end
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNormalMsgSceService() {
		try {
			NormalMsgSceService service = RequestSceServiceFactory.getNormalMsgSceService();
			assertNotNull(service);
			MsgTypeEnum informType = MsgTypeEnum.T0101;
			String receiver = "freej@sohu.com";
			InformMsg informMsg = 
					InformMsg.newBuilder()
					.setAppCode("test")
					.setType(informType.toString())
					.setTemplateId(0)
					.setSender("freej@sogou.com")
					.setReceiver(receiver)
					.setTitle("")
					.setDescription("测试")
					.setUrl("")
					.setCreationTime(System.currentTimeMillis())
					.build();
			long id = service.createInform(informMsg);
			assertTrue(id > 0);
			boolean done = service.deleteInform(id, receiver,informType);
			MsgTypeEnum requestType = MsgTypeEnum.T0203;
			RequestMsg requestMsg = 
					RequestMsg.newBuilder()
					.setAppCode("test")
					.setType(requestType.toString())
					.setTemplateId(0)
					.setSender("freej@sogou.com")
					.setReceiver(receiver)
					.setTitle("")
					.setDescription("测试")
					.setUrl("")
					.setCreationTime(System.currentTimeMillis())
					.build();
			id = service.createRequest(requestMsg);
			assertTrue(id > 0);
			done = service.deleteRequest(id, receiver, requestType);
			assertTrue(done);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetSystemMsgSceService() {
		SystemMsgSceService service = null;
		long id = -1;
		MsgTypeEnum systemMessageType = null;
		try {
			service = RequestSceServiceFactory.getSystemMsgSceService();
			assertNotNull(service);
			systemMessageType = MsgTypeEnum.T9901;
			long currentTimeMillis = System.currentTimeMillis();
			SystemMsg systemMsg = 
					SystemMsg.newBuilder()
					.setType(systemMessageType.toString())
					.setTitle("SCE测试-标题")
					.setContent("SCE测试-内容")
					.setUrl("http://www.sohu.com")
					.setRepetition(0)
					.setStartTime(currentTimeMillis)
					.setEndTime(currentTimeMillis)
					.setCreationTime(currentTimeMillis)
					.setLastUpdateTime(currentTimeMillis)
					.build();
			id = service.createOne(systemMsg);
			assertTrue(id > 0);
			SystemMsg updatedSystemMsg = 
					SystemMsg.newBuilder()
					.mergeFrom(systemMsg)
					.setUrl("http://i.sohu.com")
					.setId(id)
					.build();
			boolean done = service.update(updatedSystemMsg);
			assertTrue(done);
			SystemMsg storedSystemMsg = service.getOne(id, systemMessageType);
			assertEquals(updatedSystemMsg, storedSystemMsg);
			int start = 0;
			int count = 100;
			PagedPbSystemMsgList systemMsgList = service.getList(systemMessageType, start, count);
			assertNotNull(systemMsgList);
			assertEquals(start, systemMsgList.start);
			assertEquals(count, systemMsgList.count);
			assertTrue(systemMsgList.total >= 0);
			assertNotNull(systemMsgList.systemMsgList);
			assertTrue(systemMsgList.systemMsgList.size() >= 0);
			assertTrue(systemMsgList.systemMsgList.contains(updatedSystemMsg));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		} finally {
			if (service != null) {
				boolean done;
				try {
					done = service.delete(id, systemMessageType);
					assertTrue(done);
				} catch (RequestSceServerException e) {
					e.printStackTrace();
					fail(e.getMessage());
				}
			}
		}
	}


}
