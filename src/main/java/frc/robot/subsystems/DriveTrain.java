package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import com.ctre.phoenix6.hardware.CANcoder;
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
// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);
}

//arcade drive (actually drives the robot!)
public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(-speed, -rotation);
}

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
