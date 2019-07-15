package com.hkkj.carmall.utils;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {

	private static final String IV_STRING = "A-16-Byte-String";
	private static final String charset = "UTF-8";
	private static final String defaultKey = "hkkj2019hkkj2019";

	/**
	 * 加密
	 */
	public static String aesEncryptString(String content) {
		return aesEncryptString(content, defaultKey);
	}

	/**
	 * 加密
	 */
	public static String aesEncryptString(String content, String key) {
		try {
			byte[] contentBytes = content.getBytes(charset);
			byte[] keyBytes = key.getBytes(charset);
			byte[] encryptedBytes = aesEncryptBytes(contentBytes, keyBytes);
			return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP);
		} catch (Exception e) {
			Log.e("e","AES加密字符串异常:"+e.toString());
		}
		return null;
	}

	/**
	 * 解密
	 */
	public static String aesDecryptString(String content) {
		return aesDecryptString(content, defaultKey);
	}

	/**
	 * 解密
	 */
	public static String aesDecryptString(String content, String key) {
		try {
			content=content.replaceAll(" ", "+");//base64问题
			byte[] encryptedBytes = Base64.decode(content, Base64.DEFAULT);
			byte[] keyBytes = key.getBytes(charset);
			byte[] decryptedBytes = aesDecryptBytes(encryptedBytes, keyBytes);
			return new String(decryptedBytes, charset);
		} catch (Exception e) {
			Log.e("e","AES解密字符串异常:"+e.toString());
		}
		return null;
	}

	public static byte[] aesEncryptBytes(byte[] contentBytes, byte[] keyBytes) {
		try {
			return cipherOperation(contentBytes, keyBytes, Cipher.ENCRYPT_MODE);
		} catch (Exception e) {
			Log.e("e","AES加密字符串异常:"+e.toString());
		}
		return null;
	}

	public static byte[] aesDecryptBytes(byte[] contentBytes, byte[] keyBytes) {
		try {
			return cipherOperation(contentBytes, keyBytes, Cipher.DECRYPT_MODE);
		} catch (Exception e) {
			Log.e("e","AES解密字符串异常:"+e.toString());
		}
		return null;
	}

	private static byte[] cipherOperation(byte[] contentBytes, byte[] keyBytes, int mode) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			byte[] initParam = IV_STRING.getBytes(charset);
			IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(mode, secretKey, ivParameterSpec);

			return cipher.doFinal(contentBytes);
		} catch (Exception e) {
			Log.e("e","AES加密字符串异常:"+e.toString());
		}
		return null;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String key = "hkkj2019hkkj2019";
//		String string = AesUtil.aesEncryptString("123456", key);
//		System.out.println(string);
		System.out.println(AesUtil.aesDecryptString("qE98tXq0DOorBPSHUxDbZiEkU8FjQ7EjB01wj9iNUXOIUQHsnSNHBq7cPDKJHBum", key));
	}

}
