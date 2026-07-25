package org.firstinspires.ftc.mecanisme;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensori {
    private DistanceSensor sensor;
    public void init(HardwareMap hwMap) {
        sensor = hwMap.get(DistanceSensor.class, "distance");
    }
    public double getdists() {
        return sensor.getDistance(DistanceUnit.CM);
    }
}
