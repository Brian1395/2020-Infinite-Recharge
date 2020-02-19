package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


public class ShuffleboardControl{
    ShuffleboardTab tab = Shuffleboard.getTab("Inf Recharge");
    //NetworkTableInstance inst = NetworkTableInstance.getDefault();
    //NetworkTable table = inst.getTable("Smart Dashboard");
    //NetworkTableEntry inputEntry = table.getEntry("InputType");

    Inputs input = new Inputs();

    public void setup(){
        tab.add("InputType", input.inputTypeList[input.inputType]).withSize(2,1).withPosition(0, 0);
        tab.add("DriveType", input.driveTypeList[input.driveType]).withSize(2,1).withPosition(2, 0);
        
    /*inputEntry.
        NetworkTableEntry test = tab.get
        SimpleWidget inputType = tab.add("InputType", "NONE");
        inputType.
        tab.add("InputType", "NONE");*/
    }

    public void add(String title, Object Value){
        tab.add(title,Value);
    }
    public void add(String title, Object Value, int x, int y){
        tab.add(title,Value)
        .withPosition(x, y);
    }
    public void add(String title, Object Value, int x, int y, int width, int height){
        tab.add(title,Value)
        .withPosition(x, y)
        .withSize(width, height);
    }
}