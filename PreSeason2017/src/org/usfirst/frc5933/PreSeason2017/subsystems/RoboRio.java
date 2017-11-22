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

import org.usfirst.frc5933.PreSeason2017.Robot;
import org.usfirst.frc5933.PreSeason2017.RobotMap;
import org.usfirst.frc5933.PreSeason2017.commands.*;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class RoboRio extends Subsystem {
	public static enum AccelerometerDirection {
		X,
		Y,
		Z,
		Horizontal,
		Total
	}
	
	public static final double kGyroVoltsPerDegreesPerSecond = 0.007;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogGyro mainGyro = RobotMap.roboRioMainGyro;
    private final PowerDistributionPanel powerDistributionPanel = RobotMap.roboRioPowerDistributionPanel;
    private final BuiltInAccelerometer accelerometer = RobotMap.roboRioAccelerometer;
    private final DigitalInput frontFlagSwitch = RobotMap.roboRioFrontFlagSwitch;
    private final DigitalInput backFlagSwitch = RobotMap.roboRioBackFlagSwitch;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * Nominal battery voltage is 12.5
     */
    public static final double kNomialBatteryVoltage = 12.5;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    /**
     * Zero the gyro.
     */
    public void resetGyro() {
    	mainGyro.reset();
    }
    
    /**
     * Get the current angle of the gyro.
     * @return
     * The current angle of the robot since last reset.
     */
    public double readGyro() {
    	return mainGyro.getAngle();
    }
    
    /**
     * Checks if the resolution of the acceleration vectors in the chosen AccelerometerDirection(s)
     * is greater than or equal to the threshold. For directions Horizontal and Total, the resolution of the 
     * acceleration is defined as the square root of the applicable acceleration vectors squared. Otherwise,
     * the absolute value is taken. This method does not compare direction of acceleration to direction of
     * the threshold (in other words, this takes the absolute value of everything).
     * @param dir
     * - the acceleration vector(s) to resolve and compare to the threshold
     * @param threshold
     * - the magnitude, in g-forces, to compare to the resolved acceleration vector(s).
     * @return
     * Whether or not the resolution of vectors is greater than or equal to the threshold. Defaults to true.
     */
    public boolean accelOverThreshold(AccelerometerDirection dir, double threshold) {
    	return Math.abs(getAcceleration(dir)) >= Math.abs(threshold);
    }
    
    /**
     * A single method to get all the accelerometer values you could possibly want.
     * @param dir
     * The direction(s) to get acceleration from. Horizontal and Total return positive values only because the resolution of
     * two or more vectors (in this case, the accelerations) is defined as sqrt(X^2 + Y^2 + Z^2) and for any pair.
     * @return
     * The appropriate value - either accelerometer.getX() .getY() or .getZ() or a resolution of the horizontal components or all three.
     * If an inappropriate AccelerometerDirection is passed, the result is positive infinity.
     */
    public double getAcceleration(AccelerometerDirection dir) {
    	switch (dir) {
    	case X:
    		return accelerometer.getX();
    	case Y:
    		return accelerometer.getY();
    	case Z:
    		return accelerometer.getZ();
    	case Horizontal:
    		return Math.sqrt(Math.pow(accelerometer.getX(), 2) + Math.pow(accelerometer.getY(), 2));
    	case Total:
    		return Math.sqrt(Math.pow(accelerometer.getX(), 2) + Math.pow(accelerometer.getZ(), 2) + Math.pow(accelerometer.getY(), 2));
    	default:
    		return Double.POSITIVE_INFINITY;
    	}
    }
    
    public double getBatteryVoltage() {
    	return powerDistributionPanel.getVoltage();
    }
    
    /**
     * This method returns a number (usually less than 1) that is the proportion between
     * the current battery voltage (from the PDP) and the {@link kNominalBatteryVoltage}
     * @return
     * the proportion given by getBatteryVoltage()/kNominalBatteryVoltage
     */
    public double getVBusProportion() {
    	return getBatteryVoltage() / kNomialBatteryVoltage;
    }
    
    public boolean getFrontFlagSwitch() {
    	return frontFlagSwitch.get();
    }
    
    public boolean getBackFlagSwitch() {
    	return backFlagSwitch.get();
    }
}

