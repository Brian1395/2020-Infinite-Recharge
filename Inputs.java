package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.SubSys.DriveTrain;
import frc.robot.SubSys.Intake;
import frc.robot.SubSys.Lift;
import frc.robot.SubSys.Shooter;

public class Inputs{
    public static final int inputType = 0; //Gamepad F310, Attack 3, Extreme 3D
    public static final String[] inputTypeList = {"Gamepad F310", "Attack 3", "Extreme 3D"};
    public static final int driveType = 0; //Tank, Arcade
    public static final String[] driveTypeList = {"Tank", "Arcade"};
    private static Joystick joy,rightJoy,leftJoy;

    //DriveTrain driveTrain = new DriveTrain();
    //Intake intake = new Intake();

    
    public static void joySetup(){
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
    public static void inputPeriodic(){//Control Code
        if(inputType == 0){
            if(driveType == 0){
                if(joy.getRawButton(9)){
                    if(joy.getRawAxis(4) > 0.1)
                        DriveTrain.slide(true);
                    else if(joy.getRawAxis(4) < 0.1)
                        DriveTrain.slide(false);
                    //driveTrain.slide(joy.getRawAxis(4));
                }
                else{
                    DriveTrain.setLeft(-joy.getRawAxis(1));
                    DriveTrain.setRight(-joy.getRawAxis(5));
                }

                
                if(joy.getRawAxis(3) > 0){
                    //Intake.store();
                    //Intake.moveTrack();
                    Shooter.fire();
                }
                else{
                    //Intake.none();
                    Shooter.reset();
                }

                if(joy.getRawButton(6)){
                    Shooter.rev();
                }
                else{
                    Shooter.none();
                }

                if(joy.getRawButton(4) && joy.getRawButton(1)){
                    //Intake.store();
                    Intake.both();
                }
                else if(joy.getRawButton(1)){
                    //Intake.store();
                    Intake.spinIntake();
                }
                else if(joy.getRawButton(4)){
                    Intake.moveTrack();
                }
                else{
                    Intake.none();
                }

                if(joy.getRawAxis(2) > 0){
                    Shooter.full();
                }
                
                if(joy.getPOV() == 0 && joy.getRawButton(5)){
                    Lift.climb();
                }
                else if(joy.getPOV() == 180 && joy.getRawButton(5)){
                    Lift.release();
                }
                else if(joy.getPOV() == 0){
                    Lift.raise();
                }
                else if(joy.getPOV() == 180){
                    Lift.lower();
                }
                else{
                    Lift.none();
                }
                
            }
            else if(driveType == 1){

            }
        }
    }
}