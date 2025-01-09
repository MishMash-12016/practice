package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lifter extends SubsystemBase {
    private DcMotor lifterMotor;

    // Define target positions (in encoder ticks)
    private static final int BOTTOM_POSITION = 0;
    private static final int MIDDLE_POSITION = 1000;
    private static final int TOP_POSITION = 2000;

    // Constructor
    public Lifter(HardwareMap hardwareMap) {
        lifterMotor = hardwareMap.get(DcMotor.class, "lifterMotor");
        lifterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lifterMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lifterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void moveToBottom() {
        lifterMotor.setTargetPosition(BOTTOM_POSITION);
        lifterMotor.setPower(1.0); // Adjust power as needed
    }

    public void moveToMiddle() {
        lifterMotor.setTargetPosition(MIDDLE_POSITION);
        lifterMotor.setPower(1.0);
    }

    public void moveToTop() {
        lifterMotor.setTargetPosition(TOP_POSITION);
        lifterMotor.setPower(1.0);
    }

    public void stop() {
        lifterMotor.setPower(0);
    }

    public boolean isAtTarget() {
        return !lifterMotor.isBusy();
    }

    public String getTargetPosition() {
        return getTargetPosition();
    }

    public String getCurrentPosition() {
        return getCurrentPosition() ;
    }
}

