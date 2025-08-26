package org.example;

public class ChuckCodec {

    public static String encode(String s) {
        String binary = to7BitBinary(s);
        return binaryToUnary(binary);
    }

    public static boolean tryDecode(String s, StringBuilder out) {
        if (!isEvenLength(s)){
            return false;
        }
        if (!isZerosAndSpaces(s)){
            return false;
        }
        String bin = unaryToBinary(s);
        if (bin == null){
            return false;
        }
        String result = binaryToAscii(bin);
        out.append(result);
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

    private static boolean isEvenLength(String s){
        return s.split(" ").length % 2 == 0;
    }

    private static boolean isZerosAndSpaces(String s){
        return s.matches("[ 0]+");
    }

    private static String unaryToBinary(String unary){
        StringBuilder result = new StringBuilder();
        String literal;
        String[] arr = unary.split(" ");
        for (int i = 0; i < arr.length; i += 2){
            if(arr[i].equals("0")){
                literal = "1";
            } else if (arr[i].equals("00")){
                literal = "0";
            } else {
                return null;
            }
            result.append(literal.repeat(arr[i+1].length()));
        }
        if (result.length() % 7 != 0){
            return null;
        }
        return result.toString();
    }

    private static String binaryToAscii(String bin){
        char[] result = new char[bin.length() / 7];
        for (int i = 0; i < bin.length(); i += 7){
            result[i/7] = (char) Integer.parseInt(bin.substring(i, i + 7), 2);
        }
        return String.valueOf(result);
    }
}
