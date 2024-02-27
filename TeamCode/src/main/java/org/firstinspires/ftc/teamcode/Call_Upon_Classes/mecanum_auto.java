package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
A Backup auto class in case libraries like RoadRunner or Silver Surfer
 (New Secretive Library Stephen will experiment with during the 2024 Summer Off-Season)
  don't work
 */

public class mecanum_auto {
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;
    private DcMotor x_encoder=null;
    private DcMotor y_encoder=null;
    private Telemetry telemetry = null;
    private int i = 0;


    public void init_encoders(HardwareMap hardwareMap){
        x_encoder = hardwareMap.get(DcMotor.class, "x");
        y_encoder = hardwareMap.get(DcMotor.class, "y");
    }
/*
for deadwheels
D=35mm
C=110mm/2.80in
1 rotation = 8192 ticks
thus 8192 ticks is 2.8 in
2925 ticks is approx 1 inch
 */
/*
make one here for normal wheels
 */

    private void init_individual_motor(DcMotor motor, boolean isLeft){
        if (isLeft) motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void init_auto_drive_motors(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        fl = hardwareMap.get(DcMotor.class, "fl");
        init_individual_motor(fl, true);

        fr = hardwareMap.get(DcMotor.class, "fr");
        init_individual_motor(fr, false);

        bl = hardwareMap.get(DcMotor.class, "bl");
        init_individual_motor(bl, true);

        br = hardwareMap.get(DcMotor.class, "br");
        init_individual_motor(br, false);
    }

    public void setRelativeTargetAll(int target) {
        fl.setTargetPosition(fl.getCurrentPosition() + target);
        bl.setTargetPosition(bl.getCurrentPosition() + target);
        fr.setTargetPosition(fr.getCurrentPosition() + target);
        br.setTargetPosition(br.getCurrentPosition() + target);
    }

    public void setRelativeTargetIndividual(int fl_target, int bl_target, int fr_target, int br_target) {
        fl.setTargetPosition(fl_target + fl.getCurrentPosition());
        bl.setTargetPosition(bl_target + bl.getCurrentPosition());
        fr.setTargetPosition(fr_target + fr.getCurrentPosition());
        br.setTargetPosition(br_target + br.getCurrentPosition());
    }

    public void setPowerAll(double power) {
        fl.setPower(power);
        bl.setPower(power);
        fr.setPower(power);
        br.setPower(power);
    }

    public void setPowerIndividual(double FL, double FR, double BR, double BL){
        fl.setPower(FL);
        br.setPower(BR);
        bl.setPower(BL);
        fr.setPower(FR);

    }

    public void read_encoders(){
        y_encoder.getCurrentPosition();
        x_encoder.getCurrentPosition();
    }


    public void goToSpot(int inches, double power){
        inches*=91;
        setRelativeTargetAll(inches);
        setPowerAll(power);
//        while (isBusy()){}
    }

    public void stopMotors() {setPowerAll(0);}

    public void turn90left (double power){
        setRelativeTargetIndividual((int) Math.floor(-1440*1.2),(int) Math.floor(-1440*1.2),(int) Math.floor(1440*1.2), (int) Math.floor(1440*1.2));
        setPowerIndividual(-power,power,power,-power);
        while (isBusy()){}
    }
    public void turn90right (double power){
        setRelativeTargetIndividual((int) Math.floor(1440*1.2),(int) Math.floor(1440*1.2),(int) Math.floor(-1440*1.2), (int) Math.floor(-1440*1.2));
        setPowerIndividual(power,-power,-power,power);
        while (isBusy()){}
    }
    public void turn45left (double power){
        setRelativeTargetIndividual((int) Math.floor(-1440*1.2*.5),(int) Math.floor(-1440*1.2*.5),(int) Math.floor(1440*1.2*.5), (int) Math.floor(1440*1.2*.5));
        setPowerIndividual(-power,power,power,-power);
        while (isBusy()){}
    }
    public void turn45right (double power){
        setRelativeTargetIndividual((int) Math.floor(1440*1.2*.5),(int) Math.floor(1440*1.2*.5),(int) Math.floor(-1440*1.2*.5), (int) Math.floor(-1440*1.2*.5));
        setPowerIndividual(power,-power,-power,power);
        while (isBusy()){}
    }

    public void strafeLeft(double power, double inches) {
        //107 ticks= 1 inch
        inches*=107;
        setRelativeTargetIndividual((int)-inches,(int) inches,(int)inches,(int)-inches);
        setPowerIndividual(-power, power, -power, power);
        while (isBusy()){}
    }
    public void strafeRight(double power, double inches) {
        inches*=107;
        setRelativeTargetIndividual((int)inches,(int)-inches,(int)-inches,(int)inches);
        setPowerIndividual(power, -power, power, -power);
        while (isBusy()){}
    }

    // probably should re-write this one
    public boolean isBusy (){
//        getTelemetry(telemetry);
        int totalBusy=0;
        if (fl.isBusy())
            totalBusy++;
        if (bl.isBusy())
            totalBusy++;
        if (br.isBusy())
            totalBusy++;
        if (fr.isBusy())
            totalBusy++;

        if (totalBusy>=4)
            return true;
        else
            return false;
    }

    public void getTelemetry (Telemetry telemetry){
        telemetry.addData("fl encoder value: ",fl.getCurrentPosition());
        telemetry.addData("fr encoder value: ",fr.getCurrentPosition());
        telemetry.addData("bl encoder value: ",bl.getCurrentPosition());
        telemetry.addData("br encoder value: ",br.getCurrentPosition());
    }

    //move method takes axis the bot is meant to move on and the amount of tiles it is meant to move (and simplify calling)
    public void move(String axis, double tiles) {
        //determine what axis wants to be moved on
        if (axis == "x") {
            //determines which direction the robot need to travel (since negative traveled time doesn't mean negative direction)
            if (tiles > 0) {
                //add motion that takes timePoweredOutput as the time the motors have to be powered for to move forward the desired amount of tiles
                strafeRight(0.5, 24*tiles);
            }
            else if (tiles < 0) {
                //add motion that takes timePoweredOutput as the time the motors have to be powered for to move backward the desired amount of tiles
                strafeLeft(0.5, 24*java.lang.Math.abs(tiles));
            }
        }
        else if (axis == "y") {
            //determines which direction the robot need to travel (since negative traveled time doesn't mean negative direction)
            if (tiles > 0) {
                //add motion that takes timePoweredOutput as the time the motors have to be powered for to move right the desired amount of tiles
                for (int i=0; tiles > i; i++) {
                    goToSpot(24, 0.5);
                }
            }
            else if (tiles < 0) {
                //add motion that takes timePoweredOutput as the time the motors have to be powered for to move left the desired amount of tiles
                for (int i=0; tiles < i; i--) {
                    goToSpot((int) (-24* Math.abs(tiles)), -0.5);
                }
//                for (int i; tiles > i; i++) {
//                    goToSpot(24, 0.5);
//                }
            }
            else if (tiles < 0) {
                //add motion that takes timePoweredOutput as the time the motors have to be powered for to move left the desired amount of tiles
//                for (int i; tiles < i; i--) {
////                    goToSpot(-24*java.lang.Math.abs(tiles), -0.5);
//                }
            }
        }
    }

    //takes integer that multiplies 90 degree turns
    public void turn(int turnAmount) {
        if (turnAmount > 0) {
            for (int i=0; turnAmount > i; i++) {
                turn90right(0.5);
            }
        }
        else if (turnAmount < 0) {
            for (int i=0; turnAmount < i; i--) {
                turn90left(0.5);
            }
        }
    }
//    public void turn(int turnAmount) {
//        if (turnAmount > 0) {
//            for (int i; turnAmount > i; i++) {
//                turn90right(0.5);
//            }
//        }
//        else if (turnAmount < 0) {
//            for (int i; turnAmount < i; i--) {
//                turn90left(0.5);
//            }
//        }
}
