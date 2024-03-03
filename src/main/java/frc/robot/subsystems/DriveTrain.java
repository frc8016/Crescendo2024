package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
//import edu.wpi.first.math.geometry.Pose2d;
//import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import com.ctre.phoenix.sensors.WPI_PigeonIMU;

public class DriveTrain extends SubsystemBase {
    //motor controllers 
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backLeft = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backRight = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    //motor controller groups
    private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);
    //Encoders
    private final CANcoder m_leftEncoder = new CANcoder(10);
    private final CANcoder m_rightEncoder = new CANcoder(9);

    
    //gyro 
   // private final WPI_PigeonIMU m_Pigeon = new WPI_PigeonIMU(DriveTrainConstants.PIGION_ID);
    //odometry for checking robot pose/position
   // private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(null, null, null); 
    

// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);
    //set encoders distance per pulse 
    

 

   //new DifferentialDriveOdometry( m_Pigeon.getRotation2d(), m_leftEncoder.getPosition(), m_rightEncoder.getPosition());
}


@Override 
public void periodic(){
    //update odometry 
   // m_odometry.update(
      //  m_gyro.getRotation2d(),
    //   m_leftEncoder.getDistance(), m_rightEncoder.getDistance() );
}//

//returns estimated pose of robot
//public Pose2d getPose(){
  //  return m_odometry.getPoseMeters();
//}

//return current wheel speed
public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return null;
}
    
/*//reste odometry to the specified pose 
public void resetOdometry(Pose2d pose) {
    resetEncoders();*/
    
   // m_odometry.resetPosition(m_gyro.getRotation2d(), m_leftEncoder.getDistance(),m_rightEncoder.getDistance(), pose);
//}

//arcade drive (actually drives the robot!)
public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(-speed, -rotation);
}

//control left and right side directly with voltage 
/*public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_frontLeft.setVoltage(leftVolts);
    m_backLeft.setVoltage(rightVolts);
    m_drive.feed();*/


//reset encoders 


//get averge distance of two encoders

//get the left encoder 
public CANcoder getLeftEncoder(){
    return m_leftEncoder;
}

//get right encoder
public CANcoder getRightEncoder(){
    return m_rightEncoder;
}

//set max outputes of the drive 
public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput); 
}}
/* 
//zero heading of the robot using gyro
public void zeroHeading() {
   // m_gyro.reset();
}
/* 
//return heading of the robot
public double getHeading(){
    return (Double) null;
    //return m_gyro.getRotation2d().getDegrees();
}
//return turn rate of the robot
public double getTurnRate(){
    return (Double) null;
   // return -m_gyro.getRate();
}
}*/
