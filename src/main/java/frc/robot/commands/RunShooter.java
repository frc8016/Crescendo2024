package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants.ShooterConstants;

//run the shooter to start the flywheel wait fir it to get up to speed then feed the note into it using the intake :)
public class RunShooter extends Command{
    private Shooter m_shooter = new Shooter();

    public RunShooter(Shooter shooter) {
        Commands.sequence(
            new StartEndCommand(
              () ->m_shooter.runShooter(ShooterConstants.SHOOTER_SPEED_LEFT, ShooterConstants.SHOOTER_SPEED_RIGHT), 
              ()-> m_shooter.runShooter(0, 0), 
              m_shooter),
            new WaitCommand(2), 
            new StartEndCommand(
              () -> m_shooter.runIndex(ShooterConstants.INDEX_SPEED),
              () -> m_shooter.runIndex(0),
              m_shooter),
            new WaitCommand(2)
          );
    }

    @Override
    public void initialize(){}  
}
