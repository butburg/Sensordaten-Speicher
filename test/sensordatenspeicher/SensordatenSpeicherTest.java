package sensordatenspeicher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sensordata.Sensordata;
import sensordata.SensordataImpl;
import sensordatenspeicher.SensordatenSpeicher;
import sensordatenspeicher.SensordatenSpeicherImpl;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Edwin W (570900) on Nov 2020
 */
class SensordatenSpeicherTest {

    LinkedList<Sensordata> sensorsEmpty;
    LinkedList<Sensordata> sensorsOne;
    LinkedList<Sensordata> sensors;
    double avg;
    double avgOne;
    double avgEmpty;
    private double avgAnton;
    private double avgBerta;
    private double avgCaesar;

    @BeforeEach
    void setUp() {

        Sensordata sensor1 = new SensordataImpl(System.currentTimeMillis(), 5, "Anton");
        Sensordata sensor2 = new SensordataImpl(System.currentTimeMillis(), 6, "Berta");
        Sensordata sensor3 = new SensordataImpl(System.currentTimeMillis(), 4, "Caesar");
        Sensordata sensor4 = new SensordataImpl(System.currentTimeMillis(), 10, "Anton");
        Sensordata sensor5 = new SensordataImpl(System.currentTimeMillis(), 13, "Berta");
        Sensordata sensor6 = new SensordataImpl(System.currentTimeMillis(), 20, "Anton");

        avg = (double) (5 + 6 + 4 + 10 + 13 + 20) / 6;
        avgOne = 5;
        avgEmpty = 0;

        avgAnton = (double) (5 + 10 + 20) / 3;
        avgBerta = (float) (6 + 13) / 2;
        avgCaesar = (float) 4;
        System.out.println(avg);
        System.out.println(avgOne);
        System.out.println(avgEmpty);
        System.out.println(avgAnton);
        System.out.println(avgBerta);
        System.out.println(avgCaesar);


        sensorsEmpty = new LinkedList<>();

        sensorsOne = new LinkedList<>();
        sensorsOne.add(sensor1);

        sensors = new LinkedList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);
        sensors.add(sensor4);
        sensors.add(sensor5);
        sensors.add(sensor6);
    }


    @Test
    void calculateAvgAll() throws Exception {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(avg, sds.calculateAvgAll(sensors));
    }

    @Test
    void calculateAvgAllOneValue() throws Exception {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(avgOne, sds.calculateAvgAll(sensorsOne));
    }

    @Test
    void calculateAvgAllEmptyList() throws Exception {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Throwable exc = assertThrows(Exception.class, () -> {
            sds.calculateAvgAll(sensorsEmpty);
        });
        assertEquals(exc.getMessage(), "There are no sensors to calculate average!");
    }


    @Test
    void countSensorValues() {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(3, sds.countSensorValues("Anton", sensors));
        Assertions.assertEquals(2, sds.countSensorValues("Berta", sensors));
        Assertions.assertEquals(1, sds.countSensorValues("Caesar", sensors));
        Assertions.assertEquals(0, sds.countSensorValues("Dora", sensors));
    }

    @Test
    void countSensorValueOne() {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(1, sds.countSensorValues("Anton", sensorsOne));
        Assertions.assertEquals(0, sds.countSensorValues("Berta", sensorsOne));
    }

    @Test
    void countSensorValuesEmpty() {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(0, sds.countSensorValues("Anton", sensorsEmpty));
    }


    @Test
    void calculateAvgOfSensor() throws Exception {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(avgAnton, sds.calculateAvgOfSensor("Anton", sensors));
        Assertions.assertEquals(avgBerta, sds.calculateAvgOfSensor("Berta", sensors));
        Assertions.assertEquals(avgCaesar, sds.calculateAvgOfSensor("Caesar", sensors));
    }

    @Test
    void calculateAvgOfSensorOne() throws Exception {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Assertions.assertEquals(avgOne, sds.calculateAvgOfSensor("Anton", sensorsOne));
    }

    @Test
    void calculateAvgOfSensorEmptyList() {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Throwable exc = assertThrows(Exception.class, () -> {
            sds.calculateAvgOfSensor("Anton", sensorsEmpty);
        });
        assertEquals(exc.getMessage(), "There are no sensors to calculate average!");
    }

    @Test
    void calculateAvgOfSensorUnknownName() {
        SensordatenSpeicher sds = new SensordatenSpeicherImpl();
        Throwable exc = assertThrows(Exception.class, () -> {
            sds.calculateAvgOfSensor("Dora", sensors);
        });
        assertEquals(exc.getMessage(), "There is no sensor with that name!");
    }
}