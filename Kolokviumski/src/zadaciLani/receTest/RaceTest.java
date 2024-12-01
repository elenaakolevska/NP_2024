package zadaciLani.receTest;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Participant{
    long id;
    Duration startTime;
    Duration endTime;

    public Participant(long id, Duration startTime, Duration endTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Duration  getTotalTime(){
        return endTime.minus(startTime);
    }

    @Override
    public String toString() {
        return id + " " + durationToString(getTotalTime());
    }
    public static String durationToString(Duration duration){
        int minutes = (int) duration.toMinutes();
        int seconds = duration.minusMinutes(minutes).toSecondsPart();
        int millis = duration.minusSeconds(duration.toSeconds()).toMillisPart();

        return String.format("%02d:%02d:%02d", minutes, seconds, millis);
    }

}

class TeamRace{
   static List<Participant> participants=participants = new ArrayList<>();



    public static void findBestTeam(InputStream in, PrintStream out) {

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        br.lines().forEach(line->{

            String[] parts = line.split("\\s+");
            long participantId = Long.parseLong(parts[0]);
            Duration startTime = parseTime(parts[1]);
            Duration endTime = parseTime(parts[2]);

            participants.add(new Participant(participantId, startTime, endTime));
        });

        participants.sort(Comparator.comparing(Participant::getTotalTime));
        List<Participant>  best = participants.subList(0,4);

        Duration totalTimeBestTeam = Duration.ofMillis(best.stream().mapToLong(person -> person.getTotalTime().toMillis()).sum());

        PrintWriter pw = new PrintWriter(out);

        for (Participant participant : best) {
            pw.println(participant.toString());
        }

        pw.println(Participant.durationToString(totalTimeBestTeam));

        pw.flush();
    }


    public static Duration parseTime(String time){
        String[] times = time.split(":");
        int minutes = Integer.parseInt(times[0]);
        int seconds = Integer.parseInt(times[1]);
        int millis = Integer.parseInt(times[2]);

        return Duration.ofMinutes(minutes)
                .plusSeconds(seconds)
                .plusMillis(millis);
    }

}

public class RaceTest {
    public static void main(String[] args) {
        TeamRace.findBestTeam(System.in, System.out);
    }
}
