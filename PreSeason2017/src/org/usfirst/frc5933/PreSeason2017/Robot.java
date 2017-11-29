// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc5933.PreSeason2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc5933.PreSeason2017.SocketVision;
import org.usfirst.frc5933.PreSeason2017.commands.*;
import org.usfirst.frc5933.PreSeason2017.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;

    public static final boolean show_debug_vision = true;
    
    public static SocketVision frontWatcher;
    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Pneumatics pneumatics;
    public static Drivetrain drivetrain;
    public static RoboRio roboRio;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        pneumatics = new Pneumatics();
        drivetrain = new Drivetrain();
        roboRio = new RoboRio();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new DefaultAutonomousCommand();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	if (frontWatcher != null) {
			try {
				frontWatcher.stoprunning();
				frontWatcher.join();
				frontWatcher = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        
        drivetrain.sensorPeriodic();
        pneumatics.sensorPeriodic();
        roboRio.sensorPeriodic();
    }

    public void autonomousInit() {
    	drivetrain.setBrakeMode(true);
    	visionInit();
        // schedule the autonomous command (example)    	
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        drivetrain.sensorPeriodic();
        pneumatics.sensorPeriodic();
        roboRio.sensorPeriodic();
    }

    public void teleopInit() {
    	visionInit();
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        drivetrain.setBrakeMode(false);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        LiveWindow.run();
        
        drivetrain.sensorPeriodic();
        pneumatics.sensorPeriodic();
        roboRio.sensorPeriodic();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    private void visionInit() {
		if (frontWatcher == null) {
			frontWatcher = new SocketVision("10.59.33.255", 5800);
			if (show_debug_vision) {
				System.out.println("Vision to Boiler started.");
			}
			frontWatcher.start();

			if (!frontWatcher.is_connected()) {
				if (!frontWatcher.connect()) {
					if (show_debug_vision) {
						System.err.println("Failed to connect to the Helmsman and I really need my boiled mayonnaise");
					}
				} else {
					if (show_debug_vision) {
						System.out.println("Connected. Love me some boiled mayo.");
					}
				}
			}
		}
    }
}
