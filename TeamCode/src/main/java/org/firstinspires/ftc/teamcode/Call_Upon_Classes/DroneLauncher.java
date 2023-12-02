package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.util.Timing;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

//Added Default Class for DroneLauncher Mechanism
public class DroneLauncher {
    private boolean hasLaunched = false;
    public ServoEx launcherServo = null;
    Timing.Timer clock = null;
    Haptic_Feedback feedback = null;
    private double startPos = 0.0;

    public void init_Launcher(HardwareMap hardwareMap, String name){
        //launcher = new Servo(hardwareMap, name);
        launcherServo = new SimpleServo(hardwareMap, name, 0, 180);
        clock = new Timing.Timer(10, TimeUnit.SECONDS);
        feedback = new Haptic_Feedback();
        startPos = launcherServo.getPosition();
        //launcherServo.turnToAngle(0);
    }

    public void run_Launcher(Gamepad gamepad1){
        //if the driver hold left bumper,
        boolean lbumper = gamepad1.left_bumper;
        boolean rbumper = gamepad1.right_bumper;
        boolean b_button = gamepad1.b;

        if(lbumper && rbumper && b_button){
            launcherServo.setPosition(0); //before - 180
            hasLaunched = true;

        } else {
            launcherServo.setPosition(100);
        }



    }

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Drone Launched: ", hasLaunched);
        telemetry.addData("Launcher Position", launcherServo.getPosition());
    }

}
