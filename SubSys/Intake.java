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
    public static DigitalInput lim = new DigitalInput(9);

    private static final double track_speed = 0.95;
    private static final double intake_speed = 1; //THIS IS WHERE YOU CHANGE THE SPINNER SPEED

    //private static int trackTimer = -1;
    //Timer timer = new Timer();

    private static double timerStart = 0;
    

    
    @Override
    protected void initDefaultCommand() {
        LiveWindow.setEnabled(false);
        //ballCheck = new DigitalInput(8);
        //lim = new DigitalInput(9);
    }

    public static void moveTrack(){
        moveTrack(track_speed);
    }
    public static void moveTrackReverse(){
        moveTrack(-track_speed);
    }
    public static void moveTrack(double speed){
        track.set(ControlMode.PercentOutput, speed);
        System.out.println(track.getSelectedSensorPosition());
    }
    public static void stopTrack(){
        moveTrack(0);
    }
    public static void moveTrackTime(int sec){
        if(timerStart == 0){
            timerStart = Timer.getFPGATimestamp();
            System.out.println(Timer.getFPGATimestamp());
        }
        else if(Timer.getFPGATimestamp() - timerStart > sec){
            timerStart = 0;
            System.out.println(Timer.getFPGATimestamp());
            moveTrack(0);
        }
        moveTrack();
    }
    public static void startTimer(){
        if(timerStart == 0){
            timerStart = Timer.getFPGATimestamp();
        }
    }
    public static boolean hasPassedSec(double sec){
        if(timerStart == 0){
            return true; //IDK Might want to change
        }
        else if(Timer.getFPGATimestamp() - timerStart > sec){
            timerStart = 0;
            System.out.println(Timer.getFPGATimestamp());
            return true;
        }
        return false;
    }
    public static void spinIntake(){
        primary.set(ControlMode.PercentOutput, intake_speed); 
    }
    public static void spinIntake(double speed){
        primary.set(ControlMode.PercentOutput, speed); 
    }
    public static void spinIntakeReverse(){
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
        /*spinIntake();
        if(lim.get()){
            moveTrackTime(2);
        }
        else{
            track.setSelectedSensorPosition(0);
            stopTrack();
        }
        

        spinIntake();
        if(lim.get()){
            if(!hasPassedSec(2)){
                moveTrack();
            }
            else{
                moveTrack(0);
            }
        }
        else{
            moveTrack(0);
        } */

        
        spinIntake();
        if(lim.get()){
            startTimer();
        }

        if(!hasPassedSec(3)){
            moveTrack();
        }
        else{
            moveTrack(0);
        }
        

    }

    /*public static void releaseIntake()
    {
        startTimer();
        

        if(!hasPassedSec(1)){
            spinIntake(0);
        }
        else{
            spinIntake();
        }
    }*/

    public static void both(){
        moveTrack();
        spinIntake();
    }
    public static void bothReverse(){
        moveTrackReverse();
        spinIntakeReverse();
    }
    public static void none(){
        primary.set(ControlMode.PercentOutput, 0);
        track.set(ControlMode.PercentOutput, 0);
    }

}