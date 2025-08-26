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
                        System.out.println(out);
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

}