package org.lkw.user.utils;

import java.io.Serializable;

public class CmdResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//输入的命令
	private String cmd;
	//传递的数据
	private Object data;
	//运行的结果：true : 成功 false ：失败
	private boolean flag;
	//提示信息
	private String result;
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	

}
