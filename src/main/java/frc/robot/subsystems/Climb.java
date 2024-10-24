package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class Climb extends SubsystemBase {
  // motor controllers
  private final CANSparkMax m_left = new CANSparkMax(ClimbConstants.LEFT, MotorType.kBrushless);
  private final CANSparkMax m_right = new CANSparkMax(ClimbConstants.RIGHT, MotorType.kBrushless);

  public Climb() {
    m_left.setInverted(true);
  }

  public void raiseClimb(double speed) {
    m_left.set(-speed);
    m_right.set(-speed);
  }

  public void lowerClimb(double speed) {
    m_left.set(speed);
    m_right.set(speed);
  }

  @Override
  public void periodic() {
    m_right.follow(m_left);
  }
}
