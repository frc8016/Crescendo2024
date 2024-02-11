package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;

public class BeamBreak extends SubsystemBase {
    private final DigitalInput m_beamBreakIntake = new DigitalInput(IntakeConstants.BEAM_BREAK_INTAKE_ID);//the id for the reciever 
    private final DigitalInput m_beamBreakIndex = new DigitalInput(ShooterConstants.BEAM_BREAK_SHOOTER_ID);//the id for the reciever

public boolean beamBrokenIntake(){
        return !m_beamBreakIntake.get();
    }
public boolean beamBrokenIndex(){
    return !m_beamBreakIndex.get();
}
@Override
public void periodic(){
    
    if (!m_beamBreakIntake.get()){
        System.out.println("Note intaked");
        SmartDashboard.putBoolean("Note intaked", beamBrokenIntake());
    }
    if (!m_beamBreakIndex.get()){
        System.out.println("Note intaked");
        SmartDashboard.putBoolean("Note indexed", beamBrokenIndex());
    }
}
    
}
