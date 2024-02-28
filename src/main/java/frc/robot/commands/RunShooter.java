package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

public class RunShooter extends Command{
    private Shooter m_Shooter ;

      public RunShooter(Shooter shooter) {
        m_Shooter = shooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);    
}
@Override 
public void initialize(){
  
}

@Override
public void execute(){
    m_Shooter.runShooter(ShooterConstants.SHOOTER_SPEED);
   
}

@Override
public void end(boolean interrupted){
    m_Shooter.runShooter(0);
}
@Override
public boolean isFinished(){
    return false;
    
}

}
  
  

