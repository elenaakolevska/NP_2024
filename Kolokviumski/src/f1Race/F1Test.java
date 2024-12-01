package f1Race;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class Driver{
    String driverName;
     List<Duration> laps;

    public Driver(String driverName, List<Duration> laps) {
        this.driverName = driverName;
        this.laps = laps;

    }

    public static Driver createDriver(String line){
        String[] parts = line.split("\\s+");
        String driverName = parts[0];
        List<Duration> laps1 = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            laps1.add(castTime(parts[i]));
        }
        return new Driver(driverName, laps1);
    }

    public static Duration castTime(String time){
        String[] times = time.split(":");
        int minutes = Integer.parseInt(times[0]);
        int seconds = Integer.parseInt(times[1]);
        int millis = Integer.parseInt(times[2]);

        return Duration.ofMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);
    }

    public Duration findBestLap(){
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
        br.lines().forEach(i->{
            Driver driver = Driver.createDriver(i);
            drivers.add(driver);
        });


    }

    public void printSorted(OutputStream out) {
        PrintWriter pw = new PrintWriter(out);
        AtomicInteger count = new AtomicInteger();
        count.set(1);
        drivers.stream()
                .sorted((d1, d2) -> d1.findBestLap().compareTo(d2.findBestLap()))
                .forEachOrdered((d -> {
                    Duration bestLap = d.findBestLap();
                    String formattedLap = String.format("%2d:%02d:%03d",
                            bestLap.toMinutesPart(),
                            bestLap.toSecondsPart(),
                            bestLap.toMillisPart());
                    pw.println(String.format("%d. %-10s%10s", count.getAndIncrement(), d.driverName, formattedLap));
                }));

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

