package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.concurrent.TimeUnit;

/*
Useful methods to help alert drivers during matches
    - Alert drivers on endgame start
    - Keep Driver's aware of their speeds
    - Let Driver 2 know when they score a pixel with a quick vibration
 */
public class Haptic_Feedback {
    Timing.Timer teleOpTimer;

    public void init_Timer(){
        teleOpTimer = new Timing.Timer(90, TimeUnit.SECONDS);
    }

    public void run_Timer(Gamepad gamepad1, Gamepad gamepad2){
        if(teleOpTimer.remainingTime() == 85){
            gamepad1.rumbleBlips(3);
            gamepad2.rumbleBlips(3);
        }
    }

    public void alert_driver(Gamepad gamepad){
        gamepad.rumble(500);
    }
}
