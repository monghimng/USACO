/*
ID: monghim1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class crypt1 {

    static int[] ds;

    static boolean in(int d){
        for(int i: ds){
            if(d == i)
                return true;
        }
        return false;
    }
    static boolean validNum(int n){
        while(n > 0){
            if(!in(n%10))
                return false;
            n /= 10;
        }
        return true;
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
        int dn = Integer.parseInt(f.readLine());
        StringTokenizer st = new StringTokenizer(f.readLine());
        ds = new int[dn];
        for(int i = 0; i < dn; i++){
            ds[i] = Integer.parseInt(st.nextToken());
        }
        int poss = 0;
        for(int a: ds)
        for(int b: ds)
        for(int c: ds)
        for(int d: ds)
        for(int e: ds){
            int oper1 = a*100 + b*10 + c;
            int p1 = oper1 * d;
            int p2 = oper1 * e;
            if(validNum(p1) && p1 < 1000 && validNum(p2) && p2 < 1000 && validNum(p1*10 + p2) && p1*10+p2 < 10000){
                poss++;
            }
        }
        out.println(poss);
        out.close();
    }
}