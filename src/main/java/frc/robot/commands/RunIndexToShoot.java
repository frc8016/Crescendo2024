package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Shooter;

public class RunIndexToShoot extends Command{
    private Shooter m_Shooter ;

    public RunIndexToShoot(Shooter shooter) {
        m_Shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);    
}
@Override 
public void initialize(){
   
}

@Override
public void execute(){
    m_Shooter.runIndex(ShooterConstants.INDEX_SPEED);
   
}

@Override
public void end(boolean interrupted){
    m_Shooter.runIndex(0);
}
@Override
public boolean isFinished(){
    return false;
    
}

}
  
  


