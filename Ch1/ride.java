/*
ID: monghim1
LANG: JAVA
TASK: ride
*/
import java.io.*;
import java.util.*;

class ride {
    public static void main (String [] args) throws IOException {
        System.out.println("ok");
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("ride.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        String comet = f.readLine();
        String group = f.readLine();
        int c = 1;
        int g = 1;
        for(int i = 0; i < comet.length(); i++) {
            c *= comet.charAt(i) - 64;
        }
        for(int i = 0; i < group.length(); i++)
            g *= group.charAt(i) - 64;
        if (c % 47 == g % 47)
            out.println("GO");
        else
            out.println("STAY");
        out.close();



    }
}