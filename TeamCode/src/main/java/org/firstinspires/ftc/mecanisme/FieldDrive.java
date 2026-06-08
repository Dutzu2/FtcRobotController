package org.firstinspires.ftc.mecanisme;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class FieldDrive {
    private DcMotor frontL, frontR, backL, backR;
    private IMU imu;

    public void init(HardwareMap hwMap){
        frontL = hwMap.get(DcMotor.class, "frontL");
        frontR = hwMap.get(DcMotor.class, "frontR");
        backL = hwMap.get(DcMotor.class, "backL");
        backR = hwMap.get(DcMotor.class, "backR");
        /// deviceName este cel din config

        frontL.setDirection(DcMotor.Direction.REVERSE);
        backR.setDirection(DcMotor.Direction.REVERSE);
        ///  un set de motoare vor fi in oglinda fata de celelalte

        frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ///  RUN_USING_ENCODER inseamna ca motoarele vor incerca sa mearga la aceeasi viteza indiferent de alte forte

        imu = hwMap.get(IMU.class,"imu"); /// default name pentru imu

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        );
        imu.initialize(new IMU.Parameters(RevOrientation)); /// Luam directia robotului cu ajutorul IMU
    }
    public void drive(double forward, double strafe, double rotate){
        double frontLPower = forward + strafe + rotate;
        double backLPower = forward - strafe + rotate;
        double frontRPower = forward - strafe - rotate;
        double backRPower = forward + strafe - rotate;

        double maxPower = 1;

        maxPower = Math.max(maxPower,Math.max(Math.abs(frontLPower),Math.max(Math.abs(frontRPower),Math.max(Math.abs(backLPower),Math.abs(backRPower)))));

        frontL.setPower(frontLPower / maxPower);
        frontR.setPower(frontRPower / maxPower);
        backL.setPower(backLPower / maxPower);
        backR.setPower(backRPower / maxPower);
    } /// Functie pentru drive basic

    public void fieldMode(double forward, double strafe, double rotate){
        double t = Math.atan2(forward,strafe);
        double r = Math.hypot(strafe, forward);

        t = AngleUnit.normalizeRadians(t - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        double newForward = r * Math.sin(t);
        double newStrafe = r* Math.cos(t);

        this.drive(newForward, newStrafe, rotate);
    } /// Transformam din Drive Robot Relative in Drive Field Relative
}