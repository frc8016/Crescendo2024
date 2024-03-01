// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }
 
  public static Command scoreSpeakerAuto(Shooter shooter, Index index){
   return Commands.sequence(
    Commands.runOnce( 
      () -> shooter.runShooter(1), shooter
    ),
    new WaitCommand(2),
    Commands.runOnce(() -> index.runIndex(1), index),
    new WaitCommand(3)
   );
  }

  public static Command taxiToNote(){
    return null;
  }
  public static Command intakteAuto(Intake intake){
    return Commands.sequence(
    Commands.runOnce(() -> intake.extendIntake(), intake),
    //Commands.runOnce(()-> intake.runIntake(.5), intake),
    new WaitCommand(3),
    Commands.runOnce(() -> intake.retractIntake(), intake));
  }

  public static Command driveToShoot(){
    return null;
  }



  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
