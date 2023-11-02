package org.firstinspires.ftc.teamcode.Tests;

//tuner program used solely to tune Robot Arm PID Control
//video guide I used to create this program --> https://youtu.be/E6H6Nqe6qJo?si=MmiNmgoH_SwRyxtt
/*
Steps to Turn Arm:
    * add a target value (start with 500)
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
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
public class PID_Arm_Config extends OpMode {
    private PIDController controller;

    public static double p = 0, i = 0, d = 0; //PID variables needed
    public static double f = 0; //feed forward variable

    public static int target = 0; //the variable team drivers will control to move arm

    private final double ticks_in_degree = 700 / 180.0; //need to check motors to be accurate

    private DcMotorEx armMotor;

    @Override
    public void init() {
        controller = new PIDController(p, i, d);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        armMotor = hardwareMap.get(DcMotorEx.class, "arm");
    }

    @Override
    public void loop() {
        controller.setPID(p, i, d);
        int armPos = armMotor.getCurrentPosition();
        double pid = controller.calculate(armPos, target);
        double ff = Math.cos(Math.toRadians(target / ticks_in_degree)) * f;

        double power = pid + ff;

        armMotor.setPower(power);

        telemetry.addData("Current Position: ", armPos);
        telemetry.addData("Target Position: ", target);
        telemetry.update();
    }
}
