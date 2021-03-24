package com.pushMessage.websocket.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibs.login.TokenCheckService;
import com.pushMessage.websocket.service.DmMessageService;

@Component
public class MsgInjectServiceUtil {
	
	@Autowired
	private TokenCheckService tokenCheckService;
	
   
	@Autowired
    private DmMessageService dmMessageService;
 
    @PostConstruct
    public void init(){
        MsgInjectServiceUtil.getInstance().dmMessageService = this.dmMessageService;
        MsgInjectServiceUtil.getInstance().tokenCheckService = this.tokenCheckService;
    }
 
    /**
     *  实现单例 start
     */
    private static class SingletonHolder {
        private static final MsgInjectServiceUtil INSTANCE = new MsgInjectServiceUtil();
    }
    private MsgInjectServiceUtil (){}
    public static final MsgInjectServiceUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
	public DmMessageService getDmMessageService() {
		return dmMessageService;
	}
	public TokenCheckService getTokenCheckService() {
		return tokenCheckService;
	}
	public void setTokenCheckService(TokenCheckService tokenCheckService) {
		this.tokenCheckService = tokenCheckService;
	}
	
}
