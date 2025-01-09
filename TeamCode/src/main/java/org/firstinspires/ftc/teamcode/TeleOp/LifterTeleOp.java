package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;

@TeleOp(name = "Lifter TeleOp", group = "TeleOp")
public class LifterTeleOp extends LinearOpMode {
    private Lifter lifter;

    @Override
    public void runOpMode() {
        lifter = new Lifter(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            // Control lifter with gamepad buttons
            if (gamepad1.a) {
                lifter.moveToBottom();
            } else if (gamepad1.b) {
                lifter.moveToMiddle();
            } else if (gamepad1.y) {
                lifter.moveToTop();
            } else if (gamepad1.x) {
                lifter.stop();
            }

            // Display telemetry for debugging
            telemetry.addData("Target Position", lifter.getTargetPosition());
            telemetry.addData("Current Position", lifter.getCurrentPosition());
            telemetry.addData("At Target", lifter.isAtTarget());
            telemetry.update();
        }
    }
}
