package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem{
    static TalonFX frontLeft = new TalonFX(RobotMap.FRONT_LEFT);
    static TalonFX backLeft = new TalonFX(RobotMap.BACK_LEFT);
    static TalonFX frontRight = new TalonFX(RobotMap.FRONT_RIGHT);
    static TalonFX backRight = new TalonFX(RobotMap.BACK_RIGHT);

    //Speeds
    private static final double speedmult = .3; 
    private static double defaultsidespeed = .5;

    //Encoder Vals
    //private static final double encodePerFoot = 14000; //This is not accurate in any significant way
    private static final double encodePerRot = 4096;
    private static final int wheelDiameter = 8; //inches


    public void initDefaultCommand(){

    }

    public static void setup(){//Runs at beginning to prep for round
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
        setBoth(0);
    }


    

    //Calculations
    /*
    private static double toDeg(double units){
        double deg = units * 360 / encodePerRot;
        deg *= 10;
        deg = (int)deg;
        deg /= 10;
        return deg;
    }

    private static double toRots(double units){
        double deg = units / encodePerRot;
        deg *= 10;
        deg = (int)deg;
        deg /= 10;
        return deg;
    }*/

    private static double fromRots(double units){
        double deg = units * encodePerRot;
        deg = (int)deg;
        return deg;
    }

    


    //Movement
    public static void moveFeet(int dist){//This DO NOT work   //TODO: Switch to PID 
        double leftVal = (frontLeft.getSelectedSensorPosition() + backLeft.getSelectedSensorPosition())/2;
        double targetVal = fromRots((dist * 12)/(wheelDiameter*Math.PI));
        System.out.println(leftVal);
        if(leftVal < targetVal){
            setBoth(.3);
        }
        else if(leftVal > targetVal){
            setBoth(-.3);
        }
        else{
            setBoth(0);
        }
    }
    public static boolean moveFeetAuto(int dist){//TODO: Switch to PID 
        double leftVal = (frontLeft.getSelectedSensorPosition() + backLeft.getSelectedSensorPosition())/2;
        //double targetVal = fromRots((dist * 12)/(wheelDiameter*Math.PI));
        double targetVal = 16000 * dist;
        //dist * 8196;
        //System.out.println(targetVal);
        System.out.println(leftVal);
        if(leftVal < targetVal){
            setBoth(.6);
        }
        else{
            setBoth(0);
            return true;
        }
        return false;
    }

    public static void slideFeet(int dist){//TODO: Switch to PID 
        double crossPair1 = (frontLeft.getSelectedSensorPosition() + backRight.getSelectedSensorPosition())/2;
        double targetVal = fromRots((dist * 12)/(wheelDiameter*Math.PI));
        System.out.println(crossPair1);
        if(crossPair1 < targetVal){
            slide(true);
        }
        if(crossPair1 > targetVal){
            slide(false);
        }
        else{
            setBoth(0);
        }
    }

    public static boolean slideFeetAuto(int dist){//TODO: Switch to PID 
        double crossPair1 = (frontLeft.getSelectedSensorPosition() - backRight.getSelectedSensorPosition())/2;
        double targetVal = 14000 * dist;
        if(Math.abs(crossPair1) < Math.abs(targetVal) && targetVal < 0){
            slide(false);
        }
        else if(Math.abs(crossPair1) < Math.abs(targetVal) && targetVal > 0){
            slide(true);
        }
        else{
            setBoth(0);
            return true;
        }
        return false;
    }

    public static void slide(){
        slide(defaultsidespeed);
    }
    public static void slide(boolean right){
        if(right)
            slide(defaultsidespeed);
        else
            slide(-defaultsidespeed);
    }
    public static void slide(double speed){
        frontLeft.set(ControlMode.PercentOutput, speedmult* speed);
        backLeft.set(ControlMode.PercentOutput, -speedmult * speed);
        frontRight.set(ControlMode.PercentOutput, speedmult * speed);
        backRight.set(ControlMode.PercentOutput, -speedmult * speed);
    }

    public static void setBoth(double speed){
        setLeft(speed);
        setRight(speed);
    }
    public static void setLeft(double speed){
        frontLeft.set(ControlMode.PercentOutput, speedmult * speed);
        backLeft.set(ControlMode.PercentOutput, speedmult * speed);
    }

    public static void setRight(double speed){
        frontRight.set(ControlMode.PercentOutput, -speedmult * speed);
        backRight.set(ControlMode.PercentOutput, -speedmult * speed);
    }

    public static void stop(){
        setBoth(0);
    }
}