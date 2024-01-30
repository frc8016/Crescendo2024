package frc.robot.subsystems;

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
}
//raise the hook -- hooks rise to grab chain 
public void raiseClimb(double leftSpeed, double rightSpeed){
    m_left.set(leftSpeed);
    m_right.set(rightSpeed);
}
//lower clim -- robot climbs on the chain 
public void lowerClimb(double leftSpeed, double rightSpeed){
    m_left.set(-leftSpeed);
    m_right.set(-rightSpeed);
}

@Override
public void periodic(){
    m_right.follow(m_left);

    setIdleMode(); 
}
}
