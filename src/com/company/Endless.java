package com.company;

import java.io.IOException;

public class Endless{
    public static void main(String[] args) throws IOException {
        while (true){
            int read = System.in.read();
            System.out.println("Input " + read);
            if (filter(read)){
                process(read);
            }
        }
    }

    private static void process(int arg) {
        if (Math.max(arg, 90)%2 == 0){
            System.out.println("!");
        }
    }

    private static boolean filter(int read) {
        return read != '\n' && read != 'a';
    }
}
