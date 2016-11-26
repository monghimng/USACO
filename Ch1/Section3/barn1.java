/*
ID: monghim1
LANG: JAVA
TASK: barn1
*/
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class barn1 {

    static class Status{
        int covered = 0;
        int cowed = 0;
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("barn1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int boardNum = Integer.parseInt(st.nextToken());
        int stallNum = Integer.parseInt(st.nextToken());
        int cowedStallNum = Integer.parseInt(st.nextToken());

        Status[] barn = new Status[stallNum];
        for(int i = 0;i < stallNum; i++){
            barn[i] = new Status();
        }
        if(cowedStallNum == 0){
            out.println(0);
        }
        else if(cowedStallNum == 1){
            out.println(1);
        }
        else{
            //create first board
            for(int i = 0; i < cowedStallNum; i++){
                barn[Integer.parseInt(f.readLine()) - 1].cowed = 1;
            }
            int first = findFirst(barn);
            int last = findLast(barn);

            for(int i = first; i <= last; i++){
                barn[i].covered = 1;
            }

            //greedily remove stall cover
            for(int k = 1; k < boardNum; k++){
                if(!removeLargestSection(barn)){
                    break;
                }
            }

            //sum up the total covered stall
            out.println(Arrays.stream(barn).mapToInt(s->s.covered).sum());
        }
        out.close();
    }

    public static boolean removeLargestSection(Status[] barn){
        int maxIndex = -1;
        int index = -1;
        int maxSection = 0;
        int section = 0;
        for(int i = 0; i < barn.length; i++) {
            if (barn[i].covered == 1 && barn[i].cowed == 0) {
                if(section == 0){
                    index = i;
                }
                section++;
                if(section > maxSection){
                    maxSection = section;
                    maxIndex = index;
                }
            }
            else{
                section = 0;
            }
        }

        //result
        if(maxIndex == -1){
            return false;
        }
        else{
            for(int k = maxIndex; k < maxIndex + maxSection; k++){
                barn[k].covered = 0;
            }
            return true;
        }
    }

    public static int findFirst(Status[] barn){
        for(int i = 0; i < barn.length; i++){
            if(barn[i].cowed == 1)
                return i;
        }
        return -1;
    }
    public static int findLast(Status[] barn){
        for(int i = barn.length-1; i >= 0; i--){
            if(barn[i].cowed == 1)
                return i;
        }
        return -1;
    }
}