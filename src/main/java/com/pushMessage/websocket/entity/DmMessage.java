package com.pushMessage.websocket.entity;

import java.util.Date;

public class DmMessage{
	private String id;
	private String content;
	private String senderId;
	private String receiverId;
	private String receiverIds;
	private int msgType;
	private Date sendTime;
	//0未读 1 已读
	private int state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverIds() {
		return receiverIds;
	}
	public void setReceiverIds(String receiverIds) {
		this.receiverIds = receiverIds;
	}
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public DmMessage(String content, String senderId, String receiverId, int msgType, Date sendTime, int state) {
		super();
		this.content = content;
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.msgType = msgType;
		this.sendTime = sendTime;
		this.state = state;
	}
	public DmMessage() {
		super();
	}
}
