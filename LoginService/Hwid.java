package cn.Aurora.LoginService;

import java.awt.HeadlessException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JOptionPane;

import cn.Aurora.Client;
import cn.Aurora.utils.WebUtils;

public class Hwid {
	public static String getHWID() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		StringBuilder s = new StringBuilder();
		String main = System.getenv("PROCESS_IDENTIFIER") + System.getenv("COMPUTERNAME");
		byte[] bytes = main.getBytes("UTF-8");
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] md5 = messageDigest.digest(bytes);
		int i = 0;
		for(byte b : md5) {
			s.append(Integer.toHexString((b & 0xFF) | 0x300),0,3);
			if(i != md5.length -1) {
				s.append("");
			}
			i++;
//			System.out.println(calendar.get(Calendar.YEAR));
		}
		return (s.toString()).substring(s.length()-15,s.length());
	}
	
	public boolean Login() throws HeadlessException, NoSuchAlgorithmException, UnsupportedEncodingException, IOException{
			Client info = new Client();
			String clientversion = "" + info.version;
			System.out.println("Version:" + clientversion);
			System.out.println("User Hwid:" + getHWID());
			///更换为自己的验证文件,百度搜搜码云注册！
			try {
			if (WebUtils.get("https://banejr.github.io/Hwid.txt").contains(getHWID())) {
				System.out.println("Verificated Successfully");
			}else {
				
				JOptionPane.showMessageDialog(null,"Hwid未授权!" + "\n" + "请您访问小卖铺购买授权!(小卖铺地址:" + info.onlinestore + ")" + "\n" +  "您的Hwid:" + "\n" + getHWID() ,"Aurora Client",JOptionPane.WARNING_MESSAGE);
				System.out.println("Verification Failed");
				System.exit(0);
			}
			if (WebUtils.get("https://banejr.github.io/Version.txt").contains(clientversion)) {
				System.out.println("Is Allow-Login Version!");
			}else {
				JOptionPane.showMessageDialog(null,"版本已失效，请检查最新版本!","Aurora Client",JOptionPane.WARNING_MESSAGE);
				System.out.println("Version Out!");
				System.exit(0);
			}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"内部错误!","Aurora Client",JOptionPane.ERROR_MESSAGE);
				System.out.println("Error!");
				System.exit(0);
			}
			return true;	
		}
}
