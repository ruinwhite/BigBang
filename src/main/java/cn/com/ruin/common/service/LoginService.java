package cn.com.ruin.common.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import cn.com.ruin.common.bean.User;
import cn.com.ruin.common.dao.UserDao;
import cn.com.ruin.common.utils.EncryptAndDncrypt;

public class LoginService {
	
	/**
	 * 登录服务
	 * @param loginName
	 * @param userPassword
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public User login(String loginName,String userPassword) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException{
		User user = null;
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		if(loginName == null || "".equals(loginName.trim())){
			return null;
		}
		UserDao userDao = new UserDao();
		try{
			user = userDao.queryUserPasswordByLoginName(loginName);
			if(user == null){
				return null;
			}else if(user.getPassword().equals(EncryptAndDncrypt.encrypt(userPassword))){
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
