package cn.zhihang.cm.base.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
    public static final String KEY_SHA = "SHA-1";
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
     * @throws UnsupportedEncodingException 
     */
    public static String encryptSHA1(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data.getBytes("utf-8"));
        byte[] result = sha.digest();
        
        StringBuffer sb = new StringBuffer();
        
        for (byte b : result) {
            int i = b & 0xff;
            if (i < 0xf) {
                sb.append(0);
            }
            sb.append(Integer.toHexString(i));
        }
        
        return sb.toString();
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
        
        return new BigInteger(mac.doFinal(data.getBytes())).toString(16);
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IOException {
        String passSha = Coder.encryptSHA1("abcdef159357");
        System.out.println(passSha);
        System.out.println(Coder.encryptHMAC(passSha, "pD/Fbl7NwDolthkhNkyUKGNML9fta6mjfPSmqaHpStr531Z0E3RX1DDub5hJS2Z+Qri9Dz0bnW4OR2vVNf/oow=="));
    }
}
