package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Intake extends Subsystem{
    TalonSRX track = new TalonSRX(RobotMap.TRACK_MOTOR);
    TalonSRX primary = new TalonSRX(RobotMap.INTAKE_MOTOR);
    //DigitalInput ballCheck = new DigitalInput(RobotMap.BALL_LIMIT_SWITCH);

    private double track_speed = 0.7;
    private double intake_speed = 0.3;

    Timer timer = new Timer();
    
    @Override
    protected void initDefaultCommand() {
        
    }

    public void moveTrack(){
        track.set(ControlMode.PercentOutput, track_speed);
    }
    public void moveTrackTime(int sec){
        /*if(timer.get() > sec){
            timer.reset();
        }
        if(!ballCheck.get()){
            timer.start();
            track.set(ControlMode.PercentOutput, track_speed);
        }*/
        timer.start();
        if(timer.hasPeriodPassed(10)){
            track.set(ControlMode.PercentOutput, 0);
            timer.stop();
        }
    }
    public void spinIntake(){
        primary.set(ControlMode.PercentOutput, intake_speed);
    }

    public void store(){
        spinIntake();
        //TODO: Move a bit for each ball
    }
    public void both(){
        moveTrack();
        spinIntake();
    }
    public void none(){
        primary.set(ControlMode.PercentOutput, 0);
        track.set(ControlMode.PercentOutput, 0);
    }
    public void full(){
        spinIntake();
        //moveTrackTime(10);
        //System.out.println(timer.get());
    }
}