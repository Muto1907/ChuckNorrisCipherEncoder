package org.example;

public class ChuckCodec {

    public static String encode(String s) {
        String binary = to7BitBinary(s);
        return binaryToUnary(binary);
    }

    public static boolean tryDecode(String s, StringBuilder out) {
        return true;
    }

    private static String to7BitBinary(String s){
        StringBuilder binary = new StringBuilder();
        for (char c : s.toCharArray()){
            binary.append(String.format("%7s", Integer.toBinaryString(c)).replace(" ", "0"));
        }
        return binary.toString();
    }

    private static String binaryToUnary(String bin){
        return "";
    }

    private static boolean isZerosAndSpaces(String s){
        return false;
    }

    private static String unaryToBinary(String unary){
        return "";
    }
    private static String binaryToAscii(String bin){
        return "";
    }
}
