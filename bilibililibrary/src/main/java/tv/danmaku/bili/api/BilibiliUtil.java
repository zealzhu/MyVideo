package tv.danmaku.bili.api;

import android.util.Base64;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zhu on 2017/5/22.
 */

public class BilibiliUtil {
    private static final byte[] a = "CBTgvMwMguJaPQMPoRjG".getBytes(Charset.forName("UTF-8"));
    private static final byte[] b = "PdAFucSj3CMM8oYSnULG".getBytes(Charset.forName("UTF-8"));

    public static String getAppKey() {
        LibBili bili = new LibBili();
        return bili.getnativeGetAppKey("android");
    }
    public static String getSecret() {
        LibBili bili = new LibBili();
        byte[] appSecret = bili.getNativeGetAppSecret("android");
        return secretDecode(appSecret);
    }

    private static String secretDecode(byte[] paramArrayOfByte)
    {
        if (paramArrayOfByte != null)
        {
            byte[] arrayOfByte = getSecretByte(a, b, Base64.decode(paramArrayOfByte, 0));
            if (arrayOfByte != null)
                return new String(arrayOfByte, Charset.forName("UTF-8"));
        }
        return null;
    }
    private static byte[] getSecretByte(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    {
        try {
            return AESEncryptor.decode(new SecretKeySpec(Arrays.copyOf(paramArrayOfByte1, 16), "AES"), new IvParameterSpec(Arrays.copyOf(paramArrayOfByte2, 16)), paramArrayOfByte3);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}
