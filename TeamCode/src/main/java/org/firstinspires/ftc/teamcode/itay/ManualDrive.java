package org.firstinspires.ftc.teamcode.itay;

import static org.firstinspires.ftc.teamcode.itay.ExtendArm.extendArm;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class ManualDrive extends LinearOpMode {

    // region POSITION CONSTANTS
    // the positions of the servo for both the in and out arm positions\

    private static void setPosition(double heights) {
        grabberRight.setPosition(heights);
        grabberLeft. setPosition(heights);
    }

    static final double IN_POSITION = 0.55;
    static final double OUT_POSITION = 0.04;
    // endregion

    // region SERVO VARIABLES
    static Servo armRight;
    static Servo armLeft;
    // endregion

    static Servo grabberRight;
    static Servo grabberLeft;

    static Servo grabber;

    static void extendArm(double position){
        armLeft .setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
        armRight.setPosition((OUT_POSITION - IN_POSITION) * position + IN_POSITION);
    }

    @Override
    public void runOpMode(){

        grabberRight = hardwareMap.servo.get("grabberRight");
        grabberLeft  = hardwareMap.servo.get("grabberLeft" );

        DistanceSensor grabberDistanceToConeSensor = hardwareMap.get(DistanceSensor.class , "grabberDistanceToConeSensor");
        grabber = hardwareMap.servo.get("grabber");

        // region GET DRIVETRAIN MOTORS
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor frontLeft  = hardwareMap.dcMotor.get("frontLeft" );
        DcMotor backRight  = hardwareMap.dcMotor.get("backRight" );
        DcMotor backLeft   = hardwareMap.dcMotor.get("backLeft"  );
        // endregion

        // region INITIALIZE THE IMU
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
        // endregion

        // region SET MOTOR DIRECTION
        // reversing the right motors in order to have intuition for their movement direction
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight .setDirection(DcMotorSimple.Direction.REVERSE);
        // endregion

        grabberRight.setDirection(Servo.Direction.REVERSE);

        // region CREATE VARIABLES FOR DRIVE TRAIN
        double x1;
        double y1;
        double x2;
        double y2;
        double L;
        double beta;
        double alpha;
        //endregion

        // region SERVO INITIALIZATION
        // get servos from the hardware map
        armRight = hardwareMap.servo.get("armRight");
        armLeft  = hardwareMap.servo.get("armLeft" );

        // set the right servo to be reverse
        armRight.setDirection(Servo.Direction.REVERSE);



        final double[] heights = {0.7, 0.055, 0.06, 0.09, 0.13, 0.17};

        final double grabPosition = 0.40;
        final double releasePosition = 0.18;

        boolean dpad;
        boolean lastest_dpad = false;
        boolean dpad_click;
        boolean is_in = true;
        boolean trigger;
        boolean lastest_trigger = false;

        // set the servos to be in
        extendArm(0);
        // endregion

        setPosition(heights[0]);

        grabber.setPosition(releasePosition);

        // region WAIT FOR START
        waitForStart();
        if(isStopRequested()){
            return;
        }
        resetRuntime();
        // endregion



        while (opModeIsActive()) {

            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)

            x1 = gamepad1.left_stick_x;
            y1 = -gamepad1.left_stick_y;

            alpha = Math.atan2(y1, x1);
            beta = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
            L = Math.sqrt(x1 * x1 + y1 * y1);

            x2 = L * Math.cos(alpha + beta);
            y2 = L * Math.sin(alpha + beta);

            // region MOTOR POWER CALCULATION
            // calculating the motor powers based on the three basic movements (straight, lateral and turn)

            //                      [      straight       ] [       lateral       ] [         turn         ]
            double frontRightPower =          -y2           +          x2           + gamepad1.right_stick_x;
            double frontLeftPower  =          -y2           -          x2           - gamepad1.right_stick_x;
            double backRightPower  =          -y2           -          x2           + gamepad1.right_stick_x;
            double backLeftPower   =          -y2           +          x2           - gamepad1.right_stick_x;
            // endregion

            // region NORMALIZE MOTOR POWER

            // finds the highest absolute value of the non normalized motor powers
            double highestAbsoluteNum = Math.max(Math.max(Math.abs(frontRightPower), Math.abs(frontLeftPower)), Math.max(Math.abs(backRightPower), Math.abs(backLeftPower)));

            // if the motors aren't capable of the power requirement
            if(highestAbsoluteNum > 1) {
                // normalize the motor powers to be between -1 to 1
                frontRightPower /= highestAbsoluteNum;
                frontLeftPower  /= highestAbsoluteNum;
                backRightPower  /= highestAbsoluteNum;
                backLeftPower   /= highestAbsoluteNum;
            }
            // endregion

            // region SET MOTOR POWER
            frontRight.setPower(frontRightPower);
            frontLeft .setPower(frontLeftPower );
            backRight .setPower(backRightPower );
            backLeft  .setPower(backLeftPower  );
            // endregion

            // region give power for opening intake
            extendArm(gamepad1.left_trigger);
            // endregion


            dpad = lastest_dpad;
            lastest_dpad = gamepad1.dpad_up || gamepad1.dpad_right || gamepad1.dpad_down || gamepad1.dpad_left;
            dpad_click = lastest_dpad && !dpad;

            trigger = lastest_trigger;
            lastest_trigger = (gamepad1.left_trigger > 0.05);

            if (dpad_click) { is_in = !is_in; }

            if (lastest_trigger) {
                setPosition(heights[1]);
            } else if(trigger) {
                setPosition(heights[0]);
            } else if (dpad_click){
                if      (gamepad1.dpad_up   ) { setPosition(heights[5]); }
                else if (gamepad1.dpad_right) { setPosition(heights[4]); }
                else if (gamepad1.dpad_down ) { setPosition(heights[3]); }
                else if (gamepad1.dpad_left ) { setPosition(heights[2]); }
            } else if (!is_in) {
                setPosition(heights[0]);
            }



        }
    }
}