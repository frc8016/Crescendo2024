package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    //shooter motor controllers
    private final CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);
    //index motor 
    private final CANSparkMax m_index = new CANSparkMax(ShooterConstants.INDEX_ID, MotorType.kBrushless);
    //ultrasonic sensor 
    

    
    //runs the shooter
    public void runShooter(double leftSpeed, double rightSpeed){
        m_shooterLeft.set(leftSpeed);
        m_ShooterRight.set(rightSpeed);
    }

    //run the index
    public void runIndex(double speed){
        m_index.set(speed);
    }


}