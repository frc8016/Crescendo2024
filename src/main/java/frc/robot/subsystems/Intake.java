package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    //pnumatic cylenders 
    private final DoubleSolenoid m_solenoidLeft = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.SOLENOID_LEFT_FWD, IntakeConstants.SOLENOID_LEFT_BKW);
    private final DoubleSolenoid m_solenoidRight = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.SOLENOID_RIGHT_FWD, IntakeConstants.SOLENOiD_RIGHT_BKW);
    //neo for the rollers
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR, MotorType.kBrushless);
//run the rollers to intake note with neo 
public void runIntake(double speed){
    m_intakeMotor.set(speed);
}
//extends intake over the bumpers using pnumatics
public void extendIntake(){
    m_solenoidLeft.set(DoubleSolenoid.Value.kForward);
    m_solenoidRight.set(DoubleSolenoid.Value.kForward);
}
//retract intake into starting position using pnumatics
public void retractIntake(){
    m_solenoidLeft.set(DoubleSolenoid.Value.kReverse);
    m_solenoidRight.set(DoubleSolenoid.Value.kReverse);
}
//turns off the intake pnumatics
public void intakeOff(){
    m_solenoidLeft.set(DoubleSolenoid.Value.kOff);
    m_solenoidRight.set(DoubleSolenoid.Value.kOff);
}

}
