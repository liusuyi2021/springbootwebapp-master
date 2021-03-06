package org.synjones.springframework.util;

public class UnicodeUtil {
	 //Unicode转中文方法
    public static String unicodeToCn(String unicode) {
        /** 以 \ u 分割，因为java注释也能识别unicode，因此中间加了一个空格*/
       // String[] strs = unicode.split("\\\\u");
        String[] strs = unicode.split("u");
        String returnStr = "";
        // 由于unicode字符串以 \ u 开头，因此分割出的第一个字符是""。
        for (int i = 1; i < strs.length; i++) {
            returnStr += (char) Integer.valueOf(strs[i], 16).intValue();
        }
        return returnStr;
    }
 
    //中文转Unicode
    private static String cnToUnicode(String cn) {
        char[] chars = cn.toCharArray();
        String returnStr = "";
        for (int i = 0; i < chars.length; i++) {
            returnStr += "\\u" + Integer.toString(chars[i], 16);
        }
        return returnStr;
    }
    //测试
    public static void main(String[] args) {
        //Unicode码
        String aa = "u006fu0020u0068";
        //转中文
        String cnAa = unicodeToCn(aa);
        System.out.println("Unicode转中文结果： "+cnAa); //转Unicode
        String unicodeAa = cnToUnicode(cnAa);
        System.out.println("中文转Unicode结果： "+unicodeAa);
    }
 
}
