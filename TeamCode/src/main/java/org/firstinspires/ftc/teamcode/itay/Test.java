package org.firstinspires.ftc.teamcode.itay;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        if (isStopRequested()) {
            return;
        }

        resetRuntime();

        while (opModeIsActive()) {

        }
    }
}
