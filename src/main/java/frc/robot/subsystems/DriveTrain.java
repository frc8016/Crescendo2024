package frc.robot.subsystems;



import static edu.wpi.first.units.MutableMeasure.mutable;
import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;

import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.units.MutableMeasure;
import edu.wpi.first.units.Velocity;
import edu.wpi.first.units.Voltage;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import frc.robot.Constants.DriveTrainConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix6.hardware.CANcoder;

public class DriveTrain extends SubsystemBase {
    //motor controllers 
    private final CANSparkMax m_frontLeft = new CANSparkMax(DriveTrainConstants.FRONT_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backLeft = new CANSparkMax(DriveTrainConstants.BACK_LEFT_ID, MotorType.kBrushless);
    private final CANSparkMax m_frontRight = new CANSparkMax(DriveTrainConstants.FRONT_RIGHT_ID, MotorType.kBrushless);
    private final CANSparkMax m_backRight = new CANSparkMax(DriveTrainConstants.BACK_RIGHT_ID, MotorType.kBrushless);
    //motor controller groups
    private final DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);
    //Encoders
    private final CANcoder m_leftEncoder = new CANcoder(10);
    private final CANcoder m_rightEncoder = new CANcoder(9);
    //pigeon
    private final PigeonIMU m_pigeon = new PigeonIMU(11);

   // private final DifferentialDriveOdometry m_Odometry = new DifferentialDriveOdometry(null, null, null);
/*sysID stuff 
(my favorite)*/
    private final MutableMeasure<Voltage> m_appliedVoltage = mutable(Volts.of(0));
    private final MutableMeasure<Distance> m_distance = mutable(Meters.of(0));
    private final MutableMeasure<Velocity<Distance>> m_velocity = mutable(MetersPerSecond.of(0));

    private final SysIdRoutine m_SysIdRoutine= 
        new SysIdRoutine(
            new SysIdRoutine.Config(), 
            new SysIdRoutine.Mechanism(
                (Measure<Voltage> volts) -> {
                    m_frontLeft.setVoltage(volts.in(Volts));
                    m_frontRight.setVoltage(volts.in(Volts));
                }, 
                log -> {
                    log.motor("left drive")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                            m_frontLeft.get() * RobotController.getBatteryVoltage(), Volts))
                            .linearPosition(m_distance.mut_replace(
                                (m_leftEncoder).getPositionSinceBoot().getValue(), Meters))
                            .linearVelocity(m_velocity.mut_replace(
                                m_leftEncoder.getVelocity().getValue(), MetersPerSecond));
                    log.motor("right drive")
                    .voltage(
                        m_appliedVoltage.mut_replace(
                            m_frontRight.get() * RobotController.getBatteryVoltage(), Volts))
                        .linearPosition(m_distance.mut_replace(
                            m_rightEncoder.getPositionSinceBoot().getValue(), Meters))
                        .linearVelocity(
                            m_velocity.mut_replace(
                                m_rightEncoder.getVelocity().getValue(), MetersPerSecond));
                },
                this));

// new drive subsystem
public DriveTrain(){
    m_backLeft.follow(m_frontLeft);
    m_backRight.follow(m_frontRight);
    //invert one side of drivetrain 
    m_frontLeft.setInverted(true);
    m_frontRight.setInverted(false);
}
/*encoder and pigeon things */


//arcade drive (actually drives the robot!)
public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(-speed, -rotation);
}


/*more sysID stuff 
 * yaaaaaaay
 */
public Command sysIdQuasistatic(SysIdRoutine.Direction direction){
    return m_SysIdRoutine.quasistatic(direction);
}

public Command sysIdDynamic(SysIdRoutine.Direction direction){
    return m_SysIdRoutine.dynamic(direction);
}



}