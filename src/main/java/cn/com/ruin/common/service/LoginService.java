package cn.com.ruin.common.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import cn.com.ruin.common.bean.User;
import cn.com.ruin.common.dao.LoginDao;
import cn.com.ruin.common.utils.EncryptAndDncrypt;

public class LoginService {
	
	public User login(String loginName,String userPassword) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException{
		User user = null;
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		LoginDao loginDao = new LoginDao();
		try{
			user = loginDao.queryUserPasswordByLoginName(loginName);
			if(user.getPassword().equals(EncryptAndDncrypt.encrypt(userPassword))){
				return user;
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
