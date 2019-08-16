package com.example.tapai.common;

import java.util.Random;

public class CommonHelper {

    /**
     * @param nlenght 随机数长度
     * @return 返回随机数
     */
    public static String GenerateNumber(int nlenght) {
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < nlenght; i++) {
            int index = random.nextInt(array.length);
            buffer.append(array[index]);
        }
        return buffer.toString();
    }
}
