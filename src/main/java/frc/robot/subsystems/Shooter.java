package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private final CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);
   

    public void runShooter(double leftSpeed, double rightSpeed){
        m_shooterLeft.set(leftSpeed);
        m_ShooterRight.set(rightSpeed);
    }


}