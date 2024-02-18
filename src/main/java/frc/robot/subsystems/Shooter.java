package frc.robot.subsystems;
/*need to integrate lidar into the code 
and need to have a backup button 
that shoots at a set speed for  */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase{
    //shooter motor controllers
    private final static CANSparkMax m_shooterLeft = new CANSparkMax(ShooterConstants.SHOOTER_LEFT, MotorType.kBrushless);
    private final static CANSparkMax m_ShooterRight = new CANSparkMax(ShooterConstants.SHOOTER_RIGHT, MotorType.kBrushless);
    //index motor 
    private final CANSparkMax m_index = new CANSparkMax(ShooterConstants.INDEX_ID, MotorType.kBrushless);
    //beam break
    private final DigitalInput m_shooterBB = new DigitalInput(ShooterConstants.BEAM_BREAK_SHOOTER_ID);
 
    //runs the shooter
    public void runShooter(double speed){
        m_shooterLeft.set(speed);
        m_ShooterRight.set(-speed);
    }

    //run the index
    public void runIndex(double speed){
        m_index.set(speed);
    }
    //beam break stuff 
    public boolean shooterBeamBroken(){
        return !m_shooterBB.get();
    }
    public void shooterBeamBrokenTrue(){
        if(!m_shooterBB.get()){
            System.out.println("note in shooter");
        }
    }
//attempting to set the speed based on the lidar reading 
  public  double setShooterSpeed(double shooterSpeed){
        if(Lidar.getDistanceMM() >= 500){
          runShooter(1);
        }else if(Lidar.getDistanceMM() > 250){
            runShooter(.8);
        }else if(Lidar.getDistanceMM() >= 100){
            runShooter(.6);
        }else if(Lidar.getDistanceMM() < 50){
            runShooter(.5);
        }
        return shooterSpeed;
    }
    @Override 
    public void periodic(){
       SmartDashboard.putBoolean("Shooter Beam Break", shooterBeamBroken());
      // SmartDashboard.putNumber("Shooter Speed", setShooterSpeed());
       shooterBeamBrokenTrue();
    }

}