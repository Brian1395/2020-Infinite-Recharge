package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Vision;

public class Shooter extends Subsystem{
    static TalonSRX leftShootie = new TalonSRX(RobotMap.SHOOTER_LEFT);
    static TalonSRX rightShootie = new TalonSRX(RobotMap.SHOOTER_RIGHT);
    Compressor comp = new Compressor();
    static DoubleSolenoid pushie = new DoubleSolenoid(RobotMap.SHOOTER_FOWARD,RobotMap.SHOOTER_BACK);
    static DigitalInput chamber = new DigitalInput(RobotMap.CHAMBER_LIMIT_SWITCH);
    //static DoubleSolenoid pushie = new DoubleSolenoid(RobotMap.SHOOTER_FOWARD, RobotMap.SHOOTER_BACK);

    private static double defaultShootieSpeed = 1;
    private static boolean reving = false;

    private static double timerStart = 0;
    

    @Override
    protected void initDefaultCommand() {
        comp.start();
        
    }

    public static boolean isLoaded(){
        return !chamber.get();
    }

    public static boolean isReady(){
        if(isLoaded()){
            if(timerStart == 0){
                timerStart = Timer.getFPGATimestamp();
                System.out.println(Timer.getFPGATimestamp());
            }
            else if(Timer.getFPGATimestamp() - timerStart > 2){
                timerStart = 0;
                System.out.println(Timer.getFPGATimestamp());
                return true;
            }
        }
        return false;
    }

    public static void periodicRun(){
        if(reving){
            rev();
        }
    }

    public static void toggle(){
        reving = !reving;
    }

    public static void rev(){
        rev(defaultShootieSpeed);
    }
    public static void rev(double speed){
        leftShootie.set(ControlMode.PercentOutput, speed);
        rightShootie.set(ControlMode.PercentOutput, -speed);

        
    }

    public static void none(){
        rev(0);
    }

    public static void fire(){
        pushie.set(Value.kForward);
    }

    public static void reset(){
        if(pushie.get() == Value.kForward){
            pushie.set(Value.kReverse);
        }
    }

    public static void full(){
        rev();
        if(Vision.lineUpShoot()){
            if(isLoaded()){
                Intake.stopTrack();
            }
            else{
                Intake.moveTrack();
            }
            if(isReady()){
                fire();
            }
            else{
                reset();
            }
        }
    }

    public static void altFull(){ //Looks like were just going with this //Incase we can't get the dropping into the chamer right
        if(Vision.lineUpShoot()){
            Intake.moveTrack();
            if(isReady()){
                fire();
            }
            else if(isLoaded()){
                Intake.stopTrack();
                rev();
            } 
            else{
                reset();
                none();
            }
        }
    }
}