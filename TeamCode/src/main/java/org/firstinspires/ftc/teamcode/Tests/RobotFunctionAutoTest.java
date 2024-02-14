package org.firstinspires.ftc.teamcode.Tests;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.teamcode.Call_Upon_Classes.PoseStorage;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name="Robot Function Auto Test", group="Tests")
public class RobotFunctionAutoTest extends org.firstinspires.ftc.teamcode.Autonomous.AutoBase{
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive bot = new SampleMecanumDrive(hardwareMap);

        init_classes();

        bot.setPoseEstimate(PoseStorage.startPoseRL);

//        Trajectory test2 = bot.trajectoryBuilder(new Pose2d())
//                .back(6)
//                .addDisplacementMarker(()->{
//                    arm.setTarget(2000);
//                })
//                .build();
        TrajectorySequence test = bot.trajectorySequenceBuilder(new Pose2d())
                .forward(3)
                .addDisplacementMarker(()->{
                    arm.up();
                })
                .waitSeconds(3)
                .back(3)
                .addDisplacementMarker(()->{
                    arm.down();
                })
                .build();


        waitForStart();

        bot.followTrajectorySequenceAsync(test);
        //bot.followTrajectoryAsync(test2);

        //arm.setTarget(300);
        //arm.update(2000);


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
