package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.CubeShooterConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;


public class CubeShooter extends SubsystemBase {

    private CANSparkMax shooterMotor = new CANSparkMax(-1, MotorType.kBrushless);
    private CANSparkMax feederMotor = new CANSparkMax(-1, MotorType.kBrushless);

    private RelativeEncoder shooterEncoder = shooterMotor.getEncoder();

    private double shooterSpeed = CubeShooterConstants.kDefaultShooterSpeed;
    private double feederSpeed = CubeShooterConstants.kFeederMotorSpeed;

    private static CubeShooter instance;
    
    public static CubeShooter getInstance() {
        if (instance == null) {
            instance = new CubeShooter();
        }
        return instance;
    }

    private CubeShooter() {
        shooterMotor.restoreFactoryDefaults();
        feederMotor.restoreFactoryDefaults();

        shooterMotor.setIdleMode(IdleMode.kCoast);

        shooterMotor.burnFlash();
        feederMotor.burnFlash();
    }

    public void run() {
        shooterMotor.set(shooterSpeed);
    }

    public void feedIn() {
        feederMotor.set(-feederSpeed);
    }

    public void feedOut() { // formerly named vomit
        feederMotor.set(feederSpeed);
    }

    public void stop() {
        shooterMotor.set(0);
        feederMotor.set(0);
    }

    protected double getVeloctiy() {
        return shooterEncoder.getVelocity();
    }

    protected double getSpeed() {
        return this.shooterSpeed;
    }
}
