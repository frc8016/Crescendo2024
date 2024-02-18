package frc.robot.subsystems;

/*climb shoudl be done 
 * 2 commands, one raises, one lowers, they should lock 
 */
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class Climb extends SubsystemBase{
    //motor controllers
    private final CANSparkMax m_left = new CANSparkMax(ClimbConstants.LEFT, MotorType.kBrushless);
    private final CANSparkMax m_right = new CANSparkMax(ClimbConstants.RIGHT, MotorType.kBrushless);

public Climb(){

}
//set the idle mode to brake, hold robot on chain 
public void setIdleMode(){
    m_left.setIdleMode(IdleMode.kBrake);
    m_right.setIdleMode(IdleMode.kBrake);

    m_left.burnFlash();
    m_right.burnFlash();
}

public void setClimbSpeed(double leftSpeed, double rightSpeed){
    m_left.set(leftSpeed);
    m_right.set(rightSpeed);
}

@Override
public void periodic(){
    m_right.follow(m_left);

    setIdleMode(); 
}
}
