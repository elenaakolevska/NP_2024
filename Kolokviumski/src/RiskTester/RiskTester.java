package RiskTester;



import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class Risk {

    public void processAttacksData(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        br.lines()
                .forEach(line -> {
                    String[] parts = line.split(";");
                    String attacker = parts[0];
                    String attacked = parts[1];
                    String[] attackerArray = attacker.split("\\s+");
                    String[] attackedArray = attacked.split("\\s+");

                    int[] attackerDice = Arrays.stream(attackerArray)
                            .mapToInt(Integer::parseInt)
                            .toArray();
                    int[] attackedDice = Arrays.stream(attackedArray)
                            .mapToInt(Integer::parseInt)
                            .toArray();

                    Arrays.sort(attackerDice);
                    Arrays.sort(attackedDice);

                    int attackerSurvived = 0;
                    int attackedSurvived = 0;
                    for (int i = 0; i < 3; i++) {
                        if (attackerDice[2 - i] > attackedDice[2 - i]) {
                            attackerSurvived++;
                        } else {
                            attackedSurvived++;
                        }
                    }

                    System.out.println(attackerSurvived + " " + attackedSurvived);
                });
    }
}

public class RiskTester {
    public static void main(String[] args) {
        Risk risk = new Risk();
        risk.processAttacksData(System.in);
    }
}
