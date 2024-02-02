// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 * hello hello
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 1;
    public static final int kJoystickPort = 1;
    public static final int JOYSTICK_Y_AXIS = 1;
    public static final int JOYSTIC_X_AXIS = 1;
  }

  public static class DriveTrainConstants {
    //drivetrain motors
    public static final int FRONT_LEFT_ID = 1;
    public static final int BACK_LEFT_ID = 1;
    public static final int FRONT_RIGHT_ID = 1;
    public static final int BACK_RIGHT_ID = 1;
    
    //encoder constants 
    public static final int RIGHT_ENCODER_A = 1;
    public static final int RIGHT_ENCODER_B = 1;
    public static final boolean RIGHT_ENCODER_REVERSED = true;
    public static final int LEFT_ENCODER_A = 1;
    public static final int LEFT_ENCODER_B = 1;
    public static final boolean LEFT_ENCODER_REVERSED = false;

    public static final double kEncoderDistencePerPulse = 1;


  }

  public static class ShooterConstants{
    //ID
    public static final int SHOOTER_LEFT = 1; 
    public static final int SHOOTER_RIGHT = 2;
    //speeds
    public static final double SHOOTER_SPEED = .3; 
    



  }

  public static class IntakeConstants{

  }

  public static class ClimbConstants{
    public static final int LEFT = 1;
    public static final int RIGHT = 1;
  }
  public static class autonomousConstants{
        private final CANSparkMax frontLeftAuto = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax backLeftAuto = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax frontRightAuto = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax backRightAuto = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    
    private final DifferentialDrive backAutoDifferentialDrive = new DifferentialDrive(backLeftAuto, backRightAuto);
    private final DifferentialDrive frontAutDifferentialDrive = new DifferentialDrive(frontLeftAuto, frontRightAuto);
  }
}
