package aud9;

import java.util.HashSet;
import java.util.Random;

class BirthdayParadox{
    int maxPeople;
    static int TRIALS = 5000;
    public BirthdayParadox(int i){
        maxPeople = i;
    }


    public void conductExperiment() {
        for (int i = 2; i <= maxPeople; i++) {
            System.out.println(String.format("%d --> %.10f\n", i, runSimulation(i)));
        }
    }

    private float runSimulation(int people) {
        Random random = new Random();
        int counter =0;
        for (int i = 0; i < TRIALS; i++) {
            if(runTrial(people, random)){
                counter++;
            }
        }
        return counter*1.0f/TRIALS;
    }

    private boolean runTrial(int people, Random random) {
        HashSet<Integer> birthdays = new HashSet<>();

        for (int i = 0; i <people; i++) {
            int birthday = random.nextInt(365)+1;
            if(birthdays.contains(birthday)) return true;
            else birthdays.add(birthday);
        }
        return false;
    }


}
public class BirthdayParadoxTest {
    public static void main(String[] args) {
        BirthdayParadox bp = new BirthdayParadox(100);
        bp.conductExperiment();
    }
}
