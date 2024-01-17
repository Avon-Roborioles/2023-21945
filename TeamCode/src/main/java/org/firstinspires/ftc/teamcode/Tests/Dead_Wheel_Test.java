//package org.firstinspires.ftc.teamcode.Tests;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.util.Encoder;
//
//
////@TeleOp
//@Disabled
//public class Dead_Wheel_Test extends LinearOpMode {
//    private Encoder leftEncoder, rightEncoder, frontEncoder;
//
//    @Override
//    public void runOpMode() throws InterruptedException {
//        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftFront")); //leftFront
//        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightRear")); //rightRear
//        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftRear"));
//
//        leftEncoder.setDirection(Encoder.Direction.REVERSE);
//        //frontEncoder.setDirection(Encoder.Direction.REVERSE);
//
//        waitForStart();
//
//        while(opModeIsActive()){
//            int leftPos = leftEncoder.getCurrentPosition();
//            int rightPos = rightEncoder.getCurrentPosition();
//            int frontPos = frontEncoder.getCurrentPosition();
//
//            telemetry.addData("leftPos: ", leftPos);
//            telemetry.addData("rightPos: ", rightPos);
//            telemetry.addData("frontPos: ", frontPos);
//            telemetry.update();
//        }
//
//    }
//}
