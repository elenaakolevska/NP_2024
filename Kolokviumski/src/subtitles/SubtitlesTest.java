package subtitles;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Element{
    int number;
    Duration startTime;
    Duration endTime;
    String text;

    public Element(int number, Duration startTime, Duration endTime, String text) {
        this.number = number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.text = text;
    }
    @Override
    public String toString() {
        return number + "\n" +
                formatDuration(startTime) + " --> " + formatDuration(endTime) + "\n" +
                text + "\n";
    }
    public String formatDuration(Duration duration){
        int hours = (int) duration.toHours();
        int minutes = (int) (duration.toMinutes()%60);
        int seconds = (int) (duration.getSeconds()%60);
        int millis = (int) (duration.toMillis()%1000);
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, millis);
    }
}

class Subtitles{
List<Element> elements;

    public Subtitles() {
        elements=new ArrayList<>();
    }

    public int loadSubtitles(InputStream in) {
        Scanner sc = new Scanner(in);
        int loaded = 0;
        while (sc.hasNextLine()){
            int number = Integer.parseInt(sc.nextLine().trim());

            String[] parts = sc.nextLine().split("-->");
            Duration startTime = parseTime(parts[0].trim());
            Duration endTime = parseTime(parts[1].trim());

            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()){
                String line = sc.nextLine().trim();
                if(line.isEmpty()) break;
                sb.append(line).append("\n");
            }
            String text = sb.toString().trim();


            Element element = new Element(number, startTime, endTime, text);
            elements.add(element);
            loaded++;
        }
       return loaded;
    }

    public Duration parseTime (String time){
        String[] parts = time.split(":|,");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        int millis = Integer.parseInt(parts[3]);

        return Duration.ofHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);

    }
    public void print() {
        for (Element element : elements) {
            System.out.println(element);
        }
    }

    public void shift(int ms) {
        Duration shiftAmount = Duration.ofMillis(ms);
        for (Element element : elements) {
            element.startTime = element.startTime.plus(shiftAmount);
            element.endTime = element.endTime.plus(shiftAmount);
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

