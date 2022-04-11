package net.aeroclient.client.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ComputerUtils {
    public static String getHWID() {
        try {
            String s = "";
            String main = System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME") + System.getProperty("user.name").trim();
            byte[] bytes = new byte[0];

            bytes = main.getBytes("UTF-8");

            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] md5 = messageDigest.digest(bytes);
            int i = 0;
            for (byte b : md5) {
                s = s + Integer.toHexString(b & 0xFF | 0x300).substring(0, 3);
                if (i != md5.length - 1) {
                    s = s + "-";
                }
                ++i;
            }
            return s;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
