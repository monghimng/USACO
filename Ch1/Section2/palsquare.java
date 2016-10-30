/*
ID: monghim1
LANG: JAVA
TASK: palsquare
*/
import java.io.*;
import java.math.BigInteger;
import java.util.stream.IntStream;

class palsquare {

    public static boolean isSymmetrical(String s){
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static String convertNumToStringNum(int num){
        if(num < 10){
            return ""+num;
        }
        else{
            return Character.toString((char)(num - 10 + 'A'));
        }
    }

    public static String convertToBase(int num, int base){
        String numInBase = "";
        while(num > 0){
            int remainder = num % base;
            num = num / base;
            numInBase += convertNumToStringNum(remainder);
        }
        return new StringBuilder(numInBase).reverse().toString();
    }

    public static int count = 0;
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
        int base = Integer.parseInt(f.readLine());

        for(int i = 1; i < 301; i++){
            String numSquareBase = convertToBase((int)Math.pow(i, 2), base);
            if(isSymmetrical(numSquareBase)){
                out.println(convertToBase(i, base) + " " + numSquareBase);
            }
        }

        out.close();

    }
}

/* Much faster without using stream!
   Test 1: TEST OK [0.079 secs, 523900 KB]
   Test 2: TEST OK [0.094 secs, 523900 KB]
   Test 3: TEST OK [0.086 secs, 523900 KB]
   Test 4: TEST OK [0.086 secs, 523900 KB]
   Test 5: TEST OK [0.086 secs, 523900 KB]
   Test 6: TEST OK [0.079 secs, 490612 KB]
   Test 7: TEST OK [0.079 secs, 490612 KB]
   Test 8: TEST OK [0.086 secs, 490612 KB]
 */