package com.dsjsys.tools.core.util;
/** 
 * @author  lvzheng lz13519680617@gmail.com
 * @date 创建时间：2016年6月30日 上午10:43:53 
 */
public class Errors {
	private String errorMessage;
	
	public Errors(String errorMessage){
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
