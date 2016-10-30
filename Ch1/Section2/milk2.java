/*
ID: monghim1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

import java.util.Comparator;

/**
 * Created by mong on 10/27/16.
 */
public class milk2 {
    public static class Interval{
        public int begin, end;
        public Interval(int begin, int end){
            this.begin = begin;
            this.end = end;
        }
        public Interval merge(Interval other){
            return new Interval(Math.min(this.begin, other.begin), Math.max(this.end, other.end));
        }
        public int length(){
            return end - begin;
        }
    }
    public static class IntervalComparator implements Comparator<Interval> {

        @Override
        public int compare(Interval interval, Interval t1) {
            if(interval.begin < t1.begin)
                return -1;
            else if(interval.begin == t1.begin)
                return 0;
            else
                return 1;
        }
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int farmers = Integer.parseInt(f.readLine());
        Interval[] schedule = new Interval[farmers];
        for(int time = 0; time < farmers; time++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            schedule[time] = new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //sort the array
        Arrays.sort(schedule, new IntervalComparator());

        //get longest milking time
        Interval maxMilkingInterval = schedule[0];
        int maxMilking = maxMilkingInterval.length();
        int maxIdle = 0;
        for(int i = 1; i < schedule.length; i++){
            Interval cur = schedule[i];
            //if overlap
            if(maxMilkingInterval.end >= cur.begin){
                maxMilkingInterval = maxMilkingInterval.merge(cur);
                if(maxMilking < maxMilkingInterval.length())
                    maxMilking = maxMilkingInterval.length();
            }
            else{
                int distance = cur.begin - maxMilkingInterval.end;
                if(distance > maxIdle){
                    maxIdle = distance;
                }
                maxMilkingInterval = cur;
            }
        }

        out.print(maxMilking);
        out.println(" " + maxIdle);
        out.close();

    }
}

