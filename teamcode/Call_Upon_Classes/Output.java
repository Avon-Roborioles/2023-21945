package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import org.firstinspires.ftc.robotcore.external.Telemetry;

//Added Default Class for Output Mechanism
public class Output {
    private boolean isActive = false;

    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Currently Moving: ", isActive);
    }

}
