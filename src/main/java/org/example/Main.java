package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        System.out.println("\nThe result:");
        String binary = decodeChuckNorris(input);
        char[] binaryChars = decodeBinarytoASCII(binary);
        System.out.println(String.valueOf(binaryChars));
    }

    public static String decodeChuckNorris(String chuckNorris){
        StringBuilder result = new StringBuilder();

        String[] arr = chuckNorris.split(" ");

        for (int i = 0; i < arr.length; i+= 2){
            String literal;
            if (arr[i].length() == 1) {
                literal = "1";
            } else {
                literal = "0";
            }
            result.append(literal.repeat(arr[i+1].length()));
        }
        return result.toString();
    }

    public static char[] decodeBinarytoASCII(String binary){
        char[] result = new char[binary.length() / 7];
        for (int i = 0; i < binary.length(); i += 7){
            result[i/7] = (char) Integer.parseInt(binary.substring(i, i + 7), 2);
        }
        return result;
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