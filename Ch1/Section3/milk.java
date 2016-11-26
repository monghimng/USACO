/*
ID: monghim1
LANG: JAVA
TASK: milk
*/
import java.io.*;
import java.util.StringTokenizer;

public class milk {

    static class Farmer{
        int price, amount;
        Farmer(String info){
            String[] i = info.split(" ");
            price = Integer.parseInt(i[0]);
            amount = Integer.parseInt(i[1]);
        }
        Farmer(int price, int amount){
            this.price = price;
            this.amount = amount;
        }
    }

    public static int calculateCost(int need, Farmer[] fs, int i){
        if(fs.length <= i) {
            return 0;
        }
        else if(need <= fs[i].amount)
        {
            return need * fs[i].price;
        }
        else{
            return fs[i].amount * fs[i].price + calculateCost(need - fs[i].amount, fs, i+1);
        }
    }
    public static void main (String [] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("milk.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int need = Integer.parseInt(st.nextToken());
//        int farmerNum = Integer.parseInt(st.nextToken());

        Farmer[] fs = f.lines()
                .map(s->new Farmer(s))
                .sorted((Farmer f1, Farmer f2) -> f1.price - f2.price)
                .toArray(Farmer[]::new);
        out.println(calculateCost(need, fs, 0));
        out.close();

    }
}

/*
performance was actually ok, i am surprised, i guess usaco training didn't give me any edge case

 */