package org.example;

import java.util.Scanner;

public class Main {
    static final String MSG_PROMPT = "Please input operation (encode/decode/exit):";
    static final String MSG_INPUTSTR = "Input string:";
    static final String MSG_INPUTENCSTR = "Input encoded string:";
    static final String MSG_ENCODEDSTR = "Encoded string:";
    static final String MSG_DECODEDSTR = "Decoded string:";
    static final String MSG_INVALID = "Encoded string is not valid.";
    static final String MSG_BYE = "Bye!";
    static final String ENCODE = "encode";
    static final String DECODE = "decode";
    static final String EXIT = "exit";

    public static String noSuchOperation(String operation){
        return "There is no '" + operation + "' operation";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true){
            System.out.println(MSG_PROMPT);
            String operation = scanner.nextLine().trim();
            switch (operation){
                case ENCODE:
                    System.out.println(MSG_INPUTSTR);
                    input = scanner.nextLine();
                    System.out.println(MSG_ENCODEDSTR);
                    System.out.println(ChuckCodec.encode(input));
                    break;
                case DECODE:
                    StringBuilder out = new StringBuilder();
                    System.out.println(MSG_INPUTENCSTR);
                    if(ChuckCodec.tryDecode(scanner.nextLine(), out)){
                        System.out.println(MSG_DECODEDSTR);
                        System.out.println(out.toString());
                    } else {
                        System.out.println(MSG_INVALID);
                    }
                    break;
                case EXIT:
                    System.out.println(MSG_BYE);
                    scanner.close();
                    return;
                default:
                    System.out.println(noSuchOperation(operation));
            }
            System.out.println();
        }
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

}