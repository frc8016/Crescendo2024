package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class RunIntakeRollers extends Command {
    private Intake m_Intake;
    
    public RunIntakeRollers(Intake intake){
        m_Intake = intake;
        addRequirements(m_Intake);
    }
@Override
public void initialize(){

}

@Override
public void execute(){
    m_Intake.runIntake(IntakeConstants.INTAKE_SPEED);

}

@Override
public void end(boolean interrupted){
    m_Intake.runIntake(0);
}

@Override
public boolean isFinished(){
    return false;
}

}
