package frc.robot.commands;


import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Shooter;

import org.ejml.equation.Sequence;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public final class RobotCommands {
    private final static Shooter m_Shooter = new Shooter();
    
    public static Command runShooter(){
        return new StartEndCommand(
            () ->m_Shooter.runShooter(ShooterConstants.SHOOTER_SPEED_LEFT, ShooterConstants.SHOOTER_SPEED_RIGHT), 
            ()-> m_Shooter.runShooter(0, 0), 
            m_Shooter);
    }

    
    }



