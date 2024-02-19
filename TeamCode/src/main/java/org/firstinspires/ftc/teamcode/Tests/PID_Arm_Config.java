package org.firstinspires.ftc.teamcode.Tests;

//tuner program used solely to tune Robot Arm PID Control
//video guide I used to create this program --> https://youtu.be/E6H6Nqe6qJo?si=MmiNmgoH_SwRyxtt
/*
Steps to Turn Arm:
    * CHECK TO MAKE SURE LEFT MOTOR AS POSITIVE VALUES WHEN ARM IS UP
    * add a target value (start with 200)
    * add a value for f (start with 0.05)
    * adjust f value in FTC Dashboard so arm can resist gravity when you move it (holds in place)
    * slowly adjust p value so arm accurately holds position at target - close enough as possible
    * adjust d value so arm moves smoothly (acts as a dampener on arm movements - sorry for the spelling lol)
    * play around with target value to make sure arm is good to go
    * lastly, copy p,i,d values and put in arm class!
 */
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(name="PID Arm Config", group = "Tests")
public class PID_Arm_Config extends OpMode {
    private PIDController controller;

    public static double p = 0.0023, i = 0, d = 0.0008; //PID variables needed
    public static double f = 0.005; //feed forward variable

    public static int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    private DcMotorEx leftMotor;
    private DcMotorEx rightMotor;
    private DcMotorEx wristMotor;

    @Override
    public void init() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        leftMotor = hardwareMap.get(DcMotorEx.class,"leftMotor");
        rightMotor = hardwareMap.get(DcMotorEx.class, "rightMotor");
        wristMotor = hardwareMap.get(DcMotorEx.class,"wrist");

//        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //sets to 0
//        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);//sets to 0
//
//        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //leftMotor.setDirection(DcMotorSimple.Direction.REVERSE); //sets leftMotor in reverse
    }

    @Override
    public void loop() {
        wristMotor.setPower(-.6);
        controller.setPID(p, i, d);
        int leftArmPos = leftMotor.getCurrentPosition();
        int rightArmPos = rightMotor.getCurrentPosition();
        double pid = controller.calculate(rightArmPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        leftMotor.setPower(power);
        rightMotor.setPower(power);

        telemetry.addData("Left Arm Position: ", -leftArmPos);
        telemetry.addData("Right Arm Position: ", rightArmPos);
        telemetry.addData("Target Position: ", target);
        telemetry.addData("Power: ", power);
        telemetry.update();
    }
}
