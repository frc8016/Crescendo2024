// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command exampleAuto(ExampleSubsystem subsystem) {
    return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  }


  public static Command autoGroup(Shooter shooter, Index index, Intake intake, IntakeMotor intakeMotor){
    return
    Commands.sequence(
      Commands.parallel(
        Commands.runOnce(
        () -> shooter.runShooter(ShooterConstants.SHOOTER_SPEED), shooter),
        Commands.runOnce(
          () -> index.runIndex(ShooterConstants.INDEX_SPEED), index)),
        new WaitCommand(2),
        Commands.runOnce(() -> intakeMotor.runIntake(-.5), intakeMotor));  
  }




 /*runs the shooter and index at the same time :) */
  public static Command runShooterAuto(Shooter shooter, Index index){
   return 
    Commands.parallel(
      Commands.runOnce( 
      () -> shooter.runShooter(ShooterConstants.SHOOTER_SPEED), shooter),
    Commands.runOnce(() -> index.runIndex(ShooterConstants.INDEX_SPEED), index));
  }


  /*runs the intake motor to feed the note into the shooter */
  public static Command feedIntoShooterAuto(IntakeMotor intakeMotor){
    return 
    Commands.runOnce(
      () -> intakeMotor.runIntake(-.5), intakeMotor);
  }


  public static Command taxiToNote(){
    return null;
  }
  /*extend the intake to pick up a note  */
  public static Command extendIntakeAuto(Intake intake){
    return Commands.sequence(
    Commands.runOnce(() -> intake.extendIntake(), intake));
  }


  /*runs the intake motor to pick up the note */
  


/*retracts the intake  */
  public static Command retractIntakeAuto(Intake intake){
    return Commands. sequence(
      Commands.runOnce(() -> intake.retractIntake(), intake));
  }

  

  public static Command driveToShoot(){
    return null;
  }




  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
