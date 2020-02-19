package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.SubSys.DriveTrain;
import frc.robot.SubSys.Intake;

public class Inputs{
    public final int inputType = 0; //Gamepad F310, Attack 3, Extreme 3D
    public final String[] inputTypeList = {"Gamepad F310", "Attack 3", "Extreme 3D"};
    public final int driveType = 0; //Tank, Arcade
    public final String[] driveTypeList = {"Tank", "Arcade"};
    public Joystick joy,rightJoy,leftJoy;

    DriveTrain driveTrain = new DriveTrain();
    Intake intake = new Intake();

    
    public void joySetup(){
        if(driveType == 0){
            if(inputType > 0){
                rightJoy = new Joystick(RobotMap.RIGHT_JOYSTICK);
                leftJoy = new Joystick(RobotMap.LEFT_JOYSTICK);
            }
            else{
                joy = new Joystick(RobotMap.SOLO_JOYSTICK);
            }
        }
        else if(driveType == 1){
            joy = new Joystick(RobotMap.SOLO_JOYSTICK);
        }
    }
    public void inputPeriodic(){//Control Code
        if(inputType == 0){
            if(driveType == 0){
                if(joy.getRawButton(10)){
                    if(joy.getRawAxis(4) > 0.1)
                        driveTrain.slide(true);
                    else if(joy.getRawAxis(4) < 0.1)
                        driveTrain.slide(false);
                    //driveTrain.slide(joy.getRawAxis(4));
                }
                else{
                    driveTrain.setLeft(-joy.getRawAxis(1));
                    driveTrain.setRight(-joy.getRawAxis(5));
                }

                
                if(joy.getRawAxis(3) > 0){
                    intake.full();
                    intake.moveTrack();
                }
                else{
                    intake.none();
                }
            }
            else if(driveType == 1){

            }
        }
    }
}