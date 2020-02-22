package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.SubSys.DriveTrain;

public class Vision{
    DriveTrain driveTrain = new DriveTrain();
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("Shuffleboard");
    
    NetworkTableEntry xCellEntry;// = table.getEntry("X-Centers");
    Number[] def = {0};
    Number[] xs;// = xCellEntry.getNumberArray(def);//.getNumber(0).floatValue();

    NetworkTableEntry yCellEntry;// = table.getEntry("Y-Centers");
    //Number[] ys = yCellEntry.getNumberArray(def);

    float trackCellX = 10;
    float trackCellY = 10;

    double centerErr = 0.1;

    public void lineUpShoot(){ //TODO: CONVERT 
        xCellEntry = table.getEntry("X-Ports");
        xs = xCellEntry.getNumberArray(def);

        yCellEntry = table.getEntry("Y-Ports");
        //ys = yCellEntry.getNumberArray(def);

        trackCellX = table.getEntry("TrackedX").getNumber(10).floatValue();
        trackCellY = table.getEntry("TrackedY").getNumber(10).floatValue();

        if(trackCellX > centerErr){
            driveTrain.slide(true);
        }
        else if(trackCellX < centerErr){
            driveTrain.slide(false);
        }
        else{
            driveTrain.stop();
        }
    }

    public void followCell(){
        xCellEntry = table.getEntry("XCell-Centers");
        xs = xCellEntry.getNumberArray(def);

        yCellEntry = table.getEntry("YCell-Centers");
        //ys = yCellEntry.getNumberArray(def);

        trackCellX = table.getEntry("TrackedX").getNumber(10).floatValue();
        trackCellY = table.getEntry("TrackedY").getNumber(10).floatValue();

        if(trackCellX > centerErr){
            driveTrain.slide(true);
        }
        else if(trackCellX < centerErr){
            driveTrain.slide(false);
        }
        else{
            driveTrain.stop();
        }
    }

    public void trackUp(){
        int spot = 0;
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
        int spot = 0;
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