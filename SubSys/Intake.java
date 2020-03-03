package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.RobotMap;

public class Intake extends Subsystem{
    static TalonSRX track = new TalonSRX(RobotMap.TRACK_MOTOR);
    static TalonSRX primary = new TalonSRX(RobotMap.INTAKE_MOTOR);
    static DigitalInput ballCheck;

    private static final double track_speed = 0.7;
    private static final double intake_speed = 0.3;

    private static int trackTimer = -1;
    //Timer timer = new Timer();
    private static DigitalInput lim = new DigitalInput(9);
    

    
    @Override
    protected void initDefaultCommand() {
        LiveWindow.setEnabled(false);
        //ballCheck = new DigitalInput(8);
        //lim = new DigitalInput(9);
    }

    public static void moveTrack(){
        moveTrack(track_speed);
    }
    public static void moveTrack(double speed){
        track.set(ControlMode.PercentOutput, speed);
    }
    public static void stopTrack(){
        moveTrack(0);
    }
    public static void moveTrackTime(int sec){
        /*if(timer.get() > sec){
            timer.reset();
        }
        if(!ballCheck.get()){
            timer.start();
            track.set(ControlMode.PercentOutput, track_speed);
        }
        timer.start();
        if(timer.hasPeriodPassed(10)){
            track.set(ControlMode.PercentOutput, 0);
            timer.stop();
        }*/
    }
    public static void spinIntake(){
        primary.set(ControlMode.PercentOutput, -intake_speed);
    }
    public static void incremental(int cycs){
        if(trackTimer > 0){
            trackTimer = trackTimer - 1;
            moveTrack();
        }
        else if(trackTimer == -1){
            trackTimer = cycs;
        }
        else{
            stopTrack();
            if(lim.get()){
                trackTimer = -1;
            }
        }
        
    }

    public static void store(){
        spinIntake();
        incremental(100);
        //TODO: Move a bit for each ball
    }
    public static void both(){
        moveTrack();
        spinIntake();
    }
    public static void none(){
        primary.set(ControlMode.PercentOutput, 0);
        track.set(ControlMode.PercentOutput, 0);
    }

}