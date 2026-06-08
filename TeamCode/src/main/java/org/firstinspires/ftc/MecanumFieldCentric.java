package org.firstinspires.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.mecanisme.FieldDrive;

@TeleOp
public class MecanumFieldCentric extends OpMode {

    FieldDrive drive = new FieldDrive();
    double x, y, rotate;



    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        y = -gamepad1.left_stick_y;
        x = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.fieldMode(y,x,rotate);
    }
}
