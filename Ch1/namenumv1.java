/*
ID: monghim1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class namenumv1 {

    static String[][] keypad = {
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I"},
            {"J", "K", "L"},
            {"M", "N", "O"},
            {"P", "R", "S"},
            {"T", "U", "V"},
            {"W", "X", "Y"},
    };
    static String[] dict;
    static int dictLen;
    public static ArrayList<String> validNames = new ArrayList<>();

    public static void generateValidNames(String processed, int dictFromIndex, String serialNum){
        if(serialNum.length() == 0){
            int index = Arrays.binarySearch(dict, dictFromIndex, dictLen, processed);
            if(index >= 0){
                validNames.add(processed);
            }
        }
        else{
            int num = Integer.parseInt(serialNum.substring(0, 1)) - 2;
            for(String possibleLetter: keypad[num]){
                String possibleWord = processed + possibleLetter;
                int index = Arrays.binarySearch(dict, dictFromIndex, dictLen, possibleWord);
                if(index < 0){
                    index = Math.abs(index + 1);
                }
                if(index >= dictLen || ! dict[index].startsWith(possibleWord)){
                    continue;
                }
                generateValidNames(possibleWord, index, serialNum.substring(1));
            }
        }
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));

        //reading from dict.txt
        dict = (new BufferedReader(new FileReader("dict.txt")).lines().toArray(String[]::new));
        dictLen = dict.length;

        //read serial code
        generateValidNames("", 0, f.readLine());
        if(validNames.size() == 0){
            out.println("NONE");
        }
        for(String name: validNames){
            out.println(name);
        }
        out.close();

    }
}
/**
 Test 1: TEST OK [0.187 secs, 490612 KB]
 Test 2: TEST OK [0.187 secs, 490612 KB]
 Test 3: TEST OK [0.187 secs, 523900 KB]
 Test 4: TEST OK [0.187 secs, 523900 KB]
 Test 5: TEST OK [0.187 secs, 490612 KB]
 Test 6: TEST OK [0.187 secs, 523900 KB]
 Test 7: TEST OK [0.187 secs, 523900 KB]
 Test 8: TEST OK [0.187 secs, 490612 KB]
 Test 9: TEST OK [0.180 secs, 523900 KB]
 Test 10: TEST OK [0.187 secs, 523900 KB]
 Test 11: TEST OK [0.180 secs, 523900 KB]
 Test 12: TEST OK [0.187 secs, 523900 KB]
 Test 13: TEST OK [0.187 secs, 523900 KB]
 Test 14: TEST OK [0.180 secs, 523900 KB]
 Test 15: TEST OK [0.180 secs, 490612 KB]
 */
