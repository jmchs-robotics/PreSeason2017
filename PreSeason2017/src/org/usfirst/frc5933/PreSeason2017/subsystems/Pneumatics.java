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

import org.usfirst.frc5933.PreSeason2017.RobotMap;
import org.usfirst.frc5933.PreSeason2017.commands.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Pneumatics extends Subsystem {
	/**
	 * The timeout for commands that control solenoids: a one-place change
	 */
	public final double kSolenoidFireTime = 0.1;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final DoubleSolenoid backSolenoid = RobotMap.pneumaticsBackSolenoid;
    private final DoubleSolenoid frontSolenoid = RobotMap.pneumaticsFrontSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new UnpowerAllClaws());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Contains appropriate {@link SmartDashboard} outputs for the pneumatics system.
     */
    public void periodic() {
    	SmartDashboard.putString("Solenoid Position", frontSolenoid.get().toString());
    }
    
    public void turnClawsOff() {
    	backSolenoid.set(Value.kOff);
    	frontSolenoid.set(Value.kOff);
    }
    
    public void setClaws(Value value) {
    	backSolenoid.set(value);
    	frontSolenoid.set(value);
    }
}

