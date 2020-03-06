package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.SubSys.DriveTrain;

public class Vision{
    static NetworkTableInstance inst = NetworkTableInstance.getDefault();
    static NetworkTable table = inst.getTable("Shuffleboard");
    
    static NetworkTableEntry xPort;// = table.getEntry("X-Centers");
    static NetworkTableEntry yPort;

    static NetworkTableEntry xCellEntry;
    static Number[] def = {0};
    static Number[] xs;// = xCellEntry.getNumberArray(def);//.getNumber(0).floatValue();

    static NetworkTableEntry yCellEntry;// = table.getEntry("Y-Centers");
    //Number[] ys = yCellEntry.getNumberArray(def);

    static float trackCellX = 10;
    static float trackCellY = 10;  

    static double centerErr = 0.1;
    static double centerErrPort = 0.1;
    static double centerErrCell = 0.15;
    
    static double visonSideSpeed = .6;

    static int spot = 0;

    public static boolean lineUpShoot(){  
        xPort = table.getEntry("X-Port");
        double PortCenterX = xPort.getDouble(0);

        if(PortCenterX == 10){
          SmartDashboard.putBoolean("PortFound", false);
          SmartDashboard.putBoolean("Aligned", false);
          return false;
        }
        SmartDashboard.putBoolean("PortFound", true);

        yPort = table.getEntry("Y-Port");
        //ys = yCellEntry.getNumberArray(def);

        
        if(PortCenterX > centerErrPort * 2){
            DriveTrain.slide(-visonSideSpeed);
        }
        else if(PortCenterX > centerErrPort){
          DriveTrain.slide(-visonSideSpeed/1.5);
        }
        else if(PortCenterX < -centerErrPort * 2){
            DriveTrain.slide(visonSideSpeed);
        }
        else if(PortCenterX < -centerErrPort){
          DriveTrain.slide(visonSideSpeed/1.5);
        }
        else{
            DriveTrain.stop();
            SmartDashboard.putBoolean("Aligned", true);
            return true;
        }
        SmartDashboard.putBoolean("Aligned", false);
        return false;
    }

    static public void followCell(){
        trackCellX = table.getEntry("TrackedX").getNumber(10).floatValue();
        trackCellY = table.getEntry("TrackedY").getNumber(10).floatValue();

        if(trackCellX > centerErrCell){
            DriveTrain.slide(true);
        }
        else if(trackCellX < centerErrCell){
            DriveTrain.slide(false);
        }
        else{
            DriveTrain.setBoth(0.5);
        }
    }

    public static void cellUp(){
        xCellEntry = table.getEntry("XCell-Centers");
        xs = xCellEntry.getNumberArray(def);

        yCellEntry = table.getEntry("YCell-Centers");
        //ys = yCellEntry.getNumberArray(def);

        spot = 0;
        for(int i = 0; i < xs.length; i++){
        if(xs[i].floatValue() == trackCellX){
          spot = i;
        }
        } 
        if(spot + 1 >= xs.length){
        spot = -1;
        }
        table.getEntry("TrackedX").setValue(xs[spot+1]);
    }

    public static void cellDown(){
        xCellEntry = table.getEntry("XCell-Centers");
        xs = xCellEntry.getNumberArray(def);

        yCellEntry = table.getEntry("YCell-Centers");
        //ys = yCellEntry.getNumberArray(def);

        spot = 0;
        for(int i = 0; i < xs.length; i++){
        if(xs[i].floatValue() == trackCellX){
          spot = i;
        }
        } 
        if(spot - 1 < 0){
        spot = xs.length;
        }
        table.getEntry("TrackedX").setValue(xs[spot-1]);
    }


    public void trackUp(){ 
      xCellEntry = table.getEntry("XCell-Centers");
      xs = xCellEntry.getNumberArray(def);

      yCellEntry = table.getEntry("YCell-Centers");
      //ys = yCellEntry.getNumberArray(def);

      spot = 0;
      for(int i = 0; i < xs.length; i++){
        if(xs[i].floatValue() == trackCellX){
          spot = i;
        }
      } 
      if(spot + 1 >= xs.length){
        spot = -1;
      }
      table.getEntry("TrackedX").setValue(xs[spot+1]);
    }

    public void trackDown(){
      xCellEntry = table.getEntry("XCell-Centers");
      xs = xCellEntry.getNumberArray(def);

      yCellEntry = table.getEntry("YCell-Centers");
      //ys = yCellEntry.getNumberArray(def);

      spot = 0;
      for(int i = 0; i < xs.length; i++){
        if(xs[i].floatValue() == trackCellX){
          spot = i;
        }
      } 
      if(spot - 1 < 0){
        spot = xs.length;
      }
      table.getEntry("TrackedX").setValue(xs[spot-1]);
    }
}
