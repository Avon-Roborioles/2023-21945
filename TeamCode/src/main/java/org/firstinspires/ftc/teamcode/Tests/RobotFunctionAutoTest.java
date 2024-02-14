package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous(name="Robot Function Auto Test", group="Tests")
public class RobotFunctionAutoTest extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        init_classes();

        bot.setPoseEstimate(PoseStorage.startPoseRL);

        Trajectory test2 = bot.trajectoryBuilder(new Pose2d())
                .back(1)
                .addDisplacementMarker(()->{
                    arm.setTarget(300);
                })
                        .build();

        waitForStart();

       // bot.followTrajectorySequenceAsync(test);
        bot.followTrajectoryAsync(test2);

        while(opModeIsActive()){
            bot.update(); //handles pathing logic
            arm.update(); //handles arm PID controller
            telemetry.addData("Target: ",arm.target);
            telemetry.addData("PID Power: ", arm.pidPower);
            telemetry.addData("Right Arm Pose: ", arm.rightMotorPosition);
            telemetry.update();

        }
    }
    }
