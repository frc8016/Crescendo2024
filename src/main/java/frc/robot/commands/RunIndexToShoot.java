package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Shooter;

public class RunIndexToShoot extends Command{
    private Index m_Index;

    public RunIndexToShoot(Index index) {
       m_Index = index;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Index);    
}
@Override 
public void initialize(){
   m_Index.init();
}

@Override
public void execute(){
    m_Index.runIndex(ShooterConstants.INDEX_SPEED);
   
}

@Override
public void end(boolean interrupted){
    m_Index.runIndex(0);
}
@Override
public boolean isFinished(){
    return false;
    
}

}
  
  


