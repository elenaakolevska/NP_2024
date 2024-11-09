package timetable;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

enum TimeFormat {
    FORMAT_24, FORMAT_AMPM
}
class UnsupportedFormatException extends Exception{
    public UnsupportedFormatException(String message) {
        super(message);
    }
}

class InvalidTimeException extends Exception{
    public InvalidTimeException(String message) {
        super(message);
    }
}

class Time implements Comparable<Time>{

    int hour;
    int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String time) throws UnsupportedFormatException, InvalidTimeException {
        String [] parts = time.split("\\.");
        if(parts.length == 1)  parts=time.split(":");
        if( parts.length == 1) throw new UnsupportedFormatException(time);

        this.hour=Integer.parseInt(parts[0]);
        this.minute=Integer.parseInt(parts[1]);

        if(hour<0 || hour>23 || minute<0 || minute>59)
            throw new InvalidTimeException(time);

    }


    public String toStringAMPM(){
        String part = "AM";
        int h = hour;
        if(h==0){
            h+=12;
        }else if(h==12){
            part="PM";
        } else if (h>12) {
            h-=12;
            part="PM";
        }

        return String.format("%2d:%02d %s", h, minute, part);
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d", hour, minute);
    }

    @Override
    public int compareTo(Time o) {
        if(hour==o.hour) return minute-o.minute;
        else return hour-o.hour;
    }
}
class TimeTable{
     static List<Time> timeList;

    public TimeTable() {
        timeList=new ArrayList<>();
    }

    public static void readTimes(InputStream inputStream) throws InvalidTimeException, UnsupportedFormatException {
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String [] parts = line.split(" ");
            for (String p: parts){
                Time time = new Time(p);
                timeList.add(time);
            }
        }
    }

    public static void writeTimes(OutputStream outputStream, TimeFormat format){
        PrintWriter pw = new PrintWriter(outputStream);
        Collections.sort(timeList);

        for (Time time : timeList){
            if (format == TimeFormat.FORMAT_24) pw.println(time);
            else pw.println(time.toStringAMPM());
        }

        pw.flush();

    }

}
public class TimesTest {

    public static void main(String[] args) {
        TimeTable timeTable = new TimeTable();
        try {
            timeTable.readTimes(System.in);
        } catch (UnsupportedFormatException e) {
            System.out.println("UnsupportedFormatException: " + e.getMessage());
        } catch (InvalidTimeException e) {
            System.out.println("InvalidTimeException: " + e.getMessage());
        }
        System.out.println("24 HOUR FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
        System.out.println("AM/PM FORMAT");
        timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
    }

}

