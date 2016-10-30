/*
ID: monghim1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {

    public static int getPersonIndex(String[] people, String name){
        for(int i = 0; i < people.length; i++){
            if(people[i].equals(name)){
                return i;
            }
        }
        return -1;
    }

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

        int np = Integer.parseInt(f.readLine());
        String[] people = new String[np];
        int[] bank = new int[np];

        //init
        for(int i = 0; i < np; i++){
            people[i] = f.readLine();
        }
        //each person
        for(int i = 0; i < np; i++){
            int giver = getPersonIndex(people, f.readLine());
            StringTokenizer line = new StringTokenizer(f.readLine());
            int money = Integer.parseInt(line.nextToken());
            int receiverNum = Integer.parseInt(line.nextToken());

            if(money == 0) {
                for(int k = 0; k < receiverNum; k++)
                    f.readLine();
                continue;
            }
            else if(receiverNum == 0){
                bank[giver] += money;
                continue;
            }

            bank[giver] = bank[giver] - money + money % receiverNum;
            money = money / receiverNum;
            for(int k = 0; k < receiverNum; k++) {
                int receiver = getPersonIndex(people, f.readLine());
                bank[receiver] += money;
            }
        }

        //output
        for(int i = 0; i < np; i++) {
            out.println(people[i] + " " + bank[i]);
        }
        out.close();

    }
}