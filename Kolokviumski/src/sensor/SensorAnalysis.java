package sensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class BadSensorException extends Exception {
    String sensorId;

    public BadSensorException(String sensorId) {
        super(String.format("No readings for sensor: %s", sensorId));
    }
}

class BadMeasureException extends Exception {

    int timestamp;
    String sesnsorId;

    public BadMeasureException(int timestamp, String sensorId){
        this.timestamp = timestamp;
        this.sesnsorId = sensorId;
    }

    public void setSesnsorId(String sesnsorId) {
        this.sesnsorId = sesnsorId;
    }

    @Override
    public String getMessage() {
        return String.format("Error in timestamp: %d from sensor: %s", timestamp, sesnsorId);
    }
}

interface IGeo {

    double getLatitude();

    double getLongitude();

    default Double distance(IGeo other) {
        return Math.sqrt(
                Math.pow(this.getLatitude() - other.getLatitude(), 2) +
                        Math.pow(this.getLongitude() - other.getLongitude(), 2));
    }
}

class Measurement {
    private int timestamp;
    double value;

    public Measurement(int timestamp, double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public static Measurement createMeasurement(String data, String sensorId) throws BadMeasureException {
        String[] parts = data.split(":");
        int timestamp=Integer.parseInt(parts[0]);
        double value = Double.parseDouble(parts[1]);

        if(value<0){

        }

        return new Measurement(timestamp, value);
    }
}

class Sensor {
    private String sensorId;
    IGeo location;
    private List<Measurement> measurements;

    public Sensor(String sensorId, IGeo location, List<Measurement> measurements) {
        this.sensorId = sensorId;
        this.location = location;
        this.measurements = measurements;
    }

    public static Sensor createSensor(String line) throws BadMeasureException, BadSensorException {
        String[] parts = line.split("\\s+");
        String sensorId = parts[0];
        if(parts.length==3){ // nema senzorski merenja
            throw new BadSensorException(sensorId);
        }

        IGeo location = new IGeo() {
            @Override
            public double getLatitude() {
                return Double.parseDouble(parts[1]);
            }

            @Override
            public double getLongitude() {
                return Double.parseDouble(parts[2]);
            }
        };

        // 2 2 4 6:3 7:9 8:4


        List<Measurement> measurements =
                new ArrayList<>();
        long toSkip = 3;  // ne ni trebat vekje prvite tri
        for (String part : parts) {
            if (toSkip > 0) {
                toSkip--;
                continue;
            }
            Measurement measurement = Measurement.createMeasurement(part, sensorId);
            measurements.add(measurement);
        }

        return new Sensor(sensorId, location, measurements);
    }

    double averageValue(){
        return measurements.stream()
                .mapToDouble(m->m.value)
                .average()
                .orElse(0);
    }
    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId='" + sensorId + '\'' +
                '}';
    }
}

class GeoSensorApplication {

    List<Sensor> sensors;



    public GeoSensorApplication() {
        this.sensors = new ArrayList<>();
    }

    public void readGeoSensors(Scanner scanner) throws BadSensorException, BadMeasureException {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sensors.add(Sensor.createSensor(line));
        }

    }

    public List<Sensor> inRange(IGeo location, double distance) {
      return   sensors.stream()
                .filter(sensor->sensor.location.distance(location) <distance)
                .collect(Collectors.toList());
    }

    public double averageValue() {
         return sensors.stream()
                 .mapToDouble(sensor-> sensor.averageValue())
                .average()
                .orElse(0);
    }

    public List<ExtremeValue> extremeValues(long t1, long t2) {
        return new ArrayList<>();
    }

    public double averageDistanceValue(IGeo iGeo, double dis, long from, long to) {
        return 0;
    }
}

class ExtremeValue{

}
public class SensorAnalysis {
    public static void main(String[] args) {
        GeoSensorApplication app = new GeoSensorApplication();

        Scanner s = new Scanner(System.in);
        double lat = s.nextDouble();
        double lon = s.nextDouble();
        double dis = s.nextDouble();
        long t1 = s.nextLong();
        long t2 = s.nextLong();

        s.nextLine();

        System.out.println("Access point on {" + lat + ", " + lon + "} distance:" + dis + " from:" + t1 + " - to:" + t2);

        try {
            app.readGeoSensors(s);


            System.out.println(app.inRange(new IGeo() {
                @Override
                public double getLatitude() {
                    return lat;
                }

                @Override
                public double getLongitude() {
                    return lon;
                }
            }, dis));

            System.out.println(app.averageValue());
            System.out.println(app.averageDistanceValue(new IGeo() {
                @Override
                public double getLatitude() {
                    return lat;
                }

                @Override
                public double getLongitude() {
                    return lon;
                }
            }, dis, t1, t2));

            System.out.println(app.extremeValues(t1, t2));
        } catch (BadSensorException e) {
            System.out.println(e.getMessage());
        } catch (BadMeasureException e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
        }
    }


}
