package frc.robot.subsystems;



import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.proto.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryParameterizer.TrajectoryGenerationException;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants.DriveTrainConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.ctre.phoenix6.hardware.CANcoder;

public class DriveTrain extends SubsystemBase {
    //motor controllers 
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backLeft = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backRight = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    //motor controller groups
    private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);
    //Encoders
    //pigion
    private final WPI_PigeonIMU m_pigeon = new WPI_PigeonIMU(11);

    


    private final PIDController m_leftPID = new PIDController(DriveTrainConstants.kp_left, DriveTrainConstants.kd_left, DriveTrainConstants.ki_left);
    private final PIDController m_rightPID = new PIDController(DriveTrainConstants.kp_right, DriveTrainConstants.kd_right, DriveTrainConstants.ki_right);
  //PID controllers 

// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);


  
}
/*encoder and pigeon things */
public void arcadeDrive(double speed, double rotation ){
    m_drive.arcadeDrive(-speed, -rotation);
}




@Override
public void periodic(){
}
}