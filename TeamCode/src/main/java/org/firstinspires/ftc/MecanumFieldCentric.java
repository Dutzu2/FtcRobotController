package org.firstinspires.ftc;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.mecanisme.DistanceSensori;
import org.firstinspires.ftc.mecanisme.FieldDrive;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp (group = "AAADUTZU")
public class MecanumFieldCentric extends OpMode {

    FieldDrive drive = new FieldDrive();
    DistanceSensori distsensor = new DistanceSensori();

    TouchSensor backtouch;

    double forward, strafe, rotate;
    boolean resetyaw;


    @Override
    public void init() {
        drive.init(hardwareMap);
        distsensor.init(hardwareMap);
        backtouch = hardwareMap.get(TouchSensor.class,"touch");
    }

    @Override
    public void loop() {
        resetyaw = gamepad1.a;
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;
        telemetry.addData("Valoare forward", forward);
        telemetry.addData("Valoare strafe", strafe);
        telemetry.addData("Valoare rotate", rotate);
        double pow = 1;
       /** if (drive.goingInFront(forward))
            pow = distsensor.getdists() > 150 ? 1 : distsensor.getdists()/150; **/
        if (resetyaw)
            drive.resetrot(forward, strafe);
        else
            drive.fieldMode(forward,strafe,rotate, pow);
        telemetry.addData("Distanta de perete", distsensor.getdists());
        telemetry.addData("Rotate", drive.getYaw());
        telemetry.addData("Atinge in spate", backtouch.getValue() == 1 ? "True" : "False");
        telemetry.addData("Merge in fata", drive.goingInFront(forward));

    }
}
