package sensordatenspeicher;

import sensordata.Sensordata;

import java.util.LinkedList;

/**
 * @author Edwin W (570900) on Nov 2020
 */
public interface SensordatenSpeicher {
    /**
     * Calculates the average value of all Sensors and all values of them
     *
     * @param sensors the List of the sensors
     * @return the average value of all sensors
     */
    double calculateAvgAll(LinkedList<Sensordata> sensors) throws Exception;

    /**
     * Counts the number of values, that the particular sensor measured.
     *
     * @param sensorname the particular sensor
     * @param sensors    the List of the sensors
     * @return the number of values
     */
    int countSensorValues(String sensorname, LinkedList<Sensordata> sensors);

    /**
     * Calculates the average of all values for a specific Sensor
     *
     * @param sensorname the particular sensor
     * @param sensors    the List of the sensors
     * @return the average value of one sensor
     */
    double calculateAvgOfSensor(String sensorname, LinkedList<Sensordata> sensors) throws Exception;
}
