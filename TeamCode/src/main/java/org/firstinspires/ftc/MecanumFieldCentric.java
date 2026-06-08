package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.mecanisme.FieldDrive;

@TeleOp
public class MecanumFieldCentric extends OpMode {

    FieldDrive drive = new FieldDrive();
    double forward, strafe, rotate;



    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        forward = -gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.fieldMode(forward,strafe,rotate);
    }
}
