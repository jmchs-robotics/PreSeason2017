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
	public final double kSolenoidFireTime = 0.1; //the shortest time for the solenoids to latch is one tenth of a second, so that's our number

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

        setDefaultCommand(new UnpowerAllClaws()); //allows the default command to constantly tell the solenoids to be off.

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Contains appropriate {@link SmartDashboard} outputs for the pneumatics system.
     */
    public void sensorPeriodic() {
    	SmartDashboard.putString("Solenoid Position: ", frontSolenoid.get().toString());
    }
    
    /**
     * Turn off solenoids to conserve battery power. Since they are "latching" solenoids, they will keep
     * the pistons pressurized even when the current to them is set to 0.
     */
    public void turnClawsOff() {
    	backSolenoid.set(Value.kOff);
    	frontSolenoid.set(Value.kOff);
    }
    
    /**
     * A general method to control the position of the claws. They have been wired to open and close
     * on the same {@link Value}, so this method works cleanly. Can be used to turn claws off, but it
     * is recommended you use the method with the appropriate name for that.
     * @param value
     * The position (value) to set the claws to.
     */
    public void setClaws(Value value) {
    	backSolenoid.set(value);
    	frontSolenoid.set(value);
    }
}

