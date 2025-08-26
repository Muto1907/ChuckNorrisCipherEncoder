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
        StringBuilder unary = new StringBuilder();
        int i = 0;
        while(i < bin.length()){
            if(bin.charAt(i) == '0'){
                unary.append("00 0");
            } else if (bin.charAt(i) == '1'){
                unary.append("0 0");
            }
            i++;
            while (i < bin.length() && bin.charAt(i-1) == bin.charAt(i)){
                unary.append("0");
                i++;
            }
            if (i < bin.length()){
                unary.append(" ");
            }
        }
        return unary.toString();
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
