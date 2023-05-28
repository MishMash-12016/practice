package org.firstinspires.ftc.teamcode.itay;

import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class Test extends LinearOpMode {

    public static double calculateShloshtreve(double high_now,double DST_of_ticks) {
        return (((DST_of_ticks - high_now) / 4) * 3);
    }

    public boolean tick_past(double shloshtreve, int currentPosition){
        return currentPosition > shloshtreve;
    }

    public static void elevatorMotors(double motorPower){
     motorMiddle.setPower(motorPower);
     motorRight.setPower(motorPower);
     motorLeft.setPower(motorPower);
    }

    static DcMotor motorRight;
    static DcMotor motorLeft;
    static DcMotor motorMiddle;

    @Override
    public void runOpMode() {

        DcMotor motorRight  = (DcMotorEx) hardwareMap.dcMotor.get("elevatorRight");
        DcMotor motorLeft   = (DcMotorEx) hardwareMap.dcMotor.get("elevatorLeft");
        DcMotor motorMiddle = (DcMotorEx) hardwareMap.dcMotor.get("elevatorMiddle");

        motorRight.setDirection(DcMotorSimple.Direction.REVERSE);

        motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        final int middlePosition = 11300;

        double CLOSE_ELEVATORE_POSITION = -motorLeft.getCurrentPosition();

        boolean anotherHeight = false;

        boolean check_shloshtreve = false;

        double shloshtreveWay = 0;

        // region WAIT FOR START
        waitForStart();
        if (isStopRequested()) {
            return;
        }
        resetRuntime();
        // endregion


        while (opModeIsActive()) {
            if (gamepad1.a) {
                elevatorMotors(0.3);
            }

        }
    }
}
