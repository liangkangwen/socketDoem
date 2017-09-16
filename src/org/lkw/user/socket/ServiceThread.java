package org.lkw.user.socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.lkw.user.Service.UserService;
import org.lkw.user.entity.UserPO;
import org.lkw.user.utils.CmdResult;

public class ServiceThread extends Thread {
	
	Socket socket = null; 
	InputStream inputStream = null;
	BufferedInputStream bis = null;
	ObjectInputStream ois = null;
	UserService userService = null;
	OutputStream outputStream = null;
	BufferedOutputStream bos = null;
	ObjectOutputStream oos = null;
	
	
	public ServiceThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			bis = new BufferedInputStream (inputStream);			  //构建缓冲流
			ois = new ObjectInputStream(bis); //获取对象流
			bos = new BufferedOutputStream(outputStream);
			oos = new ObjectOutputStream(bos);
			CmdResult cmdResult = (CmdResult) ois.readObject();
			execute(cmdResult);
			oos.writeObject(cmdResult);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			close(socket,inputStream,bis,ois,outputStream,bos,oos);
			
		}
	}
	
	/**
	 * 执行业务
	 * @param cmdResult
	 * @return
	 */
	public CmdResult execute(CmdResult cmdResult) {
		String cmd = cmdResult.getCmd();
		switch (cmd) {
		case "regist":
			userService = new UserService();
			UserPO userPo = (UserPO) cmdResult.getData();
			boolean isSuccess = userService.regist(userPo);
			cmdResult.setFlag(isSuccess);
			if (isSuccess) {
				cmdResult.setResult("注册成功");
			} else {
				cmdResult.setResult("注册失败");
			}
			break;
		
		case "login":
			userService = new UserService();
			UserPO userPO = (UserPO) cmdResult.getData();
			UserPO loginUser = userService.login(userPO.getAccount(), userPO.getPassword());
			if (loginUser != null) {
				cmdResult.setFlag(true);
				cmdResult.setData(loginUser);
				cmdResult.setResult("登录成功");
			} else {
				cmdResult.setFlag(false);
				cmdResult.setResult("登录成功");
			}
			break;

		default:
			break;
		}
		return cmdResult;
	}
	/**
	 * 释放资源
	 */
	public void close(Socket socket,InputStream inputStream ,BufferedInputStream bis,ObjectInputStream ois,
			OutputStream outputStream,BufferedOutputStream bos,ObjectOutputStream oos) {
		try {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (ois != null) {
				ois.close();
			}
			if (bis != null) {
				bis.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
