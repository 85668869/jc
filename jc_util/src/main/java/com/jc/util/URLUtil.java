/**
 * Created by jingchun.zhang on 2017/10/23.
 */
package com.jc.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jc
 * @version 1.0.0
 * @date 2017/10/23
 */
public class URLUtil {

    /**
     *  根据url截取ip后面的路径
     * @param url 要处理的url
     * @return
     *
     * <br/>示例：
     * <br/> http://192.168.0.16/qq/a/1.jpg  ==>  /qq/a/1.jpg
     * <br/> http://192.168.0.16:8080/qq/a/1.jpg  ==>   /qq/a/1.jpg
     * <br/> https://www.baidu.com/haha/ss/qwe.png   ==>   /haha/ss/qwe.png
     */
    public static String subPicPath(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        String str = "";
        //使用正则表达式过滤，
        String reg = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);

        String[] split2 = url.split(reg);
        if (split2.length > 1) {
            String substring = url.substring(url.length() - split2[1].length(), url.length());
            str = substring;
        }

        return str;
    }

    public static void main(String[] args) {
        System.out.println(subPicPath("http://192.168.0.16/qq/a/1.jpg"));
        System.out.println(subPicPath("http://192.168.0.16:8080/qq/a/1.jpg"));
        System.out.println(subPicPath("https://www.baidu.com/haha/ss/qwe.png"));
        System.out.println(subPicPath("http://www.img.com/item/d54e0f3564af4acc91ed3cef28feda16.jpg"));
    }
}
