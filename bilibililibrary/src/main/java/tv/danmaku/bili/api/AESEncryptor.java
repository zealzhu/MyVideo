package tv.danmaku.bili.api;

/**
 * Created by zhu on 2017/5/23.
 */


import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * AES加密类
 */
public final class AESEncryptor
{
    /**
     * 加密
     * @param paramSecretKey 密钥
     * @param paramIvParameterSpec
     * @param paramArrayOfByte
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] encryption(SecretKey paramSecretKey, IvParameterSpec paramIvParameterSpec, byte[] paramArrayOfByte)
            throws GeneralSecurityException {
        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(1, paramSecretKey, paramIvParameterSpec);
        return localCipher.doFinal(paramArrayOfByte);
    }

    /**
     * 解密
     * @param paramSecretKey 私钥
     * @param paramIvParameterSpec 随机源
     * @param paramArrayOfByte  加密的字符串，要先用Base64解码
     * @return
     * @throws GeneralSecurityException
     */
    public static byte[] decode(SecretKey paramSecretKey, IvParameterSpec paramIvParameterSpec, byte[] paramArrayOfByte)
            throws GeneralSecurityException{

        Cipher localCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        localCipher.init(Cipher.DECRYPT_MODE, paramSecretKey, paramIvParameterSpec);
        return localCipher.doFinal(paramArrayOfByte);//解密的字符串
    }
}
