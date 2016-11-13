package Ch1.Section2;/*
ID: monghim1
LANG: JAVA
TASK: Ch1.Section2.dualpal
*/
import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class dualpal {

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

    public static boolean isDualPal(int num){
        int isPalidromeForBase = 0;
        for(int base = 2; base < 11 && isPalidromeForBase < 2; base++){
            if(isSymmetrical(convertToBase(num, base))){
                isPalidromeForBase++;
            }
        }
        return isPalidromeForBase == 2;
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int needToFind = Integer.parseInt(st.nextToken());
        int begin = Integer.parseInt(st.nextToken());


        for(int found = 0, i = begin + 1; found < needToFind; i++){
            if(isDualPal(i)){
                out.println(i);
                found++;
            }
        }

        out.close();

    }
}

/*
performance was actually ok, i am surprised, i guess usaco training didn't give me any edge case
   Test 1: TEST OK [0.079 secs, 490612 KB]
   Test 2: TEST OK [0.079 secs, 523900 KB]
   Test 3: TEST OK [0.223 secs, 524924 KB]
   Test 4: TEST OK [0.079 secs, 490612 KB]
   Test 5: TEST OK [0.086 secs, 490612 KB]
   Test 6: TEST OK [0.259 secs, 523900 KB]
   Test 7: TEST OK [0.101 secs, 523900 KB]
 */

/* So i got curious about how fast a C solution runs, so i tried the analysis code given by the training page, and holy fuck!
   Test 1: TEST OK [0.000 secs, 2204 KB]
   Test 2: TEST OK [0.000 secs, 2204 KB]
   Test 3: TEST OK [0.000 secs, 2204 KB]
   Test 4: TEST OK [0.000 secs, 2204 KB]
   Test 5: TEST OK [0.000 secs, 2204 KB]
   Test 6: TEST OK [0.000 secs, 2204 KB]
   Test 7: TEST OK [0.000 secs, 2204 KB]
   Test 8: TEST OK [0.000 secs, 2204 KB]

 */