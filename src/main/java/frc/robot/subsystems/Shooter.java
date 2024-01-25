package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    private final CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);



    public void runShooter(double speed){
        m_shooterLeft.set(ShooterConstants.SHOOTER_LEFT_SPEED);
        m_ShooterRight.set(ShooterConstants.SHOOTER_RIGHT_SPEED);
    }
}
