package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RollerIntake extends SubsystemBase {

    private CRServo leftServo;  // Servo on Control Hub
    private CRServo rightServo; // Servo on Expansion Hub

    // Constants for CR Servo speeds
    public static final double INTAKE_SPEED = 1.0;  // Full forward
    public static final double OUTTAKE_SPEED = -1.0; // Full reverse
    public static final double STOP_SPEED = 0.0;     // Stop

    /**
     * Constructor for the RollerIntake subsystem.
     *
     * @param hardwareMap The hardware map from the OpMode
     */
    public RollerIntake(HardwareMap hardwareMap) {
        initializeServos(hardwareMap);
        stop(); // Ensure servos are stopped after initialization
    }

    /**
     * Initializes the two CR servos connected to the Control Hub and Expansion Hub.
     *
     * @param hardwareMap The hardware map from the OpMode
     */
    private void initializeServos(HardwareMap hardwareMap) {
        // Initialize servos using their names in the Robot Configuration
        leftServo = hardwareMap.get(CRServo.class, "RS");   // Servo on Control Hub
        rightServo = hardwareMap.get(CRServo.class, "LS"); // Servo on Expansion Hub

        // Reverse direction of one servo if needed
        rightServo.setDirection(CRServo.Direction.REVERSE);
    }

    /**
     * Stops the intake system.
     */
    public void stop() {
        leftServo.setPower(STOP_SPEED);
        rightServo.setPower(STOP_SPEED);
    }

    /**
     * Runs the intake to pull objects in.
     */
    public void intake() {
        leftServo.setPower(INTAKE_SPEED);
        rightServo.setPower(INTAKE_SPEED);
    }

    /**
     * Runs the intake to push objects out.
     */
    public void outtake() {
        leftServo.setPower(OUTTAKE_SPEED);
        rightServo.setPower(OUTTAKE_SPEED);
    }
}
