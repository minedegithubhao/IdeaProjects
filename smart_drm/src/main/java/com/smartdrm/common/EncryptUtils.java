package com.smartdrm.common;

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
 * @date 2023/12/27 17:14
 */
public class EncryptUtils {

    /**前端AES加密算法/工作模式/填充方式*/
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    /**前端AES加密密钥*/
    private static final String SMART_KCS_5_PADDING = "smartKCS5padding";

    /**
     * 针对前端AES加密进行解密
     * @param encryptStr 加密串
     * @return 解密后的字符串
     */
    public static String AESDecrypt(String encryptStr) throws OurException{

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
            throw new OurException("解密失败!");
        }
    }
}
