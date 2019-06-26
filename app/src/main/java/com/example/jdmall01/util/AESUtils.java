package com.example.jdmall01.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//public class AESUtils {
//
//
//
//
//    //密钥
////	public static final String KEY = "1234567891234567";
//    public static final String KEY = "1234";
//
//    //加密算法
//    public static String encrypt(String src) throws Exception {
//
//        byte[] rawKey = getRawKey(KEY.getBytes());
//        byte[] result = encrypt(rawKey, src.getBytes());
//
//        return toHex(result);
//    }
//
//    //解密算法
//    public static String decrypt(String encrypted) throws Exception {
//        byte[] rawKey = getRawKey(KEY.getBytes());
//        byte[] enc = toByte(encrypted);
//        byte[] result = decrypt(rawKey, enc);
//        return new String(result);
//    }
//
//    private static byte[] getRawKey(byte[] seed) throws Exception {
//        KeyGenerator kgen = KeyGenerator.getInstance("AES");
//
//        SecureRandom sr = null;
////		if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.JELLY_BEAN) {
////			sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
////		} else {
//        sr = SecureRandom.getInstance("SHA1PRNG");
////		}
//        sr.setSeed(seed);
//        kgen.init(256, sr); // 256 bits or 128 bits,192bits
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        return raw;
//    }
//
////    private static byte[] encrypt(byte[] key, byte[] src) throws Exception {
////        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
////        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
////        String iv = "aabbccddeeffgghh";//初始化向量参数，AES 为16bytes. DES 为8bytes.
////        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
////        cipher.init(Cipher.ENCRYPT_MODE,skeySpec,ivSpec);
//////        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
////        byte[] encrypted = cipher.doFinal(src);
////        return encrypted;
////    }
//
//    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
//
////		String iv   = "aabbccddeeffgghh";//初始化向量参数，AES 为16bytes. DES 为8bytes.
////		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
////		cipher.init(Cipher.ENCRYPT_MODE,  skeySpec, ivSpec);
//
//        byte[] encrypted = cipher.doFinal(clear);
//        return encrypted;
//    }
//
////    private static byte[] decrypt(byte[] key, byte[] encrypted)
////            throws Exception {
////        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
////        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
////        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
////        byte[] decrypted = cipher.doFinal(encrypted);
////        return decrypted;
////    }
//
//    private static byte[] decrypt(byte[] raw, byte[] encrypted)
//            throws Exception {
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
//        byte[] decrypted = cipher.doFinal(encrypted);
//        return decrypted;
//    }
//
//    public static String toHex(String txt) {
//        return toHex(txt.getBytes());
//    }
//
//    public static String fromHex(String hex) {
//        return new String(toByte(hex));
//    }
//
//    public static byte[] toByte(String hexString) {
//        int len = hexString.length() / 2;
//        byte[] result = new byte[len];
//        for (int i = 0; i < len; i++)
//            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
//                    16).byteValue();
//        return result;
//    }
//
//    public static String toHex(byte[] buf) {
//        if (buf == null)
//            return "";
//        StringBuffer result = new StringBuffer(2 * buf.length);
//        for (int i = 0; i < buf.length; i++) {
//            appendHex(result, buf[i]);
//        }
//        return result.toString();
//    }
//
//    private final static String HEX = "0123456789ABCDEF";
//
//    private static void appendHex(StringBuffer sb, byte b) {
//        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
//    }
//}



public class AESUtils {
	public static final String TAG = "AESUtils";

	public static final String key = "1234567891234567";

	public static String encrypt(String clearText)
	{
		byte[] result = null;
		try
		{
			byte[] rawkey = getRawKey(key.getBytes());
			result = encrypt(rawkey, clearText.getBytes());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		String content = toHex(result);//加密后的内容
		return content;
	}

	public static String decrypt(String encrypted)
	{
		try {
			byte[] rawKey = getRawKey(key.getBytes());
			byte[] enc = toByte(encrypted);
			byte[] result = decrypt(rawKey, enc);
			String content = new String(result);//String content = new String(result, "ISO-8859-1");
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static byte[] getRawKey(byte[] seed) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = null;// SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
//		if (android.os.Build.VERSION.SDK_INT >=  17) {
//			sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
//		} else{
			sr = SecureRandom.getInstance("SHA1PRNG");
//		}

		sr.setSeed(seed);
		kgen.init(128, sr);//256 bits or 128 bits,192bits
		SecretKey sKey = kgen.generateKey();
		byte[] raw = sKey.getEncoded();

		return raw;
	}

	private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));

//		String iv   = "aabbccddeeffgghh";//初始化向量参数，AES 为16bytes. DES 为8bytes.
//		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
//		cipher.init(Cipher.ENCRYPT_MODE,  skeySpec, ivSpec);

		byte[] encrypted = cipher.doFinal(clear);
		return encrypted;
	}

	private static byte[] decrypt(byte[] raw, byte[] encrypted)
			throws Exception {
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[cipher.getBlockSize()]));
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

	public static String toHex(String txt) {
		return toHex(txt.getBytes());
	}

	public static String fromHex(String hex) {
		return new String(toByte(hex));
	}

	public static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++)
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
					16).byteValue();
		return result;
	}

	public static String toHex(byte[] buf) {
		if (buf == null)
			return "";
		StringBuffer result = new StringBuffer(2 * buf.length);
		for (int i = 0; i < buf.length; i++) {
			appendHex(result, buf[i]);
		}
		return result.toString();
	}

	private static void appendHex(StringBuffer sb, byte b) {
		final String HEX = "0123456789ABCDEF";
		sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
	}
}

