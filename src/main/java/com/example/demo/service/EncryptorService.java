package com.example.demo.service;

import static com.example.demo.utils.keyDictionary.STRONG;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.utils.AppProperty;
import com.example.demo.utils.keyDictionary;

@Service
public class EncryptorService {
	
	private String seed;
	private static SecureRandom random = new SecureRandom();
	keyDictionary keyDictionary;
	
	@Autowired
	public EncryptorService(AppProperty properties) {
		keyDictionary = STRONG;
		seed = properties.getSeedPassword();
	}
	
	public String encrypt(String text) {
		String result = "";
		try {
			byte[] key = seed.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			byte[] tmpByte = Base64.encodeBase64(encrypted);
			result = new String(tmpByte);
		} catch (Exception e) {
			result = "";
		}
		return result;
	}
	
	public String decrypt(String text) {
		String resultado = "";
		try {
			byte[] key = seed.getBytes("UTF-8");
			key = Arrays.copyOf(key, 16);
			SecretKeySpec aesKey = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] tmpBClave = Base64.decodeBase64(text);
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decrypted = cipher.doFinal(tmpBClave);
			resultado = new String(decrypted);
		} catch (Exception e) {
			resultado = "";
		}
		return resultado;
	}

	public String randomPassword(int len) {
		String dic = keyDictionary.getValue();
		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}

}
