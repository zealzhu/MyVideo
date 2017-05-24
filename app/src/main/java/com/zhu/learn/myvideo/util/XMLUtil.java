package com.zhu.learn.myvideo.util;

import android.util.Xml;

import com.zhu.learn.myvideo.bean.BilibiliVideoFileInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 2017/3/28.
 */

public class XMLUtil {

    public static BilibiliVideoFileInfo parseVideoFileInfoXML(InputStream inputStream) throws XmlPullParserException, IOException {
        BilibiliVideoFileInfo bilibiliVideoFileInfo = null;
        List<BilibiliVideoFileInfo.Durl> durls = new ArrayList<>();
        BilibiliVideoFileInfo.Durl durl = null;
        List<String> backupUrls = null;
        boolean isSetUrlInDurl = false;
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream, "UTF-8");
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    //文档开始
                    bilibiliVideoFileInfo = new BilibiliVideoFileInfo();
                    break;
                case XmlPullParser.START_TAG:
                    String tagName;
                    switch (parser.getName()) {
                        case "result":
                            bilibiliVideoFileInfo.setResult(parser.nextText());
                            break;
                        case "timelength":
                            bilibiliVideoFileInfo.setTimelength(Long.parseLong(parser.nextText()));
                            break;
                        case "format":
                            bilibiliVideoFileInfo.setFormat(parser.nextText());
                            break;
                        case "accept_format":
                            bilibiliVideoFileInfo.setAcceptFormat(parser.nextText());
                            break;
                        case "accept_quality":
                            bilibiliVideoFileInfo.setAcceptQuality(parser.nextText());
                            break;
                        case "from":
                            bilibiliVideoFileInfo.setFrom(parser.nextText());
                            break;
                        case "seek_param":
                            bilibiliVideoFileInfo.setSeekParam(parser.nextText());
                            break;
                        case "seek_type":
                            bilibiliVideoFileInfo.setSeekType(parser.nextText());
                            break;
                        case "durl":
                            durl = bilibiliVideoFileInfo.new Durl();
                            durls.add(durl);
                            break;
                        case "order":
                            durl.setOrder(parser.nextText());
                            break;
                        case "length":
                            durl.setLength(Long.parseLong(parser.nextText()));
                            break;
                        case "size":
                            durl.setSize(Long.parseLong(parser.nextText()));
                            break;
                        case "url":
                            if(!isSetUrlInDurl) {
                                durl.setUrl(parser.nextText());
                                isSetUrlInDurl = true;
                            }
                            else
                                backupUrls.add(parser.nextText());
                            break;
                        case "backup_url":
                            backupUrls = new ArrayList<>();
                            durl.setBackupUrl(backupUrls);
                            break;
                        default:
                            break;
                    }
                    //标签开始
                    break;
                case XmlPullParser.END_TAG:
                    //标签结束
                    if("video".equals(parser.getName())) {
                        bilibiliVideoFileInfo.setDurls(durls);
                    } else if("durl".equals(parser.getName())) {
                        //已经设置完一个durl了
                        isSetUrlInDurl = false;
                    }
                    break;
                default:
                    break;
            }
            eventType = parser.next();
        }
        return bilibiliVideoFileInfo;
    }
}
