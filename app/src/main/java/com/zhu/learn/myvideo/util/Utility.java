package com.zhu.learn.myvideo.util;

import com.google.gson.Gson;
import com.zhu.learn.myvideo.Application;
import com.zhu.learn.myvideo.bean.BibiliRecommendVideoInfo;
import com.zhu.learn.myvideo.bean.BilibiliSearchVideoInfo;
import com.zhu.learn.myvideo.bean.BilibiliVideoFileInfo;
import com.zhu.learn.myvideo.bean.BilibiliVideoInfo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by zhu on 2017/3/25.
 */

public class Utility {

    public static List<BibiliRecommendVideoInfo.Data> handleRecommendResponse(String response) {
        Gson gson = new Gson();
        BibiliRecommendVideoInfo info = gson.fromJson(response, BibiliRecommendVideoInfo.class);
        return info.getData();
    }

    public static BilibiliVideoFileInfo handleVideoFileInfoResponse(InputStream inputStream) {
        BilibiliVideoFileInfo bilibiliVideoFileInfo = null;
        try {
            bilibiliVideoFileInfo = XMLUtil.parseVideoFileInfoXML(inputStream);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return bilibiliVideoFileInfo;
    }

    public static BilibiliVideoInfo handleVideInfoResponse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, BilibiliVideoInfo.class);
    }

    public static BilibiliSearchVideoInfo handleSearchVideoInfo(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, BilibiliSearchVideoInfo.class);
    }

    public static String getSign(String cid) {
        String str = "appkey=" + Application.APP_KEY + "&cid=" + cid + Application.SECRET;
        return EncryptionUtil.getMD5String(str);
    }
}
