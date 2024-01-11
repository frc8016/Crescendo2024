package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class DriveTrain extends SubsystemBase {
    
   
    //motor controllers 
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backLeft = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backRight = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    //motor controller groups
  
    
    //arcade drive 
}
