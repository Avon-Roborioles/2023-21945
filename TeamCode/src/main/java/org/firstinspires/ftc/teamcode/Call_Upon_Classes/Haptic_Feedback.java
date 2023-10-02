package org.firstinspires.ftc.teamcode.Call_Upon_Classes;
import com.qualcomm.robotcore.hardware.Gamepad;

/*
Useful methods to help alert drivers during matches
    - Alert drivers on endgame start
    - Keep Driver's aware of their speeds
    - Let Driver 2 know when they score a pixel with a quick vibration
 */
public class Haptic_Feedback {

    public void endGame_Timer(Gamepad gamepad1, Gamepad gampad2){
        //starts timer for engame
        //few seconds before engame, vibrate gamepads 3 times
    }

    public void alert(Gamepad gamepad1, Gamepad gamepad2){
        //alert vibration for general use
    }

    public void getTouchpad(Gamepad gamepad){
        //returns swipe combo name a driver did to their gamepad
        //useful for extra robot functions, percise movement, etc
    }
}
