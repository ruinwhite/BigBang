package cn.com.ruin.common.utils;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptAndDncrypt {
	private static String key = 
			"oUMZQnkfWFcNOMhpKgB0cWca2GnCIS6AnkHChJXflT4JsqaMTbiLBrHgXiKCf88HYKABUsePMU6QrEYaaNH8Ow==";
	
	public static String encrypt(String password) 
			throws InvalidKeyException, NoSuchAlgorithmException, IOException{
		return (new BigInteger(SecretCode.encryptHMAC(password.getBytes(), key))).toString(16);
	}
}
