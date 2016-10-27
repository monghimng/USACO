/*
ID: monghim1
LANG: JAVA
TASK: milk2
*/
import java.io.*;
import java.util.*;

class milk2v1 {
    public static class Interval{
        //inclusive begin, exclusive end
        int begin, end;
        public Interval(int begin, int end){
            this.begin = begin;
            this.end = end;
        }
        public boolean overlap(Interval another){
            return (begin <= another.end && end >= another.end) || (another.begin <= end && another.end >= end);
        }
        public int length(){
            return end - begin;
        }
        public Interval merge(Interval another){
            return new Interval(Math.min(begin, another.begin), Math.max(end, another.end));
        }
        public static int distance(Interval earlier, Interval later){
            return later.begin - earlier.end;
        }
    }
    public static ArrayList<Interval> schedule = new ArrayList<>();
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));

        int n = Integer.parseInt(f.readLine());
        StringTokenizer st = null;
        outer: for(int i = 0; i < n; i++){
            st = new StringTokenizer(f.readLine());
            int begin = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            Interval in = new Interval(begin, end);
            for(int k = 0; k < schedule.size(); k++){
                Interval cur = schedule.get(k);
                if(cur.overlap(in)){
                    cur = cur.merge(in);
                    schedule.set(k, cur);
                    //resolve merger
                    for(int m = k + 1; m < schedule.size();){
                        if(!cur.overlap(schedule.get(m))){
                            break;
                        }
                        cur = cur.merge(schedule.get(m));
                        schedule.set(k, cur);
                        schedule.remove(m);
                    }
                    continue outer;
                }
                else if(cur.begin > in.end){
                    schedule.add(k, in);
                    continue outer;
                }
            }
            schedule.add(in);
        }

        //max milking
        int maxMilking = 0;
        for(Interval i: schedule){
            if(maxMilking < i.length()){
                maxMilking = i.length();
            }
        }

        //min milking
        int idleMilking = 0;
        if(schedule.size() > 1) {
            idleMilking = Interval.distance(schedule.get(0), schedule.get(1));
            for (int k = 1; k + 1 < schedule.size(); k++) {
                int d = Interval.distance(schedule.get(k), schedule.get(k + 1));
                if (d > idleMilking) {
                    idleMilking = d;
                }
            }
        }
        out.print(maxMilking + " ");
        out.println(idleMilking);
        out.close();

    }
}
