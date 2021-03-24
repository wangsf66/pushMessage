package com.pushMessage.websocket.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.douglei.orm.context.SessionContext;
import com.douglei.orm.context.Transaction;
import com.douglei.orm.context.TransactionComponent;
import com.ibs.code.service.BasicService;
import com.ibs.components.filters.request.header.RequestHeaderContext;
import com.ibs.components.response.ResponseContext;
import com.pushMessage.websocket.WebSocket;
import com.pushMessage.websocket.entity.DmMessage;
@TransactionComponent
public class DmMessageService extends BasicService{
	

	@Resource
    private WebSocket webSocket;
	
	//消息入库
	@Transaction
	public void save(DmMessage message) {
		SessionContext.getTableSession().save(message);
	}
	
	//改变消息状态未读改为已读
	@Transaction
	public void updateState(String id) {
		DmMessage message = SessionContext.getSqlSession().uniqueQuery(DmMessage.class,"select * from DM_MESSAGE where id='"+id+"'");
	    if(message!=null) {
	    	message.setState(1);
	    	SessionContext.getSqlSession().executeUpdate("\r\n" + 
	    			"update DM_MESSAGE set STATE = 1 where id='"+id+"'");
	    	ResponseContext.addData(message);
	    }
	}
	
	//推送未读状态消息
	@Transaction
	public void pushMessage() {
	    List<DmMessage> messageList = SessionContext.getSqlSession().query(DmMessage.class,"select * from DM_MESSAGE where  RECEIVER_ID='"+RequestHeaderContext.getTokenEntity().getAccountId()+"' and state=0");
	    ResponseContext.addData(messageList);
	}
	
	//查询历史处理消息
	@Transaction
	public void queryMessage() {
	    List<DmMessage> messageList = SessionContext.getSqlSession().query(DmMessage.class,"select * from DM_MESSAGE where  RECEIVER_ID='"+RequestHeaderContext.getTokenEntity().getAccountId()+"' and state=1");
	    ResponseContext.addData(messageList);
	}
	
	@Transaction
	public void sendMessage(DmMessage message) {
		String[] receiverIds =message.getReceiverIds().split(",");
		if(receiverIds.length<=0) {
			ResponseContext.addValidation("没有指定发送消息的目标对象", null,message);
			return;
		}
		for(String receiverId:receiverIds) {
			message.setSenderId(RequestHeaderContext.getTokenEntity().getAccountId());
			message.setSendTime(new Date());
			message.setState(0);
			message.setReceiverId(receiverId);
			SessionContext.getTableSession().save(message);
			try {
				webSocket.sendMessage(message.getContent(), receiverId);
			} catch (IOException e) {
				ResponseContext.addValidation("消息为实时发布，目标用户不在线", null,message);
			}
		}
		ResponseContext.addData(message);
	}
}
