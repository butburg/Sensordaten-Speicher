package sensordatenspeicher;

import sensordata.Sensordata;

import java.util.LinkedList;

/**
 * @author Edwin W (570900) on Nov 2020
 */
public class SensordatenSpeicherImpl implements SensordatenSpeicher {
    @Override
    public double calculateAvgAll(LinkedList<Sensordata> sensors) throws Exception {
        if (sensors.isEmpty()) throw new Exception("There are no sensors to calculate average!");
        double sum = sensors.stream().mapToDouble(sensordata -> sensordata.getValue()).sum();
        return sum / sensors.size();
    }

    @Override
    public int countSensorValues(String sensorname, LinkedList<Sensordata> sensors) {
        return (int) sensors.stream().
                filter(sensordata -> sensordata.getSensorName() == sensorname)
                .count();
    }

    @Override
    public double calculateAvgOfSensor(String sensorname, LinkedList<Sensordata> sensors) throws Exception {
        if (sensors.isEmpty()) throw new Exception("There are no sensors to calculate average!");

        int sensorNumber = countSensorValues(sensorname, sensors);
        if (sensorNumber == 0) {
            throw new Exception("There is no sensor with that name!");
        }

        double sum = sensors.stream()
                .filter(sensordata -> sensordata.getSensorName() == sensorname)
                .mapToDouble(sensordata -> sensordata.getValue())
                .sum();
        return sum / sensorNumber;
    }
}
