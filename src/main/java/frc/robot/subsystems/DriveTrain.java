package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import com.revrobotics.CANSparkMax;
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
  
    private final Encoder m_leftEncoder = new Encoder(DriveTrainConstants.LEFT_ENCODER_A, DriveTrainConstants.LEFT_ENCODER_B);
    private final Encoder m_rightEncoder = new Encoder(DriveTrainConstants.RIGHT_ENCODER_A, DriveTrainConstants.RIGHT_ENCODER_B);

    //gyro 
    private final WPI_PigeonIMU m_gyro = new WPI_PigeonIMU(DriveTrainConstants.PIGION_ID);
    //odometry for checking robot pose/position
    private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(null, null, null); 
    

// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);
    //set encoders distance per pulse 
    m_leftEncoder.setDistancePerPulse(DriveTrainConstants.kEncoderDistencePerPulse);
    m_rightEncoder.setDistancePerPulse(DriveTrainConstants.kEncoderDistencePerPulse);

    resetEncoders();

    new DifferentialDriveOdometry( m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
}


@Override 
public void periodic(){
    //update odometry 
    m_odometry.update(
        m_gyro.getRotation2d(),
        m_leftEncoder.getDistance(), m_rightEncoder.getDistance() );
}

//returns estimated pose of robot
public Pose2d getPose(){
    return m_odometry.getPoseMeters();
}

//return current wheel speed
public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
}
    
//reste odometry to the specified pose 
public void resetOdometry(Pose2d pose) {
    resetEncoders();
    
    m_odometry.resetPosition(m_gyro.getRotation2d(), m_leftEncoder.getDistance(),m_rightEncoder.getDistance(), pose);
}

//arcade drive (actually drives the robot!)
public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(-speed, -rotation);
}

//control left and right side directly with voltage 
public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_frontLeft.setVoltage(leftVolts);
    m_backLeft.setVoltage(rightVolts);
    m_drive.feed();
}

//reset encoders 
public void resetEncoders(){
    m_leftEncoder.reset();
    m_rightEncoder.reset();
}

//get averge distance of two encoders
public double getAverageEncoderDistance(){
    return (m_leftEncoder.getDistance() + m_rightEncoder.getDistance() / 2.0);
}

//get the left encoder 
public Encoder getLeftEncoder(){
    return m_leftEncoder;
}

//get right encoder
public Encoder getRightEncoder(){
    return m_rightEncoder;
}

//set max outputes of the drive 
public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput); 
}

//zero heading of the robot using gyro
public void zeroHeading() {
    m_gyro.reset();
}

//return heading of the robot
public double getHeading(){
    return m_gyro.getRotation2d().getDegrees();
}
//return turn rate of the robot
public double getTurnRate(){
    return -m_gyro.getRate();
}
}
