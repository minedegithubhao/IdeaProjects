package com.smartdrm.entity.common;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author cxdpc
 * @date 2023/12/27 13:30
 * @description 加解密工具类
 */
public class AESUtils {

    /**加解密算法/工作模式/填充方式*/
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**前端AES加密密钥*/
    private static final String SMART_KCS_5_PADDING = "smartKCS5padding";
    /**用户密码密钥*/
    public static final String PASSWORD = "smartdrm5padding";
    /**编码*/
    private static final String ENCODING = "UTF-8";
    /**算法定义*/
    private static final String AES_ALGORITHM = "AES";
    /**指定填充方式*/
    private static final String CIPHER_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 针对前端AES加密进行解密
     * @param encryptStr 加密串
     * @return 解密后的字符串
     */
    public static String AESDecrypt(String encryptStr){

        try {
            byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
            SecretKeySpec key = new SecretKeySpec(SMART_KCS_5_PADDING.getBytes(), "AES");
            IvParameterSpec iv = new IvParameterSpec(SMART_KCS_5_PADDING.getBytes());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] decryptBytes = cipher.doFinal(encryptByte);
            return new String(decryptBytes);
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | NoSuchPaddingException |
                 InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES加密
     * @param content 待加密内容
     * @param aesKey  密码
     * @return 字节数组
     */
    public static byte[] encrypt(String content, String aesKey){
        if(StringUtils.isBlank(content)){
            return null;
        }
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(ENCODING);
                //设置加密算法，生成秘钥
                SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
                //选择加密
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                //根据待加密内容生成字节数组
                byte[] encrypted = cipher.doFinal(content.getBytes(ENCODING));
                //返回base64字符串
                return cipher.doFinal(content.getBytes(ENCODING));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param content 待解密内容
     * @param aesKey  密码
     * @return 字节数组
     */
    public static byte[] decrypt(byte[] content, String aesKey){
        //判断秘钥是否为16位
        if(StringUtils.isNotBlank(aesKey) && aesKey.length() == 16){
            try {
                //对密码进行编码
                byte[] bytes = aesKey.getBytes(ENCODING);
                //设置解密算法，生成秘钥
                SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
                // "算法/模式/补码方式"
                Cipher cipher = Cipher.getInstance(CIPHER_PADDING);
                //选择解密
                cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

                //根据待解密内容进行解密
                return cipher.doFinal(content);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else {
            return null;
        }
    }

    /**
     * 将字符串加密
     * @param content 内容
     * @return 16进制密文
     */
    public static String getEncryptString(String content) {
        byte[] encrypt = encrypt(content, PASSWORD);
        return parseByteToHexStr(encrypt);
    }

    /**
     *  将16进制密文解密
     * @param content 16进制密文
     * @return 字符串
     */
    public static String getDecryptString(String content) {
        byte[] decrypt = decrypt(parseHexStrToByte(content), PASSWORD);
        return new String(decrypt);
    }

    /**
     * 字节数组转化为字符串
     * @param bytes 字节数组
     * @return 16进制字符串
     */
    public static String parseByteToHexStr(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) {
            buf.append(String.format("%02x", b & 0xff));
        }
        return buf.toString();
    }

    /**
     *  16进制字符串转化为字节数组
     * @param hexStr 16进制字符串
     * @return 字节数组
     */
    public static byte[] parseHexStrToByte(String hexStr) {
        if(StringUtils.isEmpty(hexStr)) {
            return new byte[0];
        }

        byte[] bytes = new byte[hexStr.length() / 2];
        for(int i = 0; i < hexStr.length() / 2; i++) {
            String subStr = hexStr.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }
}
