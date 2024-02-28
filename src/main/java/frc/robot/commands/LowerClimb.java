package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.Constants.ClimbConstants;
import frc.robot.subsystems.Climb;

//lowers the climb onto teh chain to raise the robot
public class LowerClimb extends Command{
     private Climb m_Climb;

    public LowerClimb(Climb climb){
       m_Climb = climb;
       addRequirements(climb);
    }
    @Override
    public void initialize(){}

    @Override
    public void execute(){
        m_Climb.setClimbSpeed(-ClimbConstants.CLIMB_SPEED, -ClimbConstants.CLIMB_SPEED);
    }

    @Override
    public void end(boolean interrupted){
        m_Climb.setClimbSpeed(0, 0);
    }
    @Override
    public boolean isFinished(){
        return false;
    }
    


}


