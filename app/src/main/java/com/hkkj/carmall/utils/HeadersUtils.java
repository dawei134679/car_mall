package com.hkkj.carmall.utils;

import com.hkkj.carmall.MyApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by 李大为 on 2019/7/11.
 */

public class HeadersUtils {

    public static Map<String,String> getHeaders(Map<String,String> params){
        long timeStamp = System.currentTimeMillis();
        String ytoken = UtilSharedPreference.getStringValue(MyApplication.getInstance().getApplicationContext(), Config.TOKEN);
        String signStr;
        //生成签名
        if(params == null){
            signStr = "os=" + 1 +"&timestamp=" + timeStamp + "&version=" + 1.0;
        }else{
            params.put("os",String.valueOf(1));
            params.put("timestamp",String.valueOf(timeStamp));
            params.put("version","1.0");
            signStr = genderParameterSortStr(params);
        }

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
    /**
     * 参数排序
     */
    private static String genderParameterSortStr(Map<String, String> params) {
        List<String> fieldNames = new ArrayList<String>(params.keySet());
        Collections.sort(fieldNames);
        StringBuffer buf = new StringBuffer();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = String.valueOf(params.get(fieldName));
            buf.append(fieldName).append("=").append(fieldValue);
            if (itr.hasNext()) {
                buf.append("&");
            }
        }
        return buf.toString();
    }
}
