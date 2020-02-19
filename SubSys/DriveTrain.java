package frc.robot.SubSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem{
    TalonFX frontLeft = new TalonFX(RobotMap.FRONT_LEFT);
    TalonFX backLeft = new TalonFX(RobotMap.BACK_LEFT);
    TalonFX frontRight = new TalonFX(RobotMap.FRONT_RIGHT);
    TalonFX backRight = new TalonFX(RobotMap.BACK_RIGHT);

    //Speeds
    public final double speedmult = 1; 
    private double defaultsidespeed = 0.7;

    //Encoder Vals
    //private final double encodePerFoot = 14000; //This is not accurate in any significant way
    private final double encodePerRot = 4096;
    private final int wheelDiameter = 8; //inches


    public void initDefaultCommand(){

    }

    public void setUp(){//Runs at beginning to prep for round
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }


    

    //Calculations
    /*
    private double toDeg(double units){
        double deg = units * 360 / encodePerRot;
        deg *= 10;
        deg = (int)deg;
        deg /= 10;
        return deg;
    }

    private double toRots(double units){
        double deg = units / encodePerRot;
        deg *= 10;
        deg = (int)deg;
        deg /= 10;
        return deg;
    }*/

    private double fromRots(double units){
        double deg = units * encodePerRot;
        deg = (int)deg;
        return deg;
    }


    //Movement
    public void moveFeet(int dist){//TODO: Switch to PID 
        double leftVal = (frontLeft.getSelectedSensorPosition() + backLeft.getSelectedSensorPosition())/2;
        double targetVal = fromRots((dist * 12)/(wheelDiameter*Math.PI));
        System.out.println(leftVal);
        if(leftVal < targetVal){
            setBoth(.3);
        }
        if(leftVal > targetVal){
            setBoth(-.3);
        }
        else{
            setBoth(0);
        }
    }

    public void slide(){
        slide(defaultsidespeed);
    }
    public void slide(boolean right){
        if(right)
            slide(speedmult * defaultsidespeed);
        else
            slide(-speedmult * defaultsidespeed);
    }
    public void slide(double speed){
        frontLeft.set(ControlMode.PercentOutput, speedmult* speed);
        backLeft.set(ControlMode.PercentOutput, -speedmult * speed);
        frontRight.set(ControlMode.PercentOutput, speedmult * speed);
        backRight.set(ControlMode.PercentOutput, -speedmult * speed);
    }

    public void setBoth(double speed){
        setLeft(speed);
        setRight(speed);
    }
    public void setLeft(double speed){
        frontLeft.set(ControlMode.PercentOutput, speedmult * speed);
        backLeft.set(ControlMode.PercentOutput, speedmult * speed);
    }

    public void setRight(double speed){
        frontRight.set(ControlMode.PercentOutput, -speedmult * speed);
        backRight.set(ControlMode.PercentOutput, -speedmult * speed);
    }

    public void stop(){
        setBoth(0);
    }
}