package com.dermai.common.Utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author Shaobo
 */
public class AddressUtils {
    public static String getRealAddressByIP(String ip)
    {
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "Internal IP";
        }
//        if (RuoYiConfig.isAddressEnabled())
//        {
//            try
//            {
//                String rspStr = HttpUtils.sendGet(IP_URL, "ip=" + ip + "&json=true", Constants.GBK);
//                if (StringUtils.isEmpty(rspStr))
//                {
//                    log.error("获取地理位置异常 {}", ip);
//                    return UNKNOWN;
//                }
//                JSONObject obj = JSON.parseObject(rspStr);
//                String region = obj.getString("pro");
//                String city = obj.getString("city");
//                return String.format("%s %s", region, city);
//            }
//            catch (Exception e)
//            {
//                log.error("获取地理位置异常 {}", ip);
//            }
//        }
        return "UNKNOWN";
    }
}
