// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5933.PreSeason2017.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc5933.PreSeason2017.Robot;

/**
 *
 */
public class DriveStraightGyro extends Command {
	double feedForwardVBus;
	double seconds;
	double initialHeading;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveStraightGyro() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.roboRio);
        
        feedForwardVBus = -0.5;
        seconds = 5;
    }
    
    public DriveStraightGyro(double vbus, double timeout) {
    	requires(Robot.drivetrain);
    	requires(Robot.roboRio);
    	
    	feedForwardVBus = vbus;
    	seconds = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() { //on start, zero the gyro and set the default heading. The gyroscope should be calibrated by this point.
    	Robot.roboRio.resetGyro();
    	initialHeading = Robot.roboRio.readGyro();
    	setTimeout(seconds); //set the timeout of the command.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Proportion controllers are relatively simple: P = (constant)(error) + (default)
    	// since the drivetrain's "default" is different for each wheel, it is summed in the
    	// tankDrive method.
    	// error = current - initial
    	
    	double proportionOffset = Robot.drivetrain.kGyroProportionConst * (Robot.roboRio.readGyro() - initialHeading);
    
    	//the offset needs to be turned into vbus proportions for the two wheels.
    	//when the proportion is 0, the left and right gearboxes should run at the same vbus.
    	//when the proportion is not 0, one side of the drivetrain should speed up and the other slow down.
    	
    	//This is the setup for our robot this year. It may switch with different drivetrain setups.
    	Robot.drivetrain.tankDrive(feedForwardVBus - proportionOffset, feedForwardVBus + proportionOffset);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut(); //use the timeout methods inherent in commands for time-based execution. May change to thresholded acceleration or vision processing
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopAll(); //stop when done.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end(); //may as well stop when interrupted too.
    }
}
