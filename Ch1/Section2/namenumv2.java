package Ch1;/*
ID: monghim1
LANG: JAVA
TASK: namenum
*/
import java.io.*;
import java.math.BigInteger;

class namenumv2 {

    public static BigInteger convertToSerialNum(String name){
        BigInteger serialNum = BigInteger.ZERO;
        for(int i = 0; i < name.length(); i++){
            int digit = name.charAt(i);
            if(digit == 'Q' || digit == 'Z'){
                return  BigInteger.valueOf(-1);
            }
            else if(digit < 'Q'){
                digit = 2 + (digit - 'A') / 3;
            }
            else{
                digit = 2 + (digit - 'A' - 1) / 3;
            }
            serialNum = serialNum.multiply(BigInteger.TEN).add(BigInteger.valueOf(digit));
        }
        return serialNum;
    }

    public static int count = 0;
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
        BigInteger targetSerialNum = new BigInteger(f.readLine());

        //reading from dict.txt
        (new BufferedReader(new FileReader("dict.txt"))
            .lines()
            .filter(s -> convertToSerialNum(s).equals(targetSerialNum)))
            .forEachOrdered(s -> {
                count++;
                out.println(s);
            });
        if(count== 0){
            out.println("NONE");
        }

        out.close();

    }
}
/**Before using BigInteger
*Test 1: TEST OK [0.187 secs, 523900 KB]
*Test 2: TEST OK [0.194 secs, 523900 KB]
 */

//We see a major performance hit by using BigInteger

/**After using BigInteger
//Test 1: TEST OK [0.223 secs, 523900 KB]
//Test 2: TEST OK [0.223 secs, 523900 KB]
//Test 3: TEST OK [0.230 secs, 523900 KB]
//Test 4: TEST OK [0.216 secs, 523900 KB]
//Test 5: TEST OK [0.223 secs, 523900 KB]
//Test 6: TEST OK [0.223 secs, 523900 KB]
//Test 7: TEST OK [0.216 secs, 523900 KB]
//Test 8: TEST OK [0.216 secs, 523900 KB]
//Test 9: TEST OK [0.223 secs, 523900 KB]
//Test 10: TEST OK [0.230 secs, 523900 KB]
//Test 11: TEST OK [0.216 secs, 523900 KB]
//Test 12: TEST OK [0.223 secs, 523900 KB]
//Test 13: TEST OK [0.223 secs, 490612 KB]
//Test 14: TEST OK [0.216 secs, 490612 KB]
//Test 15: TEST OK [0.223 secs, 523900 KB]
 */