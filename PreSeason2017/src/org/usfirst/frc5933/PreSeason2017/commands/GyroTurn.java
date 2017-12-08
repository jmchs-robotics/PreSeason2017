package org.usfirst.frc5933.PreSeason2017.commands;

import org.usfirst.frc5933.PreSeason2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurn extends Command {
	double targetHeading;	//the target in degrees
	double feedForwardVBus;	//the nominal vbus if on heading
	double timeout;			//the timeout if command overruns
	double tolerance;		//the range (in degrees) +/- the target that is acceptable. Must be non-zero and positive.
	double minVBus = 0.10;	//the minimum VBus proportion to actually move the bot

	public GyroTurn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.roboRio);

		feedForwardVBus = .5;
		targetHeading = 0;
		tolerance = 0.5;
	}

	public GyroTurn(double targetAngle, double vbus, double overrunTimeout, double degreesOfError) {
		requires(Robot.drivetrain);
		requires(Robot.roboRio);

		targetHeading = targetAngle;
		feedForwardVBus = vbus;
		timeout = overrunTimeout;
		tolerance = degreesOfError;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.roboRio.resetGyro();
		setTimeout(timeout);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//calculate the proportion that the motors need to turn by multiplying the gyro constant
		//by the current heading subtracted from the target heading.
		double proportionOffset = Robot.drivetrain.kGyroProportionConst * (Robot.roboRio.readGyro() - targetHeading);

		//the offset needs to be turned into vbus proportions for the two wheels.
		//when the proportion is 0, the left and right gearboxes should run at the same vbus.
		//when the proportion is not 0, one side of the drivetrain should speed up and the other slow down.

		//This is the setup for our robot this year. It may switch with different drivetrain setups.
		Robot.drivetrain.tankDrive(-feedForwardVBus + proportionOffset, feedForwardVBus - proportionOffset);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (targetHeading - tolerance < Robot.roboRio.readGyro()) && (Robot.roboRio.readGyro() < targetHeading + tolerance) || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
