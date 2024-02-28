package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class BeamBreakIntake extends Command {
    private Intake m_Intake;

    public BeamBreakIntake(Intake intake){
        m_Intake = intake;
        addRequirements(m_Intake);
    }
@Override
public void initialize(){}

@Override
public void execute(){
if (m_Intake.beamBroken() == true){
    m_Intake.runIntake(0);
}

}

@Override
public void  end(boolean interrupted){
   
}
public boolean sendData(boolean b){
   return m_Intake.beamBroken();
}
}
