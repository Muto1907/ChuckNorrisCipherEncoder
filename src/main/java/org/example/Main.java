package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input string:");
        String input = scanner.nextLine();
        System.out.println("\nThe result:");

        StringBuilder binary = new StringBuilder();
        for (char c : input.toCharArray()){
            binary.append(String.format("%7s",
                    Integer.toBinaryString(c)).replace(" ", "0"));
        }

        System.out.println(encodeToChuckNorris(binary.toString()));
    }
    public static String encodeToChuckNorris(String binary){
        StringBuilder result = new StringBuilder();
        int i = 0;
        while(i < binary.length()){
            if(binary.charAt(i) == '0'){
                result.append("00 0");
            } else if (binary.charAt(i) == '1'){
                result.append("0 0");
            }
            i++;
            while (i < binary.length() && binary.charAt(i-1) == binary.charAt(i)){
                result.append("0");
                i++;
            }
            if (i < binary.length()){
                result.append(" ");
            }
        }
        return result.toString();
    }
}