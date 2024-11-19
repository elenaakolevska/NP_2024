package f1Race;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Driver {
    String name;
    List<Duration> laps;

    public Driver(String name, List<Duration> lapsList) {
        this.name = name;
        this.laps = lapsList;
    }

    public static Driver createDriver(String line) {
        String[] parts = line.split("\\s+");
        String driverName = parts[0];
        List<Duration> lapsList = new ArrayList<>();

        for (int i = 1; i < parts.length; i++) {
            lapsList.add(parseLapTime(parts[i]));
        }

        return new Driver(driverName, lapsList);
    }

    private static Duration parseLapTime(String lapTime) {
        String[] timeParts = lapTime.split(":");
        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);
        int millis = Integer.parseInt(timeParts[2]);

        return Duration.ofMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);
    }

    public Duration getBestLap() {
        return laps.stream().min(Duration::compareTo).orElse(Duration.ZERO);
    }
}

class F1Race {
    List<Driver> drivers;

    public F1Race() {
        drivers = new ArrayList<>();
    }

    public void readResults(InputStream in) {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        br.lines().forEach(line -> {
            Driver driver = Driver.createDriver(line);
            drivers.add(driver);
        });
    }

    public void printSorted(PrintStream out) {
        PrintWriter pw = new PrintWriter(out);
        AtomicInteger count = new AtomicInteger();
        drivers.stream()
                .sorted((d1, d2) -> d1.getBestLap().compareTo(d2.getBestLap()))
                .forEach(driver -> {
                    count.getAndIncrement();
                    Duration bestLap = driver.getBestLap();
                    pw.printf("%d. %-10s %2d:%02d:%03d\n",
                            count.get(),
                            driver.name,
                            bestLap.toMinutes(),
                            bestLap.getSeconds() % 60,
                            bestLap.toMillis() % 1000);
                });
        pw.flush();
    }
}

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}
