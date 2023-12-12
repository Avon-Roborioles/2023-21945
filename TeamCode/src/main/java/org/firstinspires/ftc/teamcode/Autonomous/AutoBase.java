package org.firstinspires.ftc.teamcode.Autonomous;
//Added Default Class for Basic Auto Functions (Used throughout all Autonomous Programs

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.*;

public class AutoBase extends LinearOpMode {
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Arm arm = new Arm();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Intake intake = new Intake();
    protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Camera_Vision vision = new Camera_Vision();
    //protected org.firstinspires.ftc.teamcode.Call_Upon_Classes.Distance_Sensor distance_sensor = new Distance_Sensor();


    @Override
    public void runOpMode() throws InterruptedException {
    }

    public void init_classes(){
        arm.init_arm_main(hardwareMap, "leftMotor", "rightMotor", true);
        intake.init_intake_main(hardwareMap, "claw", "wrist", "pixelHolder", true);
        vision.init_cameras(hardwareMap, "Webcam 1", "Webcam 2");
    }

    public void scorePixel(){
        arm.auto_score();
        intake.auto_score();
    } //TEST - multi-function method used to score pixel on backboard

}
