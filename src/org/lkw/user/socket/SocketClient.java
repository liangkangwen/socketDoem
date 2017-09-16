package org.lkw.user.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.lkw.user.entity.UserPO;
import org.lkw.user.utils.CmdResult;

public class SocketClient {
	
	Scanner scanner = new Scanner(System.in);
	Socket socket = null;
	
	/**
	 * 显示主菜单
	 * @param selectNum
	 */
	public void showMeun() {
		System.out.println("*** 请输入要操作的内容  ***\n 1:用户注册 \n 2:登录 \n3：文件上传");
		int selectNum = scanner.nextInt();
		switch (selectNum) {
		case 1:
			regist();
			break;
		case 2:
					
					break;
		case 3:
			
			break;

		default:
			System.out.println("*** 输入的命令有无，系统已退出  ***");
			System.exit(0);
		}
	}
	
	/**
	 * 注册
	 */
	public void regist() {
		System.out.println("*** 请输入用户名 ***");
		UserPO userPO = new UserPO();
		String userName = scanner.next();
		CmdResult cmdResult = new CmdResult();
		userPO.setAccount(userName);
		while(true) {
			System.out.println("*** 请输入密码***");
			String password = scanner.next();
			System.out.println("*** 确认密码***");
			String againPassword = scanner.next();
			if (!password.equals(againPassword)) {
				System.out.println("*** 密码不一致，请重新输入***");
				continue;
			}
			userPO.setPassword(password);
			try {
				socket = new Socket("localhost",9999);
				cmdResult.setCmd("regist");
				cmdResult.setData(userPO);
				sendData(cmdResult);
				cmdResult = getData();
				if (cmdResult != null && cmdResult.isFlag()) {
					System.out.println(cmdResult.getResult());
					break;
				} else {
					System.out.println("*** 系统错误，系统退出***");
					System.exit(0);
				}
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 发送数据
	 * @param cmdResult
	 * @return
	 */
	public void sendData(CmdResult cmdResult) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(cmdResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取返回数据
	 * @return
	 */
	public CmdResult getData() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			return (CmdResult) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
