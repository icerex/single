package com.teamlinking.single.uitl;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by admin on 16/4/8.
 */
public class SecurityUtil {

    /**
     * 生成token
     *
     * @param s
     * @return
     */
    public static String generateTokon(String s) {
        return md5(s + UUID.randomUUID().toString());
    }

    /**
     * 生成key
     *
     * @return
     */
    public static String generateKey() {
        return md5(UUID.randomUUID().toString());
    }

    /**
     * 静态方法，便于作为工具类
     *
     * @param plainText
     */
    public static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkSign(String sign,long timestamp,String salt){
        long now = System.currentTimeMillis();
        if (timestamp < now+10*60*1000 && timestamp > now-10*60*1000){
            String s = SecurityUtil.md5(salt+timestamp);
            if (s.equals(sign)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(md5("134123412342134123421341234231412341234132"));
        System.out.println(generateTokon("18668181767"));
        String key = "35aeaf45de2a5c5734e6885d852eb812";
        System.out.println(key);
        long now = System.currentTimeMillis();
        System.out.println(now);
        String sign = md5(key + now);
        System.out.println(sign);
        System.out.println(checkSign(sign,now,key));

    }
}
