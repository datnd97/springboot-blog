package com.kelegele.blog.util;

import com.alibaba.fastjson.JSONObject;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: blog-4
 * @description: 工具类
 * @author: FelixHuang
 * @create: 2018-11-25 18:23
 **/
public class blogUtil {

    public static Map<String, String> categoryMap;

    static {
        categoryMap = new HashMap<>();
        categoryMap.put("Java", "Java");
        categoryMap.put("Web", "Web");
        categoryMap.put("Linux", "Linux");
        categoryMap.put("分布式系统", "Distributed");
        categoryMap.put("数据库", "Database");
        categoryMap.put("算法", "Algorithm");
        categoryMap.put("其它", "Other");
    }

    private static final Logger logger = LoggerFactory.getLogger(blogUtil.class);

    public static String[] categorys = {"Java", "Web", "Linux", "分布式系统", "数据库", "算法", "其它"};
    public static int ANONYMOUS_USER_ID = 3;

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }

    public static String getJSONString(int code) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        return json.toJSONString();
    }

    public static String MD5(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            logger.error("生成MD5失败", e);
            return null;
        }
    }

    public static String tranfer(String content) {
        MutableDataSet options = new MutableDataSet();
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(content);
        return renderer.render(document);
    }

    public static String dateParser(Date dt, String format) {

        String formatDate = "null";

        if (format.equals("yyyy-MM-dd")) {
            //格式：2016-7-6
            formatDate = DateFormat.getDateInstance().format(dt);
            return formatDate;
        }


//      //格式：2016年7月6日 星期三
//      formatDate = DateFormat.getDateInstance(DateFormat.FULL).format(dt);
//      System.out.println(formatDate);
//
        if (format.equals("yyyy-MM-dd HH:mm:ss")) {
            //格式 24小时制：2016-07-06 09:39:58
            DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //HH表示24小时制；
            formatDate = dFormat.format(dt);
            return formatDate;
        }

//
//      //格式12小时制：2016-07-06 09:42:44
//      DateFormat dFormat12 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //hh表示12小时制；
//      formatDate = dFormat12.format(dt);
//      System.out.println(formatDate);
//
//      //格式去掉分隔符24小时制：20160706094533
//      DateFormat dFormat3 = new SimpleDateFormat("yyyyMMddHHmmss");
//      formatDate = dFormat3.format(dt);
//      System.out.println(formatDate);
//
//      //格式转成long型：1467770970
//      long lTime = dt.getTime() / 1000;
//      System.out.println(lTime);
//
//      //格式long型转成Date型，再转成String：  1464710394 -> ltime2*1000 -> 2016-05-31 23:59:54
//      long ltime2 = 1464710394;
//      SimpleDateFormat lsdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      Date lDate = new Date(ltime2*1000);
//      String lStrDate = lsdFormat.format(lDate);
//      System.out.println(lStrDate);
//
//      //格式String型转成Date型：2016-07-06 10:17:48 -> Wed Jul 06 10:17:48 CST 2016
//      String strDate = "2016-07-06 10:17:48";
//      SimpleDateFormat lsdStrFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      try {
//        Date strD = lsdStrFormat.parse(strDate);
//        System.out.println(strD);
//      } catch (ParseException e) {
//        e.printStackTrace();
//      }

        return formatDate;

    }

    public static String toStr(List<String> strA) {
        String str = null;

        for (String s : strA) {
            if (str == null) {
                str = s;
            } else {
                str = String.join(",", str, s);
            }

        }
        return str;
    }

    public static List<String> toStrList(String str) {
        List<String> lists = new ArrayList<String>();
        String[] strA = str.split(",");
        for (String s : strA) {
            lists.add(s);
        }
        return lists;
    }


}
