// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*need to update / set all constants for the robot  */
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
    public static final int kDriverControllerPort = 1;
    public static final int kJoystickPort = 2;
    public static final int JOYSTICK_Y_AXIS = 1;
    public static final int JOYSTIC_X_AXIS = 0;
  }

  public static class DriveTrainConstants {
    //drivetrain motor controller id
    public static final int FRONT_LEFT_ID = 6;
    public static final int BACK_LEFT_ID = 5;
    public static final int FRONT_RIGHT_ID = 3;
    public static final int BACK_RIGHT_ID = 4;
    //encoder PID constants 

    //encoder constants 
    public static final int RIGHT_ENCODER_ID = 9;
    public static final int LEFT_ENCODER_ID = 10;
    public static final double kEncoderDistencePerPulse = 0.479 / 4096.0;
    ;

    //pigion id 
    public static final int PIGION_ID = 11; 
  }

  public static class ShooterConstants{
    //motor controller id
    public static final int SHOOTER_LEFT = 1; 
    public static final int SHOOTER_RIGHT = 2;
    public static final int INDEX_ID = 7; 
    //speeds
    public static final double SHOOTER_SPEED = -.1; 
  
    public static final double INDEX_SPEED = -.1;
    //beambreak id
    public static final int BEAM_BREAK_SHOOTER_ID = 0;
  }

  public static class IntakeConstants{
    public static final int BEAM_BREAK_INTAKE_ID1 = 1;
    public static final int BEAM_BREAK_INTAKE_ID2 = 4;

    public static final int INTAKE_MOTOR = 8; 

    public static final double INTAKE_SPEED = -.1;

    public static final int SOLENOID_FWD = 6;
    public static final int SOLENOID_BKW = 7;
    
  }

  public static class ClimbConstants{
    //motor controller id
    public static final int LEFT = 900;
    public static final int RIGHT = 90;
    //speed
    public static final double CLIMB_SPEED = 0;
  }
}
