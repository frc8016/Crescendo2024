package frc.robot.subsystems;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.autonomousConstants;
import com.revrobotics.CANSparkLowLevel.MotorType;

import java.text.BreakIterator;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.Timer;

public class Auto {
    // Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.





/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 * this is test too
 */
public class autonomous extends TimedRobot {
  private Command m_autonomousCommand;
  /*list of Autos we can use  */
  private static final String kDefaultAuto = "Default"; /* works when robot is in the middle */  
  private static final String kCusatomAuto = "My Auto"; 

 
  /* makes a long stringout of the Strings above */
  private String m_autoSelected;

  /* sends Auto strings to dashbord */
  private final SendableChooser<String> m_Chooser = new SendableChooser<>();

     /* Auto totrial */
    private final CANSparkMax frontLeftAuto = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax backLeftAuto = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax frontRightAuto = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax backRightAuto = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    
    private final DifferentialDrive backAutoDifferentialDrive = new DifferentialDrive(backLeftAuto, backRightAuto);
    private final DifferentialDrive frontAutDifferentialDrive = new DifferentialDrive(frontLeftAuto, frontRightAuto);

    private final CANSparkMax feedWheel= new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax lauchWheel = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);


    private final Timer timerAuto = new Timer();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  
  @Override
  public void autonomousInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_Chooser.setDefaultOption("Default", kDefaultAuto);
    m_Chooser.addOption("My Auto", kCusatomAuto);
    /* Auto totrial  / Ask Caroline about inverteds  */
    frontLeftAuto.setInverted(true);
    backLeftAuto.setInverted(true);
    frontRightAuto.setInverted(false);
    backRightAuto.setInverted(false);
    // this program is if the motors are inverted
    feedWheel.setInverted(true);
    lauchWheel.setInverted(false);

    timerAuto.start();
 
  }



  @Override
  public void autonomousPeriodic() {
   /* Auto totoral  */
    switch (m_autoSelected) {
      case kCusatomAuto: // check if motors are sping the right way
      
      if(timerAuto.get() < 2.0){
     backAutoDifferentialDrive.tankDrive(.5, .5);
     frontAutDifferentialDrive.tankDrive(.5, .5);
    } 
    else{
      backAutoDifferentialDrive.tankDrive(0, .0);
      frontAutDifferentialDrive.tankDrive(.0, .0);
  
    }
      if(timerAuto.get() < 2.0 ){
        lauchWheel.set(1);
      }
      else if(timerAuto.get()< 3.5){ // trun on feed
         feedWheel.set(1);
      }
      else { // done turn off motor
        lauchWheel.set(0);
        feedWheel.set(0);
      }
     break;
      case kDefaultAuto:

    default:
    if(timerAuto.get() < 2.0){
     backAutoDifferentialDrive.tankDrive(.5, .5);
     frontAutDifferentialDrive.tankDrive(.5, .5);
    } 
    else{
      backAutoDifferentialDrive.tankDrive(0, .0);
      frontAutDifferentialDrive.tankDrive(.0, .0);
  
    }
    break;
      
    }
    backAutoDifferentialDrive.tankDrive(.5, .5);
    frontAutDifferentialDrive.tankDrive(.5, .5);
    
    
  }
  


    }
  }



