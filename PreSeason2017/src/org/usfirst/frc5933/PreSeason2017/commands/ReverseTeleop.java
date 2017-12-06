package org.usfirst.frc5933.PreSeason2017.commands;

import org.usfirst.frc5933.PreSeason2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseTeleop extends Command {

    public ReverseTeleop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.reverseTalons();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
