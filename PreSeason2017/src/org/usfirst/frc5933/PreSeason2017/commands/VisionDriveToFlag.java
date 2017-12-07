package org.usfirst.frc5933.PreSeason2017.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is a challenge to any JudgeMent Call team member interested in software: Using the SocketVision object in
 * Robot.java, create a P controller (you will have to look this up. If it isn't easy to find, search PID or Proportion
 * controller) to guide the robot to the flag. Comment this well. Any constants should be placed in this class temporarily
 * and can be moved to the drivetrain subsystem later (and given their final values after testing). Everything you need to
 * create this command has already been given to you. Here's the criteria:
 * <p></p>
 * The command must exit if the limit switch on the front of the robot is closed or if a timer runs out.
 * <p></p>
 * The command must control the drivetrain in proportion to the error passed by the vision processing to stay as centered as possible on the flag.
 * <p></p>
 * The command must have a second constructor that takes parameters to set the vbus for the drivetrain and the max timeout.
 * <p></p>
 * You cannot change any code other than in this file. Again, all methods that you will need have been given to you.
 * Don't worry about how the command will be run, that will be handled after your code has been checked. You will probably
 * need to read through other commands (hint: DriveStraightGyro) and/or subsystems and the Robot class and/or google to brush up
 * on commands. Good luck!
 */
public class VisionDriveToFlag extends Command {

    public VisionDriveToFlag() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
