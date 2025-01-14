package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.roadrunner.trajectorysequence.sequencesegment.WaitSegment;

public class meepmeep {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder (new Pose2d(9.80, -62.40, Math.toRadians(0.00)))

                                .splineToSplineHeading(new Pose2d(2.20, -35.80, Math.toRadians(92.20)), Math.toRadians(92.20))
                        .turn(Math.toRadians(180))
                                .splineTo(new Vector2d(16.80, -46.80), Math.toRadians(-24.59))
                                .splineTo(new Vector2d(40.20, -49.40), Math.toRadians(-8.90))
                                .splineToSplineHeading(new Pose2d(49.00, -37.80, Math.toRadians(90.00)), Math.toRadians(90.00))
                        .turn(Math.toRadians(180))
                                .splineToSplineHeading(new Pose2d(47.80, -57.60, Math.toRadians(270)), Math.toRadians(90.00))
                                .splineToLinearHeading(new Pose2d(58.80, -38.60, Math.toRadians(270.00)), Math.toRadians(90))
                                .splineTo(new Vector2d(58.20, -58), Math.toRadians(90))

                        .build());


        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}