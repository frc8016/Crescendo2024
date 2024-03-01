package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMotor;

public class RunIntakeRollers extends Command {
    private IntakeMotor m_IntakeMotor;
    
    public RunIntakeRollers(IntakeMotor intakeMotor){
        m_IntakeMotor = intakeMotor;
        addRequirements(m_IntakeMotor);
    }
@Override
public void initialize(){

}

@Override
public void execute(){
    m_IntakeMotor.runIntake(IntakeConstants.INTAKE_SPEED);

}

@Override
public void end(boolean interrupted){
    m_IntakeMotor.runIntake(0);
}

@Override
public boolean isFinished(){
    return false;
}

}
