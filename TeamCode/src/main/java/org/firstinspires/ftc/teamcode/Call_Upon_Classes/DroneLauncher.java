package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.util.Timing;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.Haptic_Feedback;


import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

//Added Default Class for DroneLauncher Mechanism
public class DroneLauncher {
    private boolean hasLaunched = false;
    public ServoEx launcher = null;
    Timing.Timer clock = null;
    Haptic_Feedback feedback = null;

    public void init_Launcher(HardwareMap hardwareMap, String name){
        //launcher = new Servo(hardwareMap, name);
        launcher = new SimpleServo(hardwareMap, name, 0, 180);
        clock = new Timing.Timer(10, TimeUnit.SECONDS);
        feedback = new Haptic_Feedback();

    }

    public void run_Launcher(Gamepad gamepad1){
        //if the driver hold left bumper,
        if(gamepad1.left_bumper && gamepad1.right_bumper && gamepad1.b){
            launcher.setPosition(180);
            hasLaunched = true;
            clock.start();
            feedback.countdown(gamepad1, 10);
        }

        if(clock.done()){
            launcher.setPosition(0);
        }


    }

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Drone Launched: ", hasLaunched);
    }

}
