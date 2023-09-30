package org.firstinspires.ftc.teamcode.Call_Upon_Classes;


import org.firstinspires.ftc.robotcore.external.Telemetry;

//Added Default Class for DroneLauncher Mechanism
public class DroneLauncher {
    private boolean hasLaunched = false;


    public void getTelemetry(Telemetry telemetry){
        telemetry.addData("Drone Launched: ", hasLaunched);
    }

}
