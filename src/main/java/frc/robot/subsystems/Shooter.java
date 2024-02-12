package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Lidar;

public class Shooter extends SubsystemBase{
    //shooter motor controllers
    private final static CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final static CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);
    //index motor 
    private final CANSparkMax m_index = new CANSparkMax(ShooterConstants.INDEX_ID, MotorType.kBrushless);
    //lidar
    private static Lidar m_lidar = new Lidar();
    
    //runs the shooter
    public void runShooter(double speed){
        m_shooterLeft.set(speed);
        m_ShooterRight.set(-speed);
    }

    //run the index
    public void runIndex(double speed){
        m_index.set(speed);
    }
//attempting to set the speed based on the lidar reading 
  public  double setSpeed(double speed){
        if(m_lidar.getDistanceMM() >= 500){
          runShooter(1);
        }else if(m_lidar.getDistanceMM() > 250){
            runShooter(.8);
        }else if(m_lidar.getDistanceMM() >= 100){
            runShooter(.6);
        }else if(m_lidar.getDistanceMM() < 50){
            runShooter(.5);
        }
        return speed;
    }
    @Override 
    public void periodic(){
       
    }

}