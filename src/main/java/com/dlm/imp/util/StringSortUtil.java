package com.dlm.imp.util;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class StringSortUtil {
	public static int getNum(String sb){
        if(sb == null){
            return 0;
        }
        if("".equals(sb)){
            return 0;
        }
        return Integer.valueOf(sb);
    }

    public static boolean isNum(char c){
        boolean b = false;
        if(c >= '0' && c <= '9'){
            b = true;
        }
        return b;
    }

    public static boolean isChar(char c){
        boolean b = false;
        if((c >= 'a' && c <= 'z' )|| ( c >= 'A' && c <= 'Z')){
            b = true;
        }
        return b;
    }

    /**
     * 比较两个包含数字的字符串
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 返回比较结果
     */
    public static int compareTo(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int lim = Math.min(len1, len2);
        char v1[] = s1.toCharArray();
        char v2[] = s2.toCharArray();
        char int1[] = null;
        char int2[] = null;

        int k = 0;
        // 外层循环用于遍历两个字符串
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            // 如果遍历到两个字符串相同位置都是数字, 那么就要去把这个数字后面连续的数字都挖出来
            if(isNum(c1) && isNum(c2)){
                int1 = new char[len1 - k];
                int2 = new char[len2 - k];
                int count1 = 0;
                int count2 = 0;
                int n1,n2;

                //此循环用于挖出 s1 数字, 依次放到预先的int1[] 数组中
                for (int i=k;i<len1;i++){
                    if(isNum(v1[i])){
                        int1[count1] = v1[i];
                    }else {
                        break;
                    }
                    count1++;
                }

                //此循环用于挖出 s2 的数字, 依次放到预先的int2[] 数组中
                for (int i=k;i<len2;i++){
                    if(isNum(v2[i])){
                        int2[count2] = v2[i];
                    }else {
                        break;
                    }
                    count2++;
                }

                n1 =getNum(String.valueOf(int1).trim());
                n2 =getNum(String.valueOf(int2).trim());

                // 需要判断n1 和 n2 是否相等, 如果相等, 则先把 k 移位, 然后continue
                if(n1 == n2){
                    k += int1.length;
                    continue;
                }

                //如果不相等, 那么就成功得到了想要的结果
                return n1 - n2;
            }

            // 如果遍历的两个字符不是全为数字的话, 就直接比较, 得出结果
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }

    @Test
    public void test(){
        String s1 = "IMG_5328.jsp";
        String s2 = "IMG_542.jsp";
        String s3 = "IMG_543.jsp";
        String s4 = "IMG_5331.jsp";
        String s5 = "IMG_5339.jsp";
        String s6 = "IMG_5341.jsp";
        String s7 = "IMG_1328.jsp";
//        List<String> jj= new 
        
        Set<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return compareTo(o1, o2);   //设计的功能方法
                //return o1.compareTo(o2);  //可以忽略的方法
            }
        });
        set.add(s1);
        set.add(s2);
        set.add(s3);
        set.add(s4);
        set.add(s5);
        set.add(s6);
        set.add(s7);
        System.out.println(set);
    }
}

