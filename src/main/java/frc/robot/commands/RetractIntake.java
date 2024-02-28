package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;

public class RetractIntake extends Command{
    private Intake m_Intake;
    
    public RetractIntake(Intake intake){
        m_Intake = intake;
        addRequirements(m_Intake);
    }

@Override
public void initialize(){
}

@Override
public void execute(){
    m_Intake.retractIntake();
}

@Override
public void end(boolean interrupted){
}

@Override
public boolean isFinished(){
    return false;
}
}
