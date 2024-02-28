/*package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ClimbConstants;
import frc.robot.subsystems.Climb;

//raises the climb to the correct hight to get on the chain 
public class RaiseClimb extends Command{
    private Climb m_Climb;
  

    public RaiseClimb(Climb climb){
       m_Climb = climb;
       addRequirements(climb);
    }
    @Override
    public void initialize(){}

    @Override
    public void execute(){
        m_Climb.setClimbSpeed(ClimbConstants.CLIMB_SPEED, ClimbConstants.CLIMB_SPEED);
    }

    @Override
    public void end(boolean interrupted){
        m_Climb.setClimbSpeed(0, 0);
    }
    @Override
    public boolean isFinished(){
        return false;
    }
    


}*/
