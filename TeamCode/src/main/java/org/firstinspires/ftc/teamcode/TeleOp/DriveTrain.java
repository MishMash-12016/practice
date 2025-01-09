package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

@TeleOp(name = "DriveTrain teleop", group = "TeleOp")
public class DriveTrain extends OpMode {

    private Drivetrain drivetrain;

    @Override
    public void init() {
        // Initialize the drivetrain subsystem
        drivetrain = new Drivetrain(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Joystick inputs for movement
        double drive = -gamepad1.left_stick_y;  // Forward/backward (negative for forward)
        double strafe = gamepad1.left_stick_x; // Left/right movement
        double turn = gamepad1.right_stick_x;  // Rotation

        // Pass joystick values to drivetrain control
        drivetrain.drive(drive, strafe, turn);

        // Telemetry feedback for debugging
        telemetry.addData("Joystick Input", "Drive: %.2f, Strafe: %.2f, Turn: %.2f", drive, strafe, turn);
        telemetry.addData("Encoders", "FL: %d, FR: %d, BL: %d, BR: %d",
                drivetrain.getFrontLeftPosition(),
                drivetrain.getFrontRightPosition(),
                drivetrain.getBackLeftPosition(),
                drivetrain.getBackRightPosition());
        telemetry.update();
    }

    @Override
    public void stop() {
        // Ensure the drivetrain stops when the TeleOp ends
        drivetrain.stop();
    }
}
