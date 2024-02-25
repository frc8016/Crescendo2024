package frc.robot.subsystems;

/*need to figure out the beam break thing 
 * essentially when to call it and cancling it and stuff
 */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    //pnumatic cylenders 
    private final DoubleSolenoid m_solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.SOLENOID_LEFT_FWD, IntakeConstants.SOLENOID_LEFT_BKW);
  
    //neo for the rollers
    private final CANSparkMax m_intakeMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR, MotorType.kBrushless);
    //Beam breaks
    private final DigitalInput m_IntakeBB1 = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID1);
    private final DigitalInput m_IntakeBB2 = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID2);
//run the rollers to intake note with neo 
public void runIntake(double speed){
    m_intakeMotor.set(speed);
}
//extends intake over the bumpers using pnumatics
public void extendIntake(){
    m_solenoid.set(DoubleSolenoid.Value.kForward);
   
}
//retract intake into starting position using pnumatics
public void retractIntake(){
    m_solenoid.set(DoubleSolenoid.Value.kReverse);
   
}
//turns off the intake pnumatics
public void intakeOff(){
    m_solenoid.set(DoubleSolenoid.Value.kOff);
    
}
public boolean beamBroken(){
    return !m_IntakeBB1.get() && !m_IntakeBB2.get();
}

//Beam break stops intake motors 
public void beamBrokenStopMotor(){
    if(!m_IntakeBB1.get() && m_IntakeBB2.get()){
        runIntake(0);
        System.out.println("Note Intaked");
        SmartDashboard.putBoolean("Note in intake", beamBroken());
    }
}

@Override
public void periodic(){

}
}
