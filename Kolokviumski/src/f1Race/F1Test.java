package f1Race;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class F1Test {

    public static void main(String[] args) {
        F1Race f1Race = new F1Race();
        f1Race.readResults(System.in);
        f1Race.printSorted(System.out);
    }

}

class Driver {
    private String driverName;
    private  List<Lap> lapList;

    public Driver() {
        lapList = new ArrayList<>();
    }

    public Driver(String driverName, List<Lap> lapList) {
        this.driverName = driverName;
        this.lapList = lapList;
    }

    public static Driver createDriver(String line) {
        String[] parts = line.split("\\s+");
        String name = parts[0];
        List<Lap> laps = new ArrayList<>();
        Arrays.stream(parts).skip(1).forEach(i -> laps.add(new Lap(i)));

        return new Driver(name, laps);
    }

    public static int stringToTime(String time){
        String [] parts = time.split(":");

        return Integer.parseInt(parts[0]) * 60 * 1000
                + Integer.parseInt(parts[1])*1000
                + Integer.parseInt(parts[2]);
    }

    public static String timeToString(int time){
        int minutes = time/1000 / 60;
        int seconds = (time - minutes * 1000 * 60) / 1000;
        int ms = time % 1000;
        return String.format("%d:%02d:03d", minutes, seconds, ms);
    }
    public String findBestDriver(){
        return lapList.stream()
                .min(Comparator.comparingInt(lap -> stringToTime(lap.getTime())))
                        .map(Lap::getTime)
                .orElse(" ");
    }

    @Override
    public String toString() {
        return String.format("%-10s%10s", driverName, findBestDriver());
    }
}

class Lap {

    private String time;

    public Lap(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}

class F1Race {

    private List<Driver> drivers;
    public F1Race(){
        drivers=new ArrayList<>();
    }


    public void readResults(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines()
                .forEach(line -> {
                    Driver driver = Driver.createDriver(line);
                    drivers.add(driver);
                });
    }

    public void printSorted(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        drivers.sort(Comparator.comparing(Driver::findBestDriver));

        for (int i = 0; i < drivers.size(); i++) {
            pw.println((i+1)+". "+ drivers.get(i).toString());
        }
        pw.flush();
        pw.close();
    }
}
