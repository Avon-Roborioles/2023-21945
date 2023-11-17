package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.arcrobotics.ftclib.util.Timing;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.arcrobotics.ftclib.util.Timing.Timer;

import java.util.concurrent.TimeUnit;

/*
Useful methods to help alert drivers during matches
    - Alert drivers on endgame start
    - Keep Driver's aware of their speeds
    - Let Driver 2 know when they score a pixel with a quick vibration
 */
public class Haptic_Feedback {

    public void endGame_Alert(Gamepad gamepad1, Gamepad gamepad2){
        //starts timer for engame
        //few seconds before engame, vibrate gamepads 3 times
        gamepad1.rumble(5000);
        gamepad2.rumble(5000);

    }

    public void alert_driver(Gamepad gamepad){
        gamepad.rumble(2000);
    }

    public void alert_drivers(Gamepad gamepad1, Gamepad gamepad2){
        gamepad1.rumble(2000);
        gamepad2.rumble(2000);

    }


    public void countdown(Gamepad gamepad, int seconds){

    } //TODO - add countdown rumble effect

    public void get_Touchpad_Combo(Gamepad gamepad){
        //returns swipe combo name a driver did to their gamepad
        //useful for extra robot functions, percise movement, etc
    } //TODO - add code for touchpad combo feature
}
