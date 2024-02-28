// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.BeamBreakIntake;
import frc.robot.commands.ExtendIntake;
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.RetractIntake;
import frc.robot.commands.RunIndexToShoot;
import frc.robot.commands.RunIntakeRollers;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
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
  private final Climb m_Climb = new Climb();
  private final Intake m_Intake = new Intake();
  private final Index m_Index = new Index();
//commands 
  private final RaiseClimb m_RaiseClimb = new RaiseClimb(m_Climb);
  private final LowerClimb m_LowerClimb = new LowerClimb(m_Climb);
  private final RunShooter m_RunShooter = new RunShooter(m_Shooter);
  private final RunIndexToShoot m_RunIndexToShoot = new RunIndexToShoot(m_Index);
  private final ExtendIntake m_ExtendIntake = new ExtendIntake(m_Intake); 
  private final RetractIntake m_RetractIntake = new RetractIntake(m_Intake);
  private final RunIntakeRollers m_RunIntakeRollers = new RunIntakeRollers(m_Intake);
  private final BeamBreakIntake m_BeamBreakIntake = new BeamBreakIntake(m_Intake);

  private final  SendableChooser<Command> m_autoChooser = new SendableChooser<>();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_autoChooser.setDefaultOption("auto1", null);
    m_autoChooser.addOption("auto2", null);

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
  .whileTrue(
    new ParallelCommandGroup(
      m_RunShooter,
      new WaitCommand(2),
      m_RunIndexToShoot
      ));

      //for testings 
      /*Run shooter */
      m_driverController.a().whileTrue(m_RunIndexToShoot);
      /*Run index */
      m_driverController.b().whileTrue(m_RunShooter);   


        //  -------------------------- //
  
  /*intake */
    //Extend Intake 
    m_driverController.rightBumper().onTrue(m_ExtendIntake);
    //Retract intake
      m_driverController.leftBumper().onTrue(m_RetractIntake);
    //run intake rollers
    m_driverController.y().toggleOnTrue(m_RunIntakeRollers);
    //intake command thing 
    m_driverController.leftTrigger().onTrue(
      new ParallelCommandGroup(
        m_ExtendIntake
       // m_RunIntakeRollers.until(m_BeamBreak.get(true))
      )
    );

 
    /*climb*/
    m_driverController.x().onTrue(m_RaiseClimb);
    m_driverController.y().onTrue(m_LowerClimb);

     
  }
  

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
