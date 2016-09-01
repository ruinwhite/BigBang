package husky;

import java.math.BigInteger;

import org.junit.Test;

import cn.com.ruin.common.utils.SecretCode;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TestSecretCode {
	@Test
	public void ttt() throws Exception{
		String inputStr = "zxysdmn";
		System.out.println("原文:\n"+inputStr);
		byte[] inputData = inputStr.getBytes();
//		String code = SecretCode.encryptBASE64(inputData);
//		System.out.println("BASE64加密后:\n"+code);
//		byte[] output = SecretCode.decryptBASE64(code);
//		String outputStr = new String(output);
//		System.out.println("BASE64解密后:\n"+outputStr);
//		//验证BASE64加密解密是否一致
//		assertEquals(inputStr,outputStr);
//		//验证MD5对同一个字符串加密是否一致
//		assertArrayEquals(SecretCode.entryptMD5(inputData), SecretCode.entryptMD5(inputData));
//		//验证SHA对同一个字符加密是否一致
//		assertArrayEquals(SecretCode.entryptSHA(inputData),SecretCode.entryptSHA(inputData));
		String key = "oUMZQnkfWFcNOMhpKgB0cWca2GnCIS6AnkHChJXflT4JsqaMTbiLBrHgXiKCf88HYKABUsePMU6QrEYaaNH8Ow==";
//				SecretCode.initMacKey();
		System.out.println("密钥：\n"+key);
		//验证HMAC对统一内容统一密钥加密是否一致
		assertArrayEquals(SecretCode.encryptHMAC(inputData, key), 
				SecretCode.encryptHMAC(inputData, key));
		
		
//		BigInteger md5 = new BigInteger(SecretCode.entryptMD5(inputData));
//		System.out.println("MD5:\n"+md5.toString(16));
//		
//		BigInteger sha = new BigInteger(SecretCode.entryptSHA(inputData));
//		System.out.println("SHA:\n"+sha.toString(32));
		
		BigInteger hmac = new BigInteger(SecretCode.encryptHMAC(inputData, key));
		System.out.println("HMAC:\n"+hmac.toString(16));
	}
}
