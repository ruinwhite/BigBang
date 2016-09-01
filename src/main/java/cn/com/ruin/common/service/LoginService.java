package cn.com.ruin.common.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import cn.com.ruin.common.bean.Account;
import cn.com.ruin.common.dao.LoginDao;
import cn.com.ruin.common.utils.EncryptAndDncrypt;

public class LoginService {
	
	public boolean login(String loginName,String password) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException{
		if(loginName == null || "".equals(loginName.trim())){
			return false;
		}
		if(loginName == null || "".equals(loginName.trim())){
			return false;
		}
		LoginDao loginDao = new LoginDao();
		Account account = loginDao.queryAccountInfoByLoginName(loginName);
		if(account == null || account.getPassword() == null 
				|| "".equals(account.getPassword().trim())){
			return false;
		}else if(account.getPassword().equals(EncryptAndDncrypt.encrypt(password))){
			return true;
		}else{
			return false;
		}
	}
}
