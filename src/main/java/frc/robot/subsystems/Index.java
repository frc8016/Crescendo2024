package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Index extends SubsystemBase {
  // index motor
  private final CANSparkMax m_index =
      new CANSparkMax(ShooterConstants.INDEX_ID, MotorType.kBrushless);

  public void init() {}
  // run the index
  public void runIndex(double speed) {
    m_index.set(speed);
  }
}
