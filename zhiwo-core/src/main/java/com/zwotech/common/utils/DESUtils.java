package com.zwotech.common.utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * Created by szsonic 2017/2/24.
 */
public class DESUtils {
    private static Key key;
    private static String KEY_STR="我是密钥";
    //这个密钥的值可以随意定义，但是加密和解密的密钥要一致
    static{
        try
        {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对字符串进行加密，返回BASE64的加密字符串
     * @param str 加密前字符串
     * @return 加密后字符串
     */
    public static String getEncryptString(String str){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        System.out.println(key);
        try
        {
            byte[] strBytes = str.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64Encoder.encode(encryptStrBytes);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对BASE64加密字符串进行解密
     * @param str 待解密的字符串
     * @return 解密后的字符串
     */
    public static String getDecryptString(String str){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try
        {
            byte[] strBytes = base64Decoder.decodeBuffer(str);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return new String(encryptStrBytes,"UTF-8");
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }


    public static void main(String[] args)
    {
        String name ="root";
        String password="123456";
        //测试数据
        String encryname = getEncryptString(name);
        String encrypassword = getEncryptString(password);
        System.out.println(encryname);//加密后用户名
        System.out.println(encrypassword);//加密后密码

        System.out.println(getDecryptString(encryname));//解密后用户名
        System.out.println(getDecryptString(encrypassword));//解密后密码
    }
}