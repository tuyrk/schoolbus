package com.cdutcm.test;

import java.util.Random;

public class RanddomTest {
    public static void main(String[] args) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        System.out.println(code);
    }
}
