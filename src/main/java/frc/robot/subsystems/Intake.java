package frc.robot.subsystems;

import java.util.function.BooleanSupplier;


/*need to figure out the beam break thing 
 * essentially when to call it and cancling it and stuff
 */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.event.BooleanEvent;
import edu.wpi.first.wpilibj.event.EventLoop;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    //pnumatic cylenders 
    private final DoubleSolenoid m_solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.SOLENOID_FWD, IntakeConstants.SOLENOID_BKW);
  
   
    //Beam breaks
    private final DigitalInput m_IntakeBB1 = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID1);
   // private final DigitalInput m_IntakeBB2 = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID2);

    private final AnalogInput intakeInput = new AnalogInput(0);
   // private final EventLoop m_Loop = new EventLoop();
    //BooleanEvent checkbb1 = new BooleanEvent(m_loop, m_con)
//run the rollers to intake note with neo 

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
    return m_IntakeBB1.get();
}
public int intake(){
    return intakeInput.getValue();
}

public BooleanSupplier m_BooleanSupplier(){
    return (() -> beamBroken());
}

@Override
public void periodic(){
    m_BooleanSupplier();
    System.out.println(m_BooleanSupplier());
}

}

