package com.pushMessage.websocket.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglei.api.doc.annotation.Api;
import com.douglei.api.doc.annotation.ApiParam;
import com.douglei.api.doc.annotation.ApiParam_;
import com.ibs.code.controller.BasicController;
import com.ibs.components.response.Response;
import com.ibs.components.response.ResponseContext;
import com.ibs.spring.resolver.method.argument.CommonParams;
import com.ibs.spring.resolver.method.argument.CommonParamsModel;
import com.pushMessage.websocket.entity.DmMessage;
import com.pushMessage.websocket.service.DmMessageService;

@RestController
@RequestMapping("/message")
public class DmMessageController extends BasicController{
	
    
	@Autowired
	private DmMessageService messageService;
    
	@Api(name="更新消息状态",
			 url=@ApiParam(params ={
					 @ApiParam_(name="id", required=true, description="消息id", egValue="1111")
			 }))
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public Response delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		if(id!=null) {
			messageService.updateState(id);
		}
	    return ResponseContext.getFinalResponse();
	}
	
	@Api(name="推送未读状态消息")
	@RequestMapping(value="/pull",method=RequestMethod.GET)
	public Response pushMessage() {
		messageService.pushMessage();
	    return ResponseContext.getFinalResponse();
	}
	
	@Api(name="查询已读状态消息")
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public Response queryMessage() {
		messageService.queryMessage();
	    return ResponseContext.getFinalResponse();
	}
	
	@Api(name="发送消息")
	@RequestMapping(value="/send",method=RequestMethod.POST)
	public Response sendMessage(@CommonParams(cls=DmMessage.class)CommonParamsModel<DmMessage> commonParamsModel) {
		messageService.sendMessage(commonParamsModel.getList().get(0));
	    return ResponseContext.getFinalResponse();
	}
	
}
