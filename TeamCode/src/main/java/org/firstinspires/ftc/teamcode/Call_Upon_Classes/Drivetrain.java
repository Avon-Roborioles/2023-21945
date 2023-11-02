package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Drivetrain {

    private boolean strafe;
    private int strafe_set=1;

    //right == 1
    //left  == -1
    private double denominator;
    private double ly;
    private double lx;
    private double rx;
    private double lt;
    private double rt;
    private DcMotor fl = null;
    private DcMotor bl = null;
    private DcMotor fr = null;
    private DcMotor br = null;
    private DcMotor x_encoder = null;

    /**
     *
     * @param right_strafe if you want right strafe set true, left set false
     */
    public Drivetrain(boolean right_strafe){
        strafe=right_strafe;
        if (strafe)
            strafe_set = 1;
        else
            strafe_set = -1;

    }


    public void init_drive_motors(HardwareMap hardwareMap) {
        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");
//        x_encoder = hardwareMap.get(DcMotor.class, "x");
        bl.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        fl.setDirection(DcMotor.Direction.REVERSE); // maybe reverse
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    //old
    public void run_drive_motors(Gamepad gamepad1, Telemetry telemetry){
        ly=-1 * gamepad1.left_stick_y; //switched left_stick y with left stick x
        lx=gamepad1.left_stick_x;
        rx=gamepad1.right_stick_x;
        denominator = Math.max(Math.abs(ly)+Math.abs(lx)+Math.abs(rx), 1);

        fl.setPower((ly+lx+rx)/denominator);
        bl.setPower((ly+lx* strafe_set -rx* strafe_set )/denominator);
        fr.setPower((ly-lx-rx)/denominator);
        br.setPower((ly-lx* strafe_set +rx* strafe_set )/denominator);

        getTelemetry(telemetry);

    }

    public void run_drive_motors_15(Gamepad gamepad1, Telemetry telemetry){
        ly=gamepad1.left_stick_y;
        lx=gamepad1.left_stick_x;
        rx=gamepad1.right_stick_x;

        if (Math.abs(lx)>Math.abs(ly)) {//x power only
            fl.setPower(lx+rx);
            fr.setPower(-lx-rx);
            br.setPower(lx-rx);
            bl.setPower(-lx+rx);

        }else{//y power only
            fl.setPower(-ly+rx);
            fr.setPower(-ly-rx);
            bl.setPower(-ly+rx);
            br.setPower(-ly-rx);
        }

        getTelemetry(telemetry);
    }


    public void auto_forward (double inch) {
//        while () {
//
//        }
    }

    public void getTelemetry (@NonNull Telemetry telemetry){
        telemetry.addData("fl power: ",fl.getPower());
        telemetry.addData("fr power: ",fr.getPower());
        telemetry.addData("bl power: ",bl.getPower());
        telemetry.addData("br power: ",br.getPower());
//        telemetry.addData("X Dead Wheel Encoder Value: ",x_encoder.getCurrentPosition());


    }

}

