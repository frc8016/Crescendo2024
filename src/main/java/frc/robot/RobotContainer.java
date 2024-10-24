// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.Autos;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.Shooter;
import java.util.List;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // Controllers
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final Joystick m_Joystick = new Joystick(OperatorConstants.kJoystickPort);
  // subsystems
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Shooter m_Shooter = new Shooter();
  private final Climb m_Climb = new Climb();
  private final Intake m_Intake = new Intake();
  private final Index m_Index = new Index();
  private final IntakeMotor m_IntakeMotor = new IntakeMotor();

  private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // this is the auto we will be using
    // score preload, taxi and pick up
    m_autoChooser.addOption(
        "Score + taxi ",
        Commands.sequence(
            // shoot
            Autos.autoGroup3(m_Shooter, m_Index, m_IntakeMotor),
            // drop intake
            Commands.parallel(
                Autos.autoGroup2(m_Intake, m_IntakeMotor),
                // taxi
                autoTaxi()),
            autoTaxiReversed(),
            Autos.retractIntakeAuto(m_Intake),
            Autos.autoGroup3(m_Shooter, m_Index, m_IntakeMotor)));

    m_autoChooser.addOption("auto no taxi ", Autos.autoGroup3(m_Shooter, m_Index, m_IntakeMotor));

    SmartDashboard.putData(m_autoChooser);

    // Configure the trigger bindings
    configureBindings();
    // run the drivetrain
    m_DriveTrain.setDefaultCommand(
        new RunCommand(
            () ->
                m_DriveTrain.arcadeDrive(
                    -m_Joystick.getRawAxis(OperatorConstants.JOYSTICK_Y_AXIS),
                    -m_Joystick.getRawAxis(OperatorConstants.JOYSTIC_X_AXIS)),
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
    // shooter + index spped to shoot
    m_driverController
        .x()
        .toggleOnTrue(
            new ParallelCommandGroup(
                new StartEndCommand(
                    () -> m_Shooter.runShooter(ShooterConstants.SHOOTER_SPEED),
                    () -> m_Shooter.runShooter(0),
                    m_Shooter),
                new StartEndCommand(
                    () -> m_Index.runIndex(ShooterConstants.INDEX_SPEED),
                    () -> m_Index.runIndex(0),
                    m_Index)));

    /*intake */
    // Extend Intake
    m_driverController.leftBumper().onTrue(new RunCommand(() -> m_Intake.extendIntake(), m_Intake));
    // Retract intake
    m_driverController
        .rightBumper()
        .onTrue(new RunCommand(() -> m_Intake.retractIntake(), m_Intake));

    // run intake rollers
    m_driverController
        .b()
        .whileTrue(
            new StartEndCommand(
                () -> m_IntakeMotor.runIntake(IntakeConstants.INTAKE_SPEED),
                () -> m_IntakeMotor.runIntake(0),
                m_Intake));
    // reverse intake motors
    m_driverController
        .a()
        .whileTrue(
            new StartEndCommand(
                () -> m_IntakeMotor.runIntake(.1),
                () -> m_IntakeMotor.runIntake(0),
                m_IntakeMotor));

    // run intake motors with beam break to stop it
    m_driverController
        .y()
        .whileTrue(
            new SequentialCommandGroup(
                new StartEndCommand(
                        () -> m_IntakeMotor.runIntake(-.3),
                        () -> m_IntakeMotor.runIntake(0),
                        m_IntakeMotor)
                    .until(m_Intake.m_BooleanSupplier()),
                new StartEndCommand(
                        () -> m_IntakeMotor.runIntake(.05),
                        () -> m_IntakeMotor.runIntake(0),
                        m_IntakeMotor)
                    .until(m_Intake.m_BooleanSupplierNot())));

    m_driverController
        .povUp()
        .whileTrue(
            new ParallelCommandGroup(
                new StartEndCommand(
                    () -> m_Climb.raiseClimb(.5), () -> m_Climb.raiseClimb(0), m_Climb),
                new RunCommand(() -> m_Shooter.runShooter(0), m_Shooter)));

    m_driverController
        .povDown()
        .whileTrue(
            new StartEndCommand(
                () -> m_Climb.lowerClimb(.5), () -> m_Climb.lowerClimb(0), m_Climb));
  }

  public Command autoTaxi() {
    var autoVoltageConstraint =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                DriveTrainConstants.ks_left,
                DriveTrainConstants.kv_left,
                DriveTrainConstants.ka_left),
            DriveTrainConstants.kDriveKinematics,
            2);

    TrajectoryConfig config =
        new TrajectoryConfig(2, 2)
            .setKinematics(DriveTrainConstants.kDriveKinematics)
            .addConstraint(autoVoltageConstraint);

    edu.wpi.first.math.trajectory.Trajectory trajectory =
        TrajectoryGenerator.generateTrajectory(
            new Pose2d(0, 0, new Rotation2d()),
            List.of(new Translation2d(1, 0)),
            new Pose2d(3, 0, new Rotation2d()),
            config);

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory,
            m_DriveTrain::getPose,
            new RamseteController(DriveTrainConstants.kRamseteB, DriveTrainConstants.kRamseteZeta),
            new SimpleMotorFeedforward(
                DriveTrainConstants.ks_left,
                DriveTrainConstants.kv_left,
                DriveTrainConstants.ka_left),
            DriveTrainConstants.kDriveKinematics,
            m_DriveTrain::getWheelSpeeds,
            new PIDController(
                DriveTrainConstants.kp_left,
                DriveTrainConstants.ki_left,
                DriveTrainConstants.kd_left),
            new PIDController(
                DriveTrainConstants.kp_right,
                DriveTrainConstants.ki_right,
                DriveTrainConstants.kd_right),
            m_DriveTrain::tankDriveVoltsFwd,
            m_DriveTrain);

    return Commands.runOnce(() -> m_DriveTrain.resetOdometry(trajectory.getInitialPose()))
        .andThen(ramseteCommand)
        .andThen(Commands.runOnce(() -> m_DriveTrain.tankDriveVoltsFwd(0, 0)));
  }

  public Command autoTaxiReversed() {
    var autoVoltageConstraintReversed =
        new DifferentialDriveVoltageConstraint(
            new SimpleMotorFeedforward(
                DriveTrainConstants.ks_left,
                DriveTrainConstants.kv_left,
                DriveTrainConstants.ka_left),
            DriveTrainConstants.kDriveKinematics,
            .5);

    TrajectoryConfig config =
        new TrajectoryConfig(2, 2)
            .setKinematics(DriveTrainConstants.kDriveKinematics)
            .addConstraint(autoVoltageConstraintReversed);

    edu.wpi.first.math.trajectory.Trajectory trajectory2 =
        TrajectoryGenerator.generateTrajectory(
            new Pose2d(3, 0, new Rotation2d()),
            List.of(new Translation2d(2, 0)),
            new Pose2d(0, 0, new Rotation2d()),
            config.setReversed(true));

    RamseteCommand ramseteCommand =
        new RamseteCommand(
            trajectory2,
            m_DriveTrain::getPose,
            new RamseteController(DriveTrainConstants.kRamseteB, DriveTrainConstants.kRamseteZeta),
            new SimpleMotorFeedforward(
                DriveTrainConstants.ks_left,
                DriveTrainConstants.kv_left,
                DriveTrainConstants.ka_left),
            DriveTrainConstants.kDriveKinematics,
            m_DriveTrain::getWheelSpeeds,
            new PIDController(
                DriveTrainConstants.kp_left,
                DriveTrainConstants.ki_left,
                DriveTrainConstants.kd_left),
            new PIDController(
                DriveTrainConstants.kp_right,
                DriveTrainConstants.ki_right,
                DriveTrainConstants.kd_right),
            m_DriveTrain::tankDriveVoltsFwd,
            m_DriveTrain);

    return Commands.runOnce(() -> m_DriveTrain.resetOdometry(trajectory2.getInitialPose()))
        .andThen(ramseteCommand)
        .andThen(Commands.runOnce(() -> m_DriveTrain.tankDriveVoltsFwd(0, 0)));
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

  public void autonomousInit() {}
}
