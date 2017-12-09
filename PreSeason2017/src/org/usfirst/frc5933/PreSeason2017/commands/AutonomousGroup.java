package org.usfirst.frc5933.PreSeason2017.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGroup extends CommandGroup {

    public AutonomousGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	/*
    	 * the order of events in autonomous is:
    	 * 1. drive forward to the flag.
    	 * 2. grab the flag.
    	 * 3. drive backwards to the flag zone.
    	 * 4. release flag
    	 * 5. turn away from flag
    	 * 6. drive away from flag
    	 * 
    	 * These can be further divided into:
    	 * 1. gyro drive forward to about 5 feet of the flag
    	 * 2. use vision processing to drive to the flag
    	 * 3. grab the flag
    	 * 4. gyro drive backward to the flag zone
    	 * 5. release flag
    	 * 6. turn away from flag
    	 * 7. drive away from flag
    	 * 
    	 * Note that timings are going to be inconsistent due to the variable nature of the field.
    	 * Basically, because the field is bumpy, we can't time it so well.
    	 */
    	
    	//open claws as a "reset" action
    	addParallel(new OpenAllClaws());
    	
    	//drive forward to the flag
    	addSequential(new DriveStraightGyro(-.3,7.2));
    	
    	//drive to the flag
    	addSequential(new VisionDriveToFlag(.2,5)); //the claws will trigger automagically if the targeting is on.
    	
    	//grab the flag
    	addSequential(new CloseAllClaws());
    	
    	//drive backwards to the flag zone
    	addSequential(new DriveStraightGyro(.3,7.2));
    	
    	//release the flag
    	addParallel(new OpenAllClaws());
    	
    	//turn away from the flag
    	addSequential(new GyroTurn(90,0,3,0.2));
    	
    	//drive away from the flag
    	addSequential(new DriveStraightGyro(.3,2));
    }
}
