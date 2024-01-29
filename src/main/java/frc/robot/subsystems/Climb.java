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
//set the idle mode to brake 
public void setIdleMode(){
    m_left.setIdleMode(IdleMode.kBrake);
    m_right.setIdleMode(IdleMode.kBrake);
}
//move the climb
public void setClimbSpeed(double speed){
    m_left.set(speed);
}


@Override
public void periodic(){
    m_right.follow(m_left);

    setIdleMode(); 
}
}
