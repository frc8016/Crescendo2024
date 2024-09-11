package frc.robot.subsystems;
/*need to integrate lidar into the code 
and need to have a backup button 
that shoots at a set speed for  */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    //shooter motor controllers
    private final static CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final static CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);

    public Shooter(){
        setIdleModeBrake();
    }
    //runs the shooter
    public void runShooter(double speed){
        m_shooterLeft.set(speed);
        m_ShooterRight.set(-speed);
    }

    
    public void setIdleModeBrake(){
        m_ShooterRight.setIdleMode(IdleMode.kBrake);
        m_shooterLeft.setIdleMode(IdleMode.kBrake);

        m_ShooterRight.burnFlash();
        m_shooterLeft.burnFlash();
    }
    
    
    @Override 
    public void periodic(){}


    }
