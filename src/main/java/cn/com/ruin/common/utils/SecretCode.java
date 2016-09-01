package cn.com.ruin.common.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;





import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;


import javax.crypto.spec.SecretKeySpec;




/**
 * BASE64虽然可以用但非官方API，不建议使用
 */
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密包
 */
public class SecretCode {
	/**
	 * MAC算法可选以下多重算法
	 * HmacMD5
	 * HmacSHA1
	 * HmacSHA256
	 * HmacSHA384
	 * HmacSHA512
	 */
	public static final String KEY_MAC="HmacSHA512";
	public static final String KEY_SHA="SHA";
	public static final String KEY_MD5="MD5";
	
	/**
	 * BASE64解密
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public static byte[] decryptBASE64(String key) throws IOException{
		return (new BASE64Decoder()).decodeBuffer(key);
	}
	
	/**
	 * BASE64加密
	 * @param key
	 * @return
	 */
	public static String encryptBASE64(byte[] key) throws IOException{
		return (new BASE64Encoder()).encodeBuffer(key);
	}
	
	/**
	 * md5加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] entryptMD5(byte[] data) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);
		return md5.digest();
	}
	
	/**
	 * SHA1加密
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static byte[] entryptSHA(byte[] data) throws Exception{
		MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
		sha.update(data);
		return sha.digest();
	}
	
	/**
	 * 初始化HMAC密钥
	 * @return
	 * @throws Exception
	 */
	public static String initMacKey() throws Exception{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}
	
	/**
	 * HMAC加密
	 * @param data
	 * @param key
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	public static byte[] encryptHMAC(byte[] data,String key) 
			throws IOException, NoSuchAlgorithmException, InvalidKeyException{
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return mac.doFinal(data);
	}
}
