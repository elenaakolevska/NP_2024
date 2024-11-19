package risk24;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Risk{

    public AtomicInteger processAttacksData(InputStream in){
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        AtomicInteger wins = new AtomicInteger();
        br.lines()
                .forEach(line->{
                    String[] parts = line.split(";");
                    String attacker = parts[0];
                    String attacked = parts[1];
                    String[] attackerArray = attacker.split("\\s+");
                    String[] attackedArray = attacked.split("\\s+");

                    Arrays.sort(attackerArray);
                    Arrays.sort(attackedArray);

                    int count = 0;
                    for (int i = 0; i < attackerArray.length; i++) {
                        if(Integer.parseInt(attackerArray[i]) > Integer.parseInt(attackedArray[i])){
                            count++;
                        }
                    }
                    if (count==3) wins.getAndIncrement();
                });
        return wins;
    }
}

public class RiskTester {
    public static void main(String[] args) {

        Risk risk = new Risk();

        System.out.println(risk.processAttacksData(System.in));

    }
}