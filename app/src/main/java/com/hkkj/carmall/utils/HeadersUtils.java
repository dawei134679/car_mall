package com.hkkj.carmall.utils;

import com.hkkj.carmall.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李大为 on 2019/7/11.
 */

public class HeadersUtils {

    public static Map<String,String> getHeaders(){
        long timeStamp = System.currentTimeMillis();
        String ytoken = UtilSharedPreference.getStringValue(MyApplication.getInstance().getApplicationContext(), Config.TOKEN);
        //生成签名
        String signStr = "os=" + 1 +"&timestamp=" + timeStamp + "&version=" + 1.0;
        String sign = MD5.MD(MD5.MD(ytoken + ":" + signStr));
        //加密token
        String ytokenStr = ytoken + "_" + timeStamp;
        String token = AesUtil.aesEncryptString(ytokenStr);
        //组合headers参数
        HashMap<String, String> headers = new HashMap<>();
        headers.put("os","1");
        headers.put("version","1.0");
        headers.put("token",token);
        headers.put("timestamp",String.valueOf(timeStamp));
        headers.put("sign",sign);
        return headers;
    }

}
