// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5933.PreSeason2017.subsystems;

import org.usfirst.frc5933.PreSeason2017.Robot;
import org.usfirst.frc5933.PreSeason2017.RobotMap;
import org.usfirst.frc5933.PreSeason2017.commands.*;
import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Drivetrain extends Subsystem {

	/**
	 * The proportion constant for a P controller using the gyroscope. MUST be
	 * tested in a game/field environment before competition. Each PID controller
	 * that we use may need unique constants, so name them appropriately.
	 */
	public final double kGyroProportionConst = 0.001; //The proportion constant for the drive train -- NEEDS TO BE TESTED
	
	private boolean talonBrakeMode = false;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon frontLeft = RobotMap.drivetrainFrontLeft;
	private final CANTalon backLeft = RobotMap.drivetrainBackLeft;
	private final CANTalon frontRight = RobotMap.drivetrainFrontRight;
	private final CANTalon backRight = RobotMap.drivetrainBackRight;
	private final RobotDrive robotDrive = RobotMap.drivetrainRobotDrive;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		setDefaultCommand(new DefaultTeleopCommand());

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	/**
	 * Put appropriate sensor outputs here if needed, or things to put on the SmartDashboard.
	 */
	public void periodic() {
		SmartDashboard.putBoolean("Brake Mode Enabled:", talonBrakeMode);
	}

	/**
	 * This is a good example of why we use commands. Even though all the "movement" is handled by the 
	 * subsystem, it only happens when it is called by the command architecture. This is not in the Robot.teleop
	 * method because there is a chance that you may want to use the vision processing commands, for example, during
	 * teleop without interruption (or damage caused by interruption) from the driver bumping the joystick.
	 */
	public void teleopDrive() {
		robotDrive.arcadeDrive(Robot.oi.driverStick);
	}


	public void stopAll() {
		robotDrive.stopMotor();
	}

	/**
	 * Use this method for direct control of all  motors in the drivetrain. Inversion for opposite 
	 * sides of the drivetrain is handled automatically.
	 * @param leftValue
	 * the vbus proportion to set the left motors to
	 * @param rightValue
	 * the vbus proportion to set the right motors to
	 */
	public void tankDrive(double leftValue, double rightValue) {
		robotDrive.tankDrive(leftValue, rightValue, false);
	}

	public void setBrakeMode(boolean brakeMode) {
		talonBrakeMode = brakeMode;
		frontLeft.enableBrakeMode(brakeMode);
		frontRight.enableBrakeMode(brakeMode);
		backLeft.enableBrakeMode(brakeMode);
		backRight.enableBrakeMode(brakeMode);
	}
}

