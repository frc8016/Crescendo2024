package frc.robot.subsystems;

import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;

import java.util.List;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.proto.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants.DriveTrainConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
 

import com.ctre.phoenix.sensors.WPI_PigeonIMU;

public class DriveTrain extends SubsystemBase {
    
   
    //motor controllers 
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backLeft = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backRight = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    //motor controller groups
    private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);
    //Encoders
  
    private final Encoder m_leftEncoder = new Encoder(DriveTrainConstants.LEFT_ENCODER_A, DriveTrainConstants.LEFT_ENCODER_B, true);
    private final Encoder m_rightEncoder = new Encoder(DriveTrainConstants.RIGHT_ENCODER_A, DriveTrainConstants.RIGHT_ENCODER_B, true);

    //gyro 
    private final WPI_PigeonIMU m_gyro = new WPI_PigeonIMU(11);
    //odometry for checking robot pose/position
   private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance()); 
    
    //sysId nonsence 
   private final MutableMeasure <Voltage> m_appliedVoltage = mutable(Volts.of(0));
   private final MutableMeasure <Distance> m_distance = mutable(Meters.of(0));
   private final MutableMeasure <Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));

   private final SysIdRoutine m_SysIdRoutine = 
        new SysIdRoutine(
            // Empty config defaults to 1 volt/second ramp rate and 7 volt step voltage.
          new SysIdRoutine.Config(),
          new SysIdRoutine.Mechanism(
        // Tell SysId how to plumb the driving voltage to the motors.
        (Measure<Voltage> volts) -> {
            m_frontLeft.setVoltage(volts.in(Volts));
            m_frontRight.setVoltage(volts.in(Volts));
        },
         // Tell SysId how to record a frame of data for each motor on the mechanism being characterized.
        log -> {
            log.motor("drive-left")
            .voltage(
                m_appliedVoltage.mut_replace(
                    m_frontLeft.get() * RobotController.getBatteryVoltage(), Volts))
            .linearPosition(m_distance.mut_replace(m_leftEncoder.getDistance(), Meters))
            .linearVelocity(
                m_velocity.mut_replace(m_leftEncoder.getRate(), MetersPerSecond));

            log.motor("drive-right")
            .voltage(
                m_appliedVoltage.mut_replace(
                    m_frontRight.get() * RobotController.getBatteryVoltage(), Volts))
            .linearPosition(m_distance.mut_replace(m_rightEncoder.getDistance(), Meters))
            .linearVelocity(m_velocity.mut_replace(m_rightEncoder.getRate(), MetersPerSecond));
        },
          // Tell SysId to make generated commands require this subsystem, suffix test state in
              // WPILog with this subsystem's name ("drive")
         this));

// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(false);
    m_frontRight.setInverted(true);
    //set encoders distance per pulse 
    m_leftEncoder.setDistancePerPulse(DriveTrainConstants.kEncoderDistencePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveTrainConstants.kEncoderDistencePerPulse);
    //set idle mode 
    setIdleModeCoast();

}
//set idle mode to brake
public void setIdleModeBrake(){
    m_frontLeft.setIdleMode(IdleMode.kBrake);
    m_frontRight.setIdleMode(IdleMode.kBrake);
    m_backLeft.setIdleMode(IdleMode.kBrake);
    m_backRight.setIdleMode(IdleMode.kBrake);

    m_frontLeft.burnFlash();
    m_frontRight.burnFlash();
    m_backLeft.burnFlash();
    m_backRight.burnFlash();
}
//sets idle mode to coast
public void setIdleModeCoast(){
    m_frontLeft.setIdleMode(IdleMode.kCoast);
    m_frontRight.setIdleMode(IdleMode.kCoast);
    m_backLeft.setIdleMode(IdleMode.kCoast);
    m_backRight.setIdleMode(IdleMode.kCoast);

    m_frontLeft.burnFlash();
    m_frontRight.burnFlash();
    m_backLeft.burnFlash();
    m_backRight.burnFlash();
}



@Override
public void periodic(){
    m_odometry.update(
        m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

    System.out.println("left encoder" + m_leftEncoder.getDistance());
    System.out.println("right encoder" + m_rightEncoder.getDistance());
}

public Pose2d getPose(){
    return m_odometry.getPoseMeters();
}

public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
}

public void resetOdometry(Pose2d pose){
    m_odometry.resetPosition(
        m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance(), pose);
}

public void arcadeDrive(double speed, double rotation){
    m_drive.arcadeDrive(-speed, -rotation);
}

public void tankDriveVoltsFwd(double leftVolts, double rightVolts){
    m_frontLeft.setVoltage(-leftVolts);
    m_frontRight.setVoltage(-rightVolts);
    m_drive.feed();
}

public void tankDriveVoltsBkw(double leftVolts, double rightVolts){
    m_frontLeft.setVoltage(leftVolts);
    m_frontRight.setVoltage(rightVolts);
    m_drive.feed();
}

public void resetEncoders(){
    m_leftEncoder.reset();
    m_rightEncoder.reset();
}

public double getAverageEncoderDistance(){
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance()) / 2.0;
}

public Encoder getLeftEncoder(){
    return m_leftEncoder;
}

public Encoder getRightEncoder(){
    return m_rightEncoder;
}

public void setMoxOutput(double maxOutput){
    m_drive.setMaxOutput(maxOutput);
}

public void zeroHeading(){
    m_gyro.reset();
}

public double getHeading(){
    return m_gyro.getRotation2d().getDegrees();
}

public double getTurnRate(){
    return -m_gyro.getRate();
    }
    
}