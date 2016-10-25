/*
ID: monghim1
LANG: JAVA
TASK: friday
*/
import java.io.*;
import java.util.*;

class friday {
    public static Date13 first = new Date13(0, 1900, 5);
    public static int[] normalYear = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static int[] leapYear = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static class Date13{
        public int m, y, weekday;
        public Date13(int m, int y, int weekday){
            this.m = m;
            this.y = y;
            this.weekday = weekday;
        }
    }

    public static Date13 next13(Date13 last){
        int dayNum;
        if(last.y % 400 == 0){
            dayNum = leapYear[last.m];
        }
        else if(last.y % 100 == 0){
            dayNum = normalYear[last.m];
        }
        else if(last.y % 4 == 0){
            dayNum = leapYear[last.m];
        }
        else{
            dayNum = normalYear[last.m];
        }
        int nextM = (last.m + 1) % 12;
        int nextY = nextM == 0 ? last.y +1 : last.y;
        int nextWeekday = (last.weekday + dayNum) % 7;

        return new Date13(nextM, nextY, nextWeekday);
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("friday.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));

        int yearCount = Integer.parseInt(f.readLine());
        int[] fridayCount = new int[7];
        Date13 cur = first;
        for(int i = 0; i < yearCount * 12; i++){
            fridayCount[cur.weekday]++;
            cur = next13(cur);
        }

        out.print(fridayCount[5]);
        out.print(" " + fridayCount[6]);

        for(int i = 0; i < 5; i++){
            out.print(" " + fridayCount[i]);
        }
        out.println();
        out.close();

    }
}