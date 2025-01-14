package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.RollerIntake;

@TeleOp
        (name = "Roller Intake TeleOp", group = "TeleOp")
public class RollerIntakeTeleOp extends OpMode {
    private RollerIntake rollerIntake;

    @Override
    public void init() {
        rollerIntake = new RollerIntake(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        if (gamepad1.right_trigger > 0.1) {
            rollerIntake.intake(); // Pull objects in
        } else if (gamepad1.left_trigger > 0.1) {
            rollerIntake.outtake(); // Push objects out
        } else {
            rollerIntake.stop(); // Stop the intake
        }

        // Telemetry for debugging
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.update();
    }

    @Override
    public void stop() {
        rollerIntake.stop(); // Stop servos when TeleOp ends
    }
}
