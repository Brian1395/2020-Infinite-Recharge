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
    private static DigitalInput lim = new DigitalInput(9);

    private static final double track_speed = 0.8;
    private static final double intake_speed = .3; //THIS IS WHERE YOU CHANGE THE SPINNER SPEED

    private static int trackTimer = -1;
    //Timer timer = new Timer();
    

    
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
        int trackEnc = track.getSelectedSensorPosition();
        if(trackEnc > -cycs){
            moveTrack();
        }
        else{
            stopTrack();
        }
        
    }

    public static void store(){
        spinIntake();
        if(lim.get()){
            incremental(1000000);
        }
        else{
            track.setSelectedSensorPosition(0);
            stopTrack();
        }
        
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