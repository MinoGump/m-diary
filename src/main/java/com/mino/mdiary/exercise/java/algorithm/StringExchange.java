package com.mino.mdiary.exercise.java.algorithm;

import org.junit.Test;

public class StringExchange {

    @Test
    public void test() {
        String s = "abcdefg";
        int n = 3;

        String newString = exchangeWord(s, n);
        System.out.println(newString);

        String newString1 = exchangeWord1(s, n);
        System.out.println(newString1);

        String s1 = exchange1(s);
        System.out.println(s1);

        char[] chars = s.toCharArray();
        exchange(chars, 0, s.length()-1);
        System.out.println(chars);
    }

    // 基本方法   abcdefg, 3 =>  defgabc
    private String exchangeWord(String s, int n) {
        StringBuilder result = new StringBuilder();
        for (int i = n; i < s.length(); ++i) {
            result.append(s.charAt(i));
        }
        for (int i = 0; i < n; ++i) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    // 单词顺序翻转 ，可以理解为每个单词顺序翻转后，再全局翻转字符串。  abc defgh => cba hgfde => edfghabc
    private String exchangeWord1(String s, int n) {
        String s1 = s.substring(0, n);
        String s2 = s.substring(n);
        return exchange1(exchange1(s1) + exchange1(s2));
    }

    // 利用异或交换两个字符  Hello => olleH
    private String exchange1(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] chars = s.toCharArray();
        int length = s.length()-1;
        for (int i = 0; i < (length + 1) / 2; ++i) {
            chars[i] ^= chars[length-i];
            chars[length-i] ^= chars[i];
            chars[i] ^= chars[length-i];
        }
        return new String(chars);
    }

    private void exchange(char[] chars, int begin, int end) {
        while (begin < end) {
            swap(chars, begin, end);
            begin++;
            end--;
        }
    }

    private void swap(char[] chars, int n, int m) {
        if (n == m) return;
        chars[n] ^= chars[m];
        chars[m] ^= chars[n];
        chars[n] ^= chars[m];
    }
}
