/*
 * Copyright @ 2016 com.szdtoo
 * easypro 下午1:33:45
 * All right reserved.
 *
 */
package com.school.driving.model.msg;

import java.util.Map;

/**
 * @desc:信息返回对象
 * @author:
 * @createTime:
 * @version: 1.0
 */
public class Message<T> {
	// 状态码
	private final int code;
	// 提示语
	private final String msg;
	// 状态(true=成功)
	private final boolean status;
	// 对象
	private T t;

	// 对象
	private Map map;

	//跳转页面
	private String path;
	/**
	 * @param errorCode
	 */
	public Message(ErrorCode errorCode) {
		super();
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = code < 1400 ? true : false;
	}

	public Message(int code,String msg) {
		this.code = code;
		this.msg = msg;
		this.status = code < 1400 ? true : false;
	}
	/**
	 * @param errorCode
	 * @param t
	 */
	public Message(ErrorCode errorCode, T t) {
		super();
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = code < 1400 ? true : false;
		this.t = t;
	}
	
	public Message(ErrorCode errorCode, T t, String path) {
		super();
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = code < 1400 ? true : false;
		this.t = t;
		this.path=path;
	}

	public Message(ErrorCode errorCode, T t,Map map) {
		super();
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = code < 1400 ? true : false;
		this.t = t;
		this.map=map;
	}


	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @return the t
	 */
	public T getT() {
		return t;
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public Message setT(T t) {
		this.t = t;
		return this;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Message [code=");
		stringBuilder.append(code);
		stringBuilder.append(", msg=");
		stringBuilder.append(msg);
		stringBuilder.append(", status=");
		stringBuilder.append(status);
		stringBuilder.append(", t=");
		stringBuilder.append(t);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	public Map getMap() {
		return map;
	}

	public Message setMap(Map map) {
		this.map = map;
		return this;
	}
}
