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
	public final double kGyroProportionConst = 0.02; //The proportion constant for the drive train -- NEEDS TO BE TESTED
	
	/**
	 * The proportion constant for a P controller using the gyroscope to turn.
	 * MUST be tested in a game/field environment before competition. Each PID
	 * controller that we use may need unique constants, so name them appropriately.
	 */
	public final double kGyroTurnProportionConst = 12/180; //The proportion constant for the turns -- NEEDS TO BE TESTED
	
	/**
	 * The proportion constant for a P controller using the vision processing. MUST be
	 * tested in a game/field environment before competition. Each PID controller
	 * that we use may need unique constants, so name them appropriately.
	 */	
	public final double kVisionProportionConst = 0.003;
	
	/**
	 * The state of the Talon SRXs w.r.t. brake mode. True if brake mode is enabled.
	 */
	private boolean talonBrakeMode = false;
	
	/**
	 * The state of the Talons SRXs w.r.t. reversed mode. True if Talons are reversed.
	 */
	private boolean talonReverseMode = false;
	
	/**
	 * The limit that the motors can draw instantaneously to eliminate nasty shuddering.
	 */
	private final int ampLimit = 50;

	private double talonNominalOutput = +0f;
	private double talonPeakOutput = +12.0f;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon frontLeft = RobotMap.drivetrainFrontLeft;
	private final CANTalon backLeft = RobotMap.drivetrainBackLeft;
	private final CANTalon thirdLeft = RobotMap.drivetrainThirdLeft;
	private final CANTalon frontRight = RobotMap.drivetrainFrontRight;
	private final CANTalon backRight = RobotMap.drivetrainBackRight;
	private final CANTalon thirdRight = RobotMap.drivetrainThirdRight;
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
	public void sensorPeriodic() {
		SmartDashboard.putBoolean("Brake Mode Enabled: ", talonBrakeMode);
		SmartDashboard.putNumber("Joystick X: ", Robot.oi.driverStick.getX());
		SmartDashboard.putNumber("Joystick Y: ", Robot.oi.driverStick.getY());
	}

	/**
	 * This is a good example of why we use commands. Even though all the "movement" is handled by the 
	 * subsystem, it only happens when it is called by the command architecture. This is not in the Robot.teleop
	 * method because there is a chance that you may want to use the vision processing commands, for example, during
	 * teleop without interruption (or damage caused by interruption) from the driver bumping the joystick.
	 */
	public void teleopDrive() {
		robotDrive.arcadeDrive(Robot.oi.driverStick, false);
		
		//these two lines are a bit of cheating, because the robotDrive object cannot accept 6 motors.
		thirdLeft.set(frontLeft.get());
		thirdRight.set(frontRight.get());
	}


	/**
	 * Simple method to set every drivetrain motor to 0 (off)
	 */
	public void stopAll() {
		robotDrive.stopMotor();

		thirdLeft.set(frontLeft.get());
		thirdRight.set(frontRight.get());
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

		thirdLeft.set(frontLeft.get());
		thirdRight.set(frontRight.get());
	}
	
	public void arcadeDrive(double moveValue, double rotateValue) {
		robotDrive.arcadeDrive(moveValue, rotateValue, false);
		
		thirdLeft.set(frontLeft.get());
		thirdRight.set(frontRight.get());		
	}

	/**
	 * When brake mode is enabled (true has been passed into this method) the TalonSRXs will actively 
	 * resist continued movement after their vbus has been set to 0. This does drain more battery, especially
	 * if the robot gets into a pushing match with something else so it is recommended to not use it for the full
	 * match. Autonomous mode benefits most from brakemode, when the robot needs to be as consistent as possible.
	 * Brake mode is less important during teleop, because a good driver can adapt and even take advantage of 
	 * the robot's momentum.
	 * @param brakeMode
	 * Set to true to enable brakemode.
	 */
	public void setBrakeMode(boolean brakeMode) {
		talonBrakeMode = brakeMode; //on or off
		frontLeft.enableBrakeMode(brakeMode);
		frontRight.enableBrakeMode(brakeMode);
		backLeft.enableBrakeMode(brakeMode);
		backRight.enableBrakeMode(brakeMode);
		
		thirdLeft.enableBrakeMode(brakeMode);
		thirdRight.enableBrakeMode(brakeMode);
	}
	
	/**
	 * Sets the talons to a specified reverse mode. True inverts the drivetrain.
	 * We cannot use the CANTalon.reverseOutput() method because that only works in
	 * follower mode, for whatever reason.
	 */
	public void reverseTalons(boolean reverseMode) {
		talonReverseMode = reverseMode;
		
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, talonReverseMode);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, talonReverseMode);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, talonReverseMode);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, talonReverseMode);
	}
	
	public void config_nominal_and_max_voltages() {
		frontLeft.configNominalOutputVoltage(talonNominalOutput,-talonNominalOutput);
		frontLeft.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
		
		frontRight.configNominalOutputVoltage(talonNominalOutput,-talonNominalOutput);
		frontRight.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
		
		backLeft.configNominalOutputVoltage(talonNominalOutput,-talonNominalOutput);
		backLeft.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
		
		backRight.configNominalOutputVoltage(talonNominalOutput,-talonNominalOutput);
		backRight.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
		
		thirdLeft.configNominalOutputVoltage(talonNominalOutput, -talonNominalOutput);
		thirdLeft.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
		
		thirdRight.configNominalOutputVoltage(talonNominalOutput, -talonNominalOutput);
		thirdLeft.configPeakOutputVoltage(talonPeakOutput, -talonPeakOutput);
	}
	
	public void configCurrentLimit(boolean enable) {
		frontLeft.setCurrentLimit(ampLimit);
		frontLeft.EnableCurrentLimit(enable);
		
		frontRight.setCurrentLimit(ampLimit);
		frontRight.EnableCurrentLimit(enable);
		
		backLeft.setCurrentLimit(ampLimit);
		backLeft.EnableCurrentLimit(enable);
		
		backRight.setCurrentLimit(ampLimit);
		backRight.EnableCurrentLimit(enable);
		
		thirdLeft.setCurrentLimit(ampLimit);
		thirdLeft.EnableCurrentLimit(enable);
		
		thirdRight.setCurrentLimit(ampLimit);
		thirdLeft.EnableCurrentLimit(enable);
	}
}