package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.subsystems.Lifter;
import org.firstinspires.ftc.teamcode.subsystems.RollerIntake;

@TeleOp
        (name = "Single Gamepad TeleOp", group = "TeleOp")
public class cycleTeleOp extends OpMode {
    // Subsystem declarations
    private Drivetrain drivetrain;
    private Lifter lifter;
    private RollerIntake rollerIntake;

    @Override
    public void init() {
        // Initialize subsystems
        drivetrain = new Drivetrain(hardwareMap);
        lifter = new Lifter(hardwareMap);
        rollerIntake = new RollerIntake(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // ---- Drivetrain Control ----
        double drive = -gamepad1.left_stick_y; // Forward/backward
        double strafe = gamepad1.left_stick_x; // Left/right
        double turn = gamepad1.right_stick_x; // Rotation
        drivetrain.drive(drive, strafe, turn);

        // ---- Lifter Control ----
        if (gamepad1.dpad_up) {
            lifter.moveToBottom(); // Move lifter up to position 1000
        } else if (gamepad1.dpad_down) {
            lifter.moveToMiddle(); // Move lifter down to position 0
        } else if (gamepad1.dpad_left) {
            lifter.moveToTop(); // Manual up control
        } else {
            lifter.stop(); // Stop the lifter
        }

        // ---- Roller Intake Control ----
        if (gamepad1.right_trigger > 0.1) {
            rollerIntake.intake(); // Run intake to pull objects in
        } else if (gamepad1.left_trigger > 0.1) {
            rollerIntake.outtake(); // Run intake to push objects out
        } else {
            rollerIntake.stop(); // Stop the intake
        }

        // ---- Telemetry ----
        telemetry.addData("Drive", "Drive: %.2f, Strafe: %.2f, Turn: %.2f", drive, strafe, turn);
        telemetry.addData("Lifter", "Position: %d", lifter.getCurrentPosition());
        telemetry.addData("Intake", "Intake running: %s",
                gamepad1.right_trigger > 0.1 ? "Yes" : gamepad1.left_trigger > 0.1 ? "Outtake" : "No");
        telemetry.update();
    }

    @Override
    public void stop() {
        drivetrain.stop(); // Stop drivetrain
        lifter.stop(); // Stop lifter
        rollerIntake.stop(); // Stop intake
    }
}
