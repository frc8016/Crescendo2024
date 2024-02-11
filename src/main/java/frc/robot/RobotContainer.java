// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.LowerClimb;
import frc.robot.commands.RaiseClimb;
import frc.robot.commands.RunShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
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


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final Joystick m_Joystick = new Joystick(OperatorConstants.kJoystickPort);
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Shooter m_Shooter = new Shooter();
  private final Climb m_Climb = new Climb();

  private final RaiseClimb m_RaiseClimb = new RaiseClimb(m_Climb);
  private final LowerClimb m_LowerClimb = new LowerClimb(m_Climb);
  private final RunShooter m_RunShooter = new RunShooter(m_Shooter);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
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
    //run shooter (turns on the shooter, 
    //waits x seconds to let shooter get up to speed, 
    //feeds note into shooter)
    m_driverController.rightTrigger().onTrue(m_RunShooter);
   
    //climb
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
    return null;
  }
}
