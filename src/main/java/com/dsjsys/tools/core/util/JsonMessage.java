package com.dsjsys.tools.core.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/** 
 * @author  lvzheng lz13519680617@gmail.com
 * @date 创建时间：2016年6月23日 下午9:54:27 
 */
public class JsonMessage {

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String info;
	
	private Object content;
	
	private String url;
	
	private int status;
	
	private List<Errors> errors;
	
	public JsonMessage(Object content,String info,String url,int status){
		this.content=content;
		this.info = info;
		this.url = url;
		this.status = status;
	}
	
	public JsonMessage(Object content,String info,String url,int status,List<Errors> errors){
		this.content=content;
		this.info = info;
		this.url = url;
		this.status = status;
		this.setErrors(errors);
		
	}
	
	public JsonMessage(){
	}
	
	public JsonMessage(String info){
		this.info=info;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	public List<Errors> getErrors() {
		return errors;
	}

	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}
	
	
	
}
