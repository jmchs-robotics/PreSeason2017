package org.usfirst.frc5933.PreSeason2017.commands;

import org.usfirst.frc5933.PreSeason2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SimpleTurn extends Command {

	double angle; //the arc length of the turn
	double vbus; //the proportion of vbus for the fastest side
	double initialAngle;

	/**
	 * this is a simple, slow turn around the center of the robot to a specified number of degrees.
	 * Defaults to 45 degrees at .25 vbus. Account for drift, even if using brakeMode(true)
	 */
	public SimpleTurn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		//requires the drivetrain because, well, it drives the bot
		requires(Robot.drivetrain);
		//even though this uses the gyro from the roboRio, since the roboRio does not
		//control any motors, and there is only one thread in the program, commands
		//should not and don't need to require it.

		angle = 45;
		vbus = 0.25;
	}

	/**
	 * This is a simple turn around the center of the robot. Account for robot drift, even if using brakeMode(true).
	 * @param voltage
	 * - the vbus to drive the motors at
	 * @param degrees
	 * - the isFinished comparator
	 */
	public SimpleTurn(double voltage, double degrees) {
		requires(Robot.drivetrain);

		angle = degrees;
		vbus = voltage * Math.signum(angle); //this makes sure the vbus matches the signs appropriate to turn left or right
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.roboRio.resetGyro();
		initialAngle = Robot.roboRio.readGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.tankDrive(-vbus, vbus); //this may need to be switched.
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return angle >= initialAngle;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stopAll();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
