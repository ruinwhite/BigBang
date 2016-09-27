package cn.com.ruin.common.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import cn.com.ruin.common.bean.Account;
import cn.com.ruin.common.dao.LoginDao;
import cn.com.ruin.common.utils.EncryptAndDncrypt;

public class LoginService {
	
	public Account login(String loginName,String userPassword) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException{
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		LoginDao loginDao = new LoginDao();
		try{
			return loginDao.queryAccountInfoByLoginNameAndPassword(
					loginName,EncryptAndDncrypt.encrypt(userPassword));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
