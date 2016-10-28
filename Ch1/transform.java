/*
ID: monghim1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {

    static int n = 0;
    static char[][] original;
    static char[][] after;
    static char[][] reflect(char[][] original){
        char [][] result = new char[n][n];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < (n+1)/2; c++){
                result[r][n-1-c] = original[r][c];
                result[r][c] = original[r][n-1-c];
            }
        }
        return result;
    }
    static char[][] rotate(char[][] original){
        char [][] result = new char[n][n];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                result[c][n-1-r] = original[r][c];
            }
        }
        return result;
    }
    static boolean gridEquals(char[][] in, char[][] out){
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                if(in[r][c] != out[r][c])
                    return false;
            }
        }
        return true;
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("transform.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));

        //reading input
        n = Integer.parseInt(f.readLine());
        original = new char[n][n];
        after = new char[n][n];
        for(int r = 0; r < n; r++){
            String row = f.readLine();
            for(int c = 0; c < n; c++){
                original[r][c] = row.charAt(c);
            }
        }
        for(int r = 0; r < n; r++){
            String row = f.readLine();
            for(int c = 0; c < n; c++){
                after[r][c] = row.charAt(c);
            }
        }

        //determine transformation type
        int type = 7;
        char[][] horizontalReflected = reflect(original);
        char[][] clockwise90 = rotate(original);
        if(gridEquals(after, clockwise90)){
            type = 1;
        }
        else if(gridEquals(after, (clockwise90 = rotate(clockwise90)))){
            type = 2;
        }
        else if(gridEquals(after, (rotate(clockwise90)))){
            type = 3;
        }
        else if(gridEquals(after, horizontalReflected)){
            type = 4;
        }
        else if(gridEquals(after, (horizontalReflected = rotate(horizontalReflected)))
                || gridEquals(after, (horizontalReflected = rotate(horizontalReflected)))
                || gridEquals(after, (rotate(horizontalReflected)))){
            type = 5;
        }
        else if(gridEquals(after, original)){
            type = 6;
        }

        out.println(type);
        out.close();

    }
}