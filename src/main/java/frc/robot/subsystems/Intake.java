package frc.robot.subsystems;

import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    //pnumatic cylenders 
    private final DoubleSolenoid m_solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, IntakeConstants.SOLENOID_FWD, IntakeConstants.SOLENOID_BKW);
    //Beam breaks
    private final DigitalInput m_IntakeBB1 = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID1);
  
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

    public BooleanSupplier m_BooleanSupplier(){
        return (() -> m_IntakeBB1.get() == false);
    }

    public void getAsBoolean(){
        m_BooleanSupplier().getAsBoolean();
    }


    @Override
    public void periodic(){
        m_BooleanSupplier();
        System.out.println(m_BooleanSupplier());
        System.out.println("beam break get " + beamBroken());

        SmartDashboard.putBoolean("Note in intake", !beamBroken());
    }
    }

