/*
ID: monghim1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {

    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
        int np = Integer.parseInt(f.readLine());
        Map<String, Integer> people = new HashMap<String, Integer>();
        for(int i = 0; i < np; i++){
            people.put(f.readLine(), 0);
        }
        //each person
        for(int i = 0; i < np; i++){
            String giver = f.readLine();
            StringTokenizer line = new StringTokenizer(f.readLine());
            int money = Integer.parseInt(line.nextToken());
            int receiverNum = Integer.parseInt(line.nextToken());
            if(receiverNum == 0) {
                people.put(giver, money);
                continue;
            }
            else {
                people.put(giver, money % receiverNum - money);
                money = money / receiverNum;
            }
            for(int k = 0; k < receiverNum; k++) {
                String receiver = f.readLine();
                people.put(receiver, people.get(receiver) + money);
            }
        }

        //output
        for(String name: people.keySet()) {
            out.println(name + " " + people.get(name));
        }
        out.close();

    }
}