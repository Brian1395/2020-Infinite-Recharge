package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Lift extends Subsystem{
    //static TalonSRX lift = new TalonSRX(RobotMap.LIFT_MOTOR);
    static VictorSPX lift = new VictorSPX(RobotMap.LIFT_MOTOR);
    static TalonSRX climb1 = new TalonSRX(RobotMap.CLIMB_MOTOR1);
    static TalonSRX climb2 = new TalonSRX(RobotMap.CLIMB_MOTOR2);
    

    final static double defaultLiftSpeed = 1;
    final static double defaultClimbSpeed = 1;
    final static double holdPower = .2;
    final static double releaseSpeed = .4;

	@Override
	protected void initDefaultCommand() {
		
    }
    
    public static void raise(){
        lift.set(ControlMode.PercentOutput,defaultLiftSpeed);
    }

    public static void lower(){
        lift.set(ControlMode.PercentOutput,-defaultLiftSpeed);
    }

    public static void climb(){
        climb1.setNeutralMode(NeutralMode.Brake);
        climb2.setNeutralMode(NeutralMode.Brake);

        climb1.set(ControlMode.PercentOutput,-defaultClimbSpeed);
        climb2.set(ControlMode.PercentOutput,-defaultClimbSpeed);
    }

    public static void release(){
        climb1.set(ControlMode.PercentOutput,releaseSpeed);
        climb2.set(ControlMode.PercentOutput,releaseSpeed);
    }

    public static void hold(){
        climb1.set(ControlMode.PercentOutput,-holdPower);
        climb2.set(ControlMode.PercentOutput,holdPower);
    }

    public static void none(){
        lift.set(ControlMode.PercentOutput,0);
        climb1.set(ControlMode.PercentOutput,0);
        climb2.set(ControlMode.PercentOutput,0);
    }

    public static void stay(){
        lift.set(ControlMode.PercentOutput,0);
    }

}