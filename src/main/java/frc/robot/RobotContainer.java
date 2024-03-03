// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.BeamBreakIntake;
import frc.robot.commands.ExtendIntake;

import frc.robot.commands.RetractIntake;
import frc.robot.commands.RunIndexToShoot;
import frc.robot.commands.RunIntakeRollers;
import frc.robot.commands.RunShooter;
//import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.Shooter;

import com.fasterxml.jackson.databind.node.ShortNode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
//Controllers 
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final Joystick m_Joystick = new Joystick(OperatorConstants.kJoystickPort);
//subsystems 
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Shooter m_Shooter = new Shooter();
 //private final Climb m_Climb = new Climb();
  private final Intake m_Intake = new Intake();
  private final Index m_Index = new Index();
  private final IntakeMotor m_IntakeMotor = new IntakeMotor();

  private final  SendableChooser<Command> m_autoChooser = new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_autoChooser.setDefaultOption("auto1", Autos.runShooterAuto(m_Shooter, m_Index));
    m_autoChooser.addOption("auto2", Autos.extendIntakeAuto(m_Intake));
    m_autoChooser.addOption("auto3", Autos.retractIntakeAuto(m_Intake));
    m_autoChooser.addOption("auto4", Autos.feedIntoShooterAuto(m_IntakeMotor));
    m_autoChooser.addOption("auto5", Autos.runInakeMotorAuto(m_IntakeMotor, m_Intake));

    SmartDashboard.putData(m_autoChooser);



    // Configure the trigger bindings
    configureBindings();
    //run the drivetrain 
    m_DriveTrain.setDefaultCommand(
      new RunCommand(
        () -> m_DriveTrain.arcadeDrive(
          m_Joystick.getRawAxis(OperatorConstants.JOYSTICK_Y_AXIS),
          m_Joystick.getRawAxis(OperatorConstants.JOYSTIC_X_AXIS)),
       m_DriveTrain));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
   //shooter (need to check whats wrong with this) :)
   m_driverController
  .x()
  .toggleOnTrue(
    new ParallelCommandGroup(
      new StartEndCommand(() -> m_Shooter.runShooter(ShooterConstants.SHOOTER_SPEED), () -> m_Shooter.runShooter(0), m_Shooter),
      new StartEndCommand(() -> m_Index.runIndex(ShooterConstants.INDEX_SPEED), () -> m_Index.runIndex(0), m_Index)
    ));

      //for testings 
      /*Run shooter */
      m_driverController.a().toggleOnTrue(
       new StartEndCommand(
        () -> m_Shooter.runShooter(ShooterConstants.SHOOTER_SPEED),
        () -> m_Shooter.runShooter(0),
        m_Shooter
       ));
     

      /*Run index */
      m_driverController.rightTrigger().whileTrue(
        new StartEndCommand(
          () -> m_Index.runIndex(ShooterConstants.INDEX_SPEED), 
          () -> m_Index.runIndex(0), m_Index));   


        //  -------------------------- //
  
     

  /*intake */
    //Extend Intake 
    m_driverController.leftBumper().onTrue(
      new RunCommand(() -> m_Intake.extendIntake(), m_Intake));
    //Retract intake
      m_driverController.rightBumper().onTrue(
        new RunCommand(() -> m_Intake.retractIntake(), m_Intake));
    //run intake rollers
    m_driverController.b().whileTrue(
      new StartEndCommand(
        () -> m_IntakeMotor.runIntake(IntakeConstants.INTAKE_SPEED),
        () -> m_IntakeMotor.runIntake(0),
        m_Intake));

      m_driverController.a().whileTrue(
        new StartEndCommand(
          () -> m_IntakeMotor.runIntake(-IntakeConstants.INTAKE_SPEED), 
          () -> m_IntakeMotor.runIntake(0), 
          m_IntakeMotor)
      );
      
      m_driverController.y().whileTrue(
             new StartEndCommand(
          () -> m_IntakeMotor.runIntake(IntakeConstants.INTAKE_SPEED),
          () -> m_IntakeMotor.runIntake(0))
          .until(m_Intake.m_BooleanSupplier())
          );}
        //  new RunCommand(() -> m_Intake.retractIntake(), m_Intake))
    /*climb
  m_driverController.povUp().whileTrue(
    new RunCommand(() -> m_Climb.raiseClimb(.2), m_Climb));

  m_driverController.povDown().whileTrue(
    new RunCommand(() -> m_Climb.lowerClimb(.2), m_Climb));

  }*/
  

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_autoChooser.getSelected();
  }
  public void autonomousInit(){
    
  }

}
