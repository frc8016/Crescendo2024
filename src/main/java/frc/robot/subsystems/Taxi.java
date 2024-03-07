package frc.robot.subsystems;


import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.Constants.DriveTrainConstants;

public class Taxi {
      private DriveTrain m_DriveTrain = new DriveTrain();
public Command autoThing(){
    var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
        new SimpleMotorFeedforward(
            DriveTrainConstants.ks_left, 
            DriveTrainConstants.kv_left,
            DriveTrainConstants.ka_left), 
            DriveTrainConstants.kDriveKinematics, 2);

    TrajectoryConfig config = new TrajectoryConfig(
        2, 2)
        .setKinematics(DriveTrainConstants.kDriveKinematics)
        .addConstraint(autoVoltageConstraint);

    edu.wpi.first.math.trajectory.Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
        new Pose2d(0,0, new Rotation2d()),
        List.of(new Translation2d(1,0)),
        new Pose2d(4, 0, new Rotation2d()),
        config);

        RamseteCommand ramseteCommand = 
        new RamseteCommand(
          trajectory,
           m_DriveTrain::getPose,
         new RamseteController(
          DriveTrainConstants.kRamseteB, DriveTrainConstants.kRamseteZeta),
          new SimpleMotorFeedforward(
            DriveTrainConstants.ks_left, 
            DriveTrainConstants.kv_left,
            DriveTrainConstants.ka_left),
            DriveTrainConstants.kDriveKinematics,
           m_DriveTrain::getWheelSpeeds, 
        new PIDController(DriveTrainConstants.kp_left, DriveTrainConstants.ki_left, DriveTrainConstants.kd_left),
        new PIDController(DriveTrainConstants.kp_right, DriveTrainConstants.ki_right, DriveTrainConstants.kd_right),
        m_DriveTrain::tankDriveVoltsFwd,
        m_DriveTrain);

        return Commands.runOnce(() -> m_DriveTrain.resetOdometry(trajectory.getInitialPose()))
        .andThen(ramseteCommand)
        .andThen(Commands.runOnce(() -> m_DriveTrain.tankDriveVoltsFwd(0, 0)));
}



}
