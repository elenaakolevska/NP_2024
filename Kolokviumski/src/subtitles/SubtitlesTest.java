package subtitles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class Part{
    int number;
    Duration startTime;
    Duration endTime;
    String text;

    public Part(int number, Duration startTime, Duration endTime, String text) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = text;
    }

    public String timeToString(Duration duration){
        int hours = duration.toHoursPart();
        int minutes = duration.minusHours(hours).toMinutesPart();
        int seconds = duration.minusHours(hours).minusMinutes(minutes).toSecondsPart();
        int millis = duration.minusHours(hours).minusMinutes(minutes).minusSeconds(seconds).toMillisPart();

        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, millis);

    }

    @Override
    public String toString() {
        return number + "\n" + timeToString(startTime)+ "-->" + timeToString(endTime) +"\n"+text+"\n";
    }
}
class Subtitles{

    List<Part> partList;

    public Subtitles() {
        partList = new ArrayList<>();
    }

    public int loadSubtitles(InputStream in) {
       Scanner sc = new Scanner(in);
        int count = 0;
       while (sc.hasNextLine()){
           int number = Integer.parseInt(sc.nextLine());
           String[] parts = sc.nextLine().split("-->");
           Duration startTime = parseTime(parts[0]);
           Duration endTime = parseTime(parts[1]);

           StringBuilder sb = new StringBuilder();
           while (sc.hasNextLine()){
               String line = sc.nextLine();
               if(line.isEmpty()) break;
               sb.append(line).append("\n");
           }
           String text = sb.toString();

           Part part = new Part(number, startTime, endTime, text);
           partList.add(part);
           count++;
       }
       return count;
    }
    public Duration parseTime(String time){
        String[] times = time.split("[:,]");
        int hours = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);
        int seconds = Integer.parseInt(times[2]);
        int millis = Integer.parseInt(times[3]);

        return Duration.ofHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);
    }

    public void print() {
        for (Part part : partList) {
            System.out.println(part);
        }
    }

    public void shift(int shift) {
        Duration shifting = Duration.ofMillis(shift);
        for (Part part : partList) {
            part.startTime = part.startTime.plus(shifting);
            part.endTime = part.endTime.plus(shifting);
        }
    }
}

public class SubtitlesTest {
    public static void main(String[] args) {
        Subtitles subtitles = new Subtitles();
        int n = subtitles.loadSubtitles(System.in);
        System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
        subtitles.print();
        int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
        System.out.println(String.format("SHIFT FOR %d ms", shift));
        subtitles.shift(shift);
        System.out.println("+++++ SHIFTED SUBTITLES +++++");
        subtitles.print();
    }
}

