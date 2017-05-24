package tv.danmaku.bili.api;

/**
 * Created by zhu on 2017/5/22.
 */

public class LibBili {
    static {
        System.loadLibrary("bili");
    }

    public String getnativeGetAppKey(String paramString) {
        return nativeGetAppKey(paramString);
    }

    public byte[] getNativeGetAppSecret(String paramString) {
        return nativeGetAppSecret(paramString);
    }

    private native String nativeGetAppKey(String paramString);

    private native byte[] nativeGetAppSecret(String paramString);

    private native int nativeGetCpuCount();

    private native int nativeGetCpuId();
}
