package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;


public class ShuffleboardControl{
    static ShuffleboardTab tab = Shuffleboard.getTab("Inf Recharge");
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();
    static NetworkTable table = inst.getTable("Smart Dashboard");
    //NetworkTableEntry inputEntry = table.getEntry("InputType");
    static NetworkTableEntry loadie = inst.getEntry("Loaded");



    public static void setup(){
        tab.add("InputType", Inputs.inputTypeList[Inputs.inputType]).withSize(2,1).withPosition(0, 0);
        tab.add("DriveType", Inputs.driveTypeList[Inputs.driveType]).withSize(2,1).withPosition(2, 0);
        loadie.setBoolean(false);
    /*inputEntry.
        NetworkTableEntry test = tab.get
        SimpleWidget inputType = tab.add("InputType", "NONE");
        inputType.
        tab.add("InputType", "NONE");*/
    }

    public static void add(String title, Object Value){
        tab.add(title,Value);
    }
    public static void add(String title, Object Value, int x, int y){
        tab.add(title,Value)
        .withPosition(x, y);
    }
    public static void add(String title, Object Value, int x, int y, int width, int height){
        tab.add(title,Value)
        .withPosition(x, y)
        .withSize(width, height);
    }

    public static void loaded(boolean yes){
        if(yes){
            loadie.setBoolean(true);
        }
        else{
            loadie.setBoolean(true);
        }
    }
}