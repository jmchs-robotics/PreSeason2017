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

import org.usfirst.frc5933.PreSeason2017.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc5933.PreSeason2017.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	/*
	 * When declaring joysticks it is recommended to follow a similar format
	 * where each button is named with respect to (w.r.t.) the joystick it is
	 * from and the button name it has on that joystick. (so the A button on the
	 * driver's joystick is driverA; etc., etc.)
	 */
    public JoystickButton driverA;
    public JoystickButton driverB;
    public JoystickButton driverX;
    public JoystickButton driverY;
    public JoystickButton driverLeftBumper;
    public JoystickButton driverRightBumper;
    public JoystickButton driverBack;
    public JoystickButton driverStart;
    public JoystickButton driverLeftToggle;
    public JoystickButton driverRightToggle;
    
    public Joystick driverStick;

//    public JoystickButton subsystemA;
//    public JoystickButton subsystemB;
//    public JoystickButton subsystemX;
//    public JoystickButton subsystemY;
//    public JoystickButton subsystemLeftBumper;
//    public JoystickButton subsystemRightBumper;
//    public JoystickButton subsystemBack;
//    public JoystickButton subsystemStart;
//    public JoystickButton subsystemLeftToggle;
//    public JoystickButton subsystemRightToggle;
//    
//    public Joystick subsystemStick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        driverStick = new Joystick(0);

        driverA = new JoystickButton(driverStick, 1);
        driverA.whenPressed(new NullCommand());
        driverB = new JoystickButton(driverStick, 2);
        driverB.whenPressed(new NullCommand());
        driverX = new JoystickButton(driverStick, 3);
        driverX.whenPressed(new NullCommand());
        driverY = new JoystickButton(driverStick, 4);
        driverY.whenPressed(new NullCommand());
        driverLeftBumper = new JoystickButton(driverStick, 5);
        driverLeftBumper.whenPressed(new NullCommand());
        driverRightBumper = new JoystickButton(driverStick, 6);
        driverRightBumper.whenPressed(new NullCommand());
        driverBack = new JoystickButton(driverStick, 7);
        driverBack.whenPressed(new NullCommand());
        driverStart = new JoystickButton(driverStick, 8);
        driverStart.whenPressed(new NullCommand());
        driverLeftToggle = new JoystickButton(driverStick, 9);
        driverLeftToggle.whenPressed(new NullCommand());
        driverRightToggle = new JoystickButton(driverStick, 10);
        driverRightToggle.whenPressed(new NullCommand());
        
//        subsystemStick = new Joystick(1);
//
//        subsystemA = new JoystickButton(subsystemStick, 1);
//        subsystemA.whenPressed(new CloseAllClaws());
//        subsystemB = new JoystickButton(subsystemStick, 2);
//        subsystemB.whenPressed(new OpenAllClaws());
//        subsystemX = new JoystickButton(subsystemStick, 3);
//        subsystemX.whenPressed(new NullCommand());
//        subsystemY = new JoystickButton(subsystemStick, 4);
//        subsystemY.whenPressed(new NullCommand());
//        subsystemLeftBumper = new JoystickButton(subsystemStick, 5);
//        subsystemLeftBumper.whenPressed(new NullCommand());
//        subsystemRightBumper = new JoystickButton(subsystemStick, 6);
//        subsystemRightBumper.whenPressed(new NullCommand());
//        subsystemBack = new JoystickButton(subsystemStick, 7);
//        subsystemBack.whenPressed(new NullCommand());
//        subsystemStart = new JoystickButton(subsystemStick, 8);
//        subsystemStart.whenPressed(new NullCommand());
//        subsystemLeftToggle = new JoystickButton(subsystemStick, 9);
//        subsystemLeftToggle.whenPressed(new NullCommand());
//        subsystemRightToggle = new JoystickButton(subsystemStick, 10);
//        subsystemRightToggle.whenPressed(new NullCommand());

        // SmartDashboard Buttons
        SmartDashboard.putData("DefaultAutonomousCommand", new DefaultAutonomousCommand());
        SmartDashboard.putData("CloseAllClaws", new CloseAllClaws());
        SmartDashboard.putData("OpenAllClaws", new OpenAllClaws());
        SmartDashboard.putData("UnpowerAllClaws", new UnpowerAllClaws());
        SmartDashboard.putData("DriveStraightGyro", new DriveStraightGyro());
        SmartDashboard.putData("SimpleDriveStraight", new SimpleDriveStraight());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    /**
     * Use this to get the driverStick object safely.
     * @return
     */
    public Joystick getDriverStick() {
        return driverStick;
    }
    
    /**
     * Use this to get the subsystemStick object safely.
     * @return
     */
//    public Joystick getSubsystemStick() {
//    	return subsystemStick;
//    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

