 package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Intake extends SubsystemBase{
private final CANSparkMax m_Intake = new CANSparkMax(ShooterConstants.INTAKE, MotorType.kBrushless);

    public void runIntake(double speed){
        m_Intake.set(speed);
    }

}
 