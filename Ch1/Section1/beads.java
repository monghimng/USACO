/*
ID: monghim1
LANG: JAVA
TASK: beads
*/
import java.io.*;
import java.util.*;

class beads {

    public static int collect(String necklace, int len, int start, int dir){
        int collected = 1;
        char first = necklace.charAt(len + start);
        for(int r = 1; r < len; r++){
            char cur = necklace.charAt(len + start + r * dir);
            if(first == 'w'){
                collected++;
                first = cur;
            }
            else if(cur == 'w' || first == cur){
                collected++;
            }
            else{
                return collected;
            }
        }
        return collected;
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("beads.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));

        int len = Integer.parseInt(f.readLine());
        String necklace = f.readLine();
        necklace = necklace + necklace + necklace;
        int maxCollected = 0;

        for(int i = 0; i < len; i++){
            int curCollected = collect(necklace, len, i, 1) + collect(necklace, len, i-1, -1);
            maxCollected = Math.max(curCollected, maxCollected);
            if(maxCollected >= len){
                maxCollected = len;
                break;
            }
        }


        out.println(maxCollected);
        out.close();

    }
}