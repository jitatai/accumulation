/*
 * @(#)CommResponse.java      0.1   2013-2-26
 *
 * Copyright (c) 2013 xQuant Co., Ltd. All rights reserved.
 *
 * This software is the confidential and proprietary information of 
 * xQuant Co., Ltd. ("Confidential Information").  
 * You shall not disclose such Confidential Information and shall use it 
 * only in accordance with the terms of the license agreement you entered 
 * into with xQuant.
 */
package com.jt.projects.results;

import com.jt.projects.validation.ValidationExceptionEnum;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.platform.commons.util.StringUtils;

import java.util.HashMap;
import java.util.Map;


/**
 * 通用json返回格式。 无特殊情况下IR系统前后台统一使用该格式作为返回前台json对象的格式。 该对象包含是否成功标志，及需要返回处理的对象。
 * 
 * @version 0.1 2013-2-26
 * @author mohb
 * 
 */
public class CommResponse {

	/**
	 * 返回的对象。
	 */
	private Object msg;

	/**
	 * 是否成功标志。
	 */
	private boolean success;

	private CommResponse(){}
	
	/**
	 * 使用成功表示和返回的对象创建通用json格式。
	 * 
	 * @param success
	 * @param msg
	 */
	public CommResponse(boolean success, Object msg) {
		super();
		this.success = success;
		if (msg != null && StringUtils.isNotBlank(msg.toString())) {
			if(String.class.isInstance(msg)){
				msg =  StringEscapeUtils.escapeHtml4(String.valueOf(msg));
			}
			this.msg = msg;
		} else {
			if(success) {
				this.msg = ValidationExceptionEnum.SUCCESS.getDesc();
			} else {
				this.msg = ValidationExceptionEnum.ERROR_PARAM.getDesc();
			}
		}

	}

	/**
	 * 使用是否成功标志及字符串数据创建通用json对象。,<br/>
	 * 例如<br/>
	 * 传入参数(true,"userId","00212","userName","admin","age","23")创建出以下格式返回对象:<br/>
	 * 
	 * @param success
	 * @param args
	 */
	public CommResponse(boolean success, Object... args) {
		super();
		this.success = success;
		Map<String, Object> map = new HashMap<String, Object>();
		if (args != null && args.length>0) {
			for (int i = 0; i < args.length - 1; i++) {
				if (i % 2 == 0) {
					if (args[i] != null && args[i + 1] != null) {
						map.put(args[i].toString(), args[i + 1]);
					}
				}
			}
			this.msg = map;
		}else{
			if(success) {
				this.msg = ValidationExceptionEnum.SUCCESS.getDesc();
			} else {
				this.msg = ValidationExceptionEnum.ERROR_PARAM.getDesc();
			}
		}
	}

	public Object getMsg() {
		return msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * 将通用json格式转换为的Map类型。
	 *
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", success);
		map.put("msg", msg);
		return map;
	}

    public static CommResponse Ok(Object... args) {
        return new CommResponse(true, args);
    }

    public static CommResponse Fail(Object... args) {
        return new CommResponse(false, args);
    }
    
    public static CommResponse newForNoEscapeMsg(boolean success, Object msg){
    	CommResponse ret = new CommResponse();
    	ret.setSuccess(success);
    	ret.setMsg(msg);
    	return ret;
    }
    
    @SuppressWarnings("unchecked")
	public void supplyMap(Object... args){
		if (args != null && args.length>0) {
			Map<String, Object> map = new HashMap<String, Object>();
			if(this.msg instanceof Map){
				map = (Map<String, Object>)this.msg;
			}
			for (int i = 0; i < args.length - 1; i++) {
				if (i % 2 == 0) {
					if (args[i] != null && args[i + 1] != null) {
						map.put(args[i].toString(), args[i + 1]);
					}
				}
			}
			this.msg = map;
		}
    }
}