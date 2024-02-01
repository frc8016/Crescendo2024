package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    
    private final DigitalInput m_beamBreakIntake = new DigitalInput(IntakeConstants.BEAM_BREAK_ID);



@Override
public void periodic(){
    if (m_beamBreakIntake.get()){
        System.out.println("Note intaked");
        SmartDashboard.putString("Note intaked", "yes");
    }
}
}
