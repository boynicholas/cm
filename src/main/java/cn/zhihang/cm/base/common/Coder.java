package cn.zhihang.cm.base.common;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder; 
import sun.misc.BASE64Encoder;

public abstract class Coder {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_MD5 = "MD5";
    
    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HMACMD5
     * HMACSHA1
     * HMACSHA256
     * HMACSHA384
     * HMACSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacSHA1";
    
    /**
      * Base64解密
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
    public static String encryptBASE64(byte[] key){
        return (new BASE64Encoder()).encodeBuffer(key);
    }
    
   /**
     * MD5加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
    */
    public static String encryptMD5(String data) throws NoSuchAlgorithmException{
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data.getBytes());
        
        return new String(md5.digest());
    }
    
    /**
      * SHA加密
      * @param data
      * @return
      * @throws NoSuchAlgorithmException
     */
    public static String encryptSHA1(String data) throws NoSuchAlgorithmException{
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data.getBytes());
        
        return new String(sha.digest());
    }
    
    /**
      * 初始化HMAC密钥
      * @return
      * @throws NoSuchAlgorithmException
     */
    public static String initHacKey() throws NoSuchAlgorithmException{
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
    public static String encryptHMAC(String data, String key) throws IOException, NoSuchAlgorithmException, InvalidKeyException{
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        
        return new String(mac.doFinal(data.getBytes()));
    }
}
