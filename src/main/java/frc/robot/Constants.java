// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kJoystickPort = 0;
    public static final int JOYSTICK_Y_AXIS = 1;
    public static final int JOYSTIC_X_AXIS = 0;
  }

  public static class DriveTrainConstants {
    //drivetrain motors
    public static final int FRONT_LEFT_ID = 0;
    public static final int BACK_LEFT_ID = 0;
    public static final int FRONT_RIGHT_ID = 0;
    public static final int BACK_RIGHT_ID = 0;
    
    //encoder constants 
    public static final int RIGHT_ENCODER_A = 0;
    public static final int RIGHT_ENCODER_B = 0;
    public static final boolean RIGHT_ENCODER_REVERSED = true;
    public static final int LEFT_ENCODER_A = 0;
    public static final int LEFT_ENCODER_B = 0;
    public static final boolean LEFT_ENCODER_REVERSED = false;

    public static final double kEncoderDistencePerPulse = 0;


  }

  public static class ShooterConstants{
    //ID
    public static final int SHOOTER_LEFT = 0; 
    public static final int SHOOTER_RIGHT = 0;
    //speeds
    public static final double SHOOTER_SPEED = .3; 
    



  }

  public static class IntakeConstants{

  }

  public static class ClimbConstants{
    
  }
}
