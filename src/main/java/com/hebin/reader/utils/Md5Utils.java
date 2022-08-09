package com.hebin.reader.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
    public static String md5Digest(String sourcr,Integer salt){
        char[] chars = sourcr.toCharArray();
        for (int i = 0; i <chars.length ; i++) {
            chars[i] = (char) (chars[i]+salt);
        }
        String s = new String(chars);
        String s1 = DigestUtils.md5Hex(s);
        return s1;

    }
}
