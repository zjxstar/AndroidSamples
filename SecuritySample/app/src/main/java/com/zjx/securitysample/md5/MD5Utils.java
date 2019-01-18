package com.zjx.securitysample.md5;

import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 通过MessageDigest实现计算MD5
 */
public class MD5Utils {

    private static final String ALGORITHM_MD5 = "MD5";

    /**
     * 计算MD5
     *
     * @param str 待计算字符串
     * @return 32位小写md5字符串
     */
    public static String getMD5ForStr(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                MessageDigest md5 = MessageDigest.getInstance(ALGORITHM_MD5);
                md5.update(str.getBytes());
                byte[] md5Bytes = md5.digest(); // 16字节，128位
                return toHex(md5Bytes);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("the str is empty");
        }
        return "";
    }

    /**
     * 计算流的MD5
     * @param inputStream 文件流
     * @return 32位小写md5字符串
     */
    public static String getMD5ForFile(InputStream inputStream) {
        DigestInputStream dis = null;

        byte[] buffer = new byte[1024];

        try {
            MessageDigest md5 = MessageDigest.getInstance(ALGORITHM_MD5);
            dis = new DigestInputStream(inputStream, md5);
            while (dis.read(buffer) > 0); // 相当于MessageDigest的update
            md5 = dis.getMessageDigest();
            return toHex(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    /**
     * 转成16进制
     *
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int len = bytes.length;
        char str[] = new char[len * 2]; // 32
        int k = 0;
        for (int i = 0; i < len; i++) {
            byte byteTemp = bytes[i];
            str[k++] = hexDigits[byteTemp >>> 4 & 0xf]; // 前四位
            str[k++] = hexDigits[byteTemp & 0xf]; // 后四位
        }
        return new String(str);
    }

}
