package com.zxb.structurealgo.hashalgo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description TODO
 * Created by xuery on 2018/12/23.
 */
public class MD5Util {

    public static void main(String[] args) {
        String md5Result1 = hash("今天我来讲哈希算法","MD5");
        String md5Result2 = hash("今天我来讲哈希算法!","MD5");

        System.out.println(md5Result1.length());
        System.out.println(md5Result2);
    }

    /**
     *
     * @param source
     * @param hashType MD5 or SHA
     * @return
     */
    public static String hash(String source, String hashType) {
        StringBuilder sb = new StringBuilder();
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance(hashType);
            md5.update(source.getBytes());
            for (byte b : md5.digest()) {
                sb.append(String.format("%02X", b)); // 10进制转16进制，X 表示以十六进制形式输出，02 表示不足两位前面补0输出
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
