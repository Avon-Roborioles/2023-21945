package org.firstinspires.ftc.teamcode.Call_Upon_Classes;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

// - RoadRunner imports from last year
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import org.firstinspires.ftc.teamcode.drive.DriveConstants;
//import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;


public class Distance_Sensor {
    //add in constructor class when needed
//    public Distance_Sensor(String sensor_name){
//
//    }
    private Rev2mDistanceSensor d_sensor = null;
    private Telemetry telemetry = null;
    private double distance = 0.0;

    public void initDistance(HardwareMap hardwareMap, Telemetry telemetry) {
         d_sensor = hardwareMap.get(Rev2mDistanceSensor.class,"ds1");
        this.telemetry = telemetry;

    }

    public void runDistance() {
        //updates distance variable
        distance = d_sensor.getDistance(DistanceUnit.INCH);
    }

    public double getDistance(){
        //lets other classes read but not write from the distance variable
        return distance;
    }

    //useful function to check if robot is greater than or less than distance sensor value
    public boolean checkDistance(double limit, boolean checkGreater){
        if(checkGreater){
            if(distance > limit){
                return true;
            } else {
                return false;
            }
        } else {
            if(distance < limit){
                return true;
            } else{
                return false;
            }
        }
    }

    public void getTelemetry() {
        telemetry.addData("Distance", getDistance());
    }
}
