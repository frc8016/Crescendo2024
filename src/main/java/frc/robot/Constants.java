// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
/*need to update / set all constants for the robot  */
package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

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
    public static final int FRONT_LEFT_ID = 1;
    public static final int BACK_LEFT_ID = 2;
    public static final int FRONT_RIGHT_ID = 3;
    public static final int BACK_RIGHT_ID = 4;
    //encoder constants 
    public static final int LEFT_ENCODER_A = 1;
    public static final int RIGHT_ENCODER_A = 3;
     public static final int LEFT_ENCODER_B = 6;
    public static final int RIGHT_ENCODER_B = 4;
    public static final int LEFT_ABSOLUTE_ENCODER = 2;
    public static final int RIGHT_ABSOLUTE_ENCODER = 5;

    public static final double kEncoderDistencePerPulse = 0.479 / 4096.0;
    //kinematics 
    public static final double kTrackWidthMeters = .635;
    public static final DifferentialDriveKinematics kDriveKinematics = 
      new DifferentialDriveKinematics(kTrackWidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3;
    public static final double kMaxAccelerationMetersPerSecond = 1;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = .7;
    //PID stuff
    public static final double kp_left = .6;
    public static final double kd_left = 0.05;
    public static final double ki_left = 0;

    public static final double kp_right = .6;
    public static final double kd_right = 0.05;
    public static final double ki_right = 0;

    public static final double ka_left = .01;
    public static final double kv_left = .01;
    public static final double ks_left = 0;
    public static final double ka_right = .01;
    public static final double kv_right = .01; 
    public static final double ks_right = 0;

   
    //pigion id 
    public static final int PIGION_ID = 11; 
  }

  public static class ShooterConstants{
    //motor controller id
    public static final int SHOOTER_LEFT = 7; 
    public static final int SHOOTER_RIGHT = 8;
    //index motor controller
    public static final int INDEX_ID = 6; 
    //speeds
    public static final double SHOOTER_SPEED = -.7; 
  
    public static final double INDEX_SPEED = -.7;
    //beambreak id
   // public static final int BEAM_BREAK_SHOOTER_ID = 9;
  }

  public static class IntakeConstants{
    public static final int BEAM_BREAK_INTAKE_ID1 = 8;
    //intake motor id
    public static final int INTAKE_MOTOR = 5; 
    //intake motor spped
    public static final double INTAKE_SPEED = -.5;
    //pnumatics
    public static final int SOLENOID_FWD = 4;
    public static final int SOLENOID_BKW = 5;
  }

  public static class ClimbConstants{
    //motor controller id
    public static final int LEFT = 9;
    public static final int RIGHT = 10;
    //speed
    public static final double CLIMB_SPEED = .5;
  }
}
