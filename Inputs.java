package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.SubSys.DriveTrain;
import frc.robot.SubSys.Intake;
import frc.robot.SubSys.Lift;
import frc.robot.SubSys.Shooter;

public class Inputs{
    ublic static final int inputType = 0; //Gamepad F310, Dual Driver
    public static final String[] inputTypeList = {"Gamepad F310","Dual Driver"};
    public static final int driveType = 0; //Tank, Arcade
    public static final String[] driveTypeList = {"Tank", "Arcade"};
    private static Joystick driverJoy, aimJoy, joy;
    
    public static void joySetup(){
        if(inputType == 0){
            System.out.println("Using a single driver with a Gamepad F310");
            joy = new Joystick(0);
        }
        else if(inputType == 1){
            System.out.println("Using a driver joy(0) and control joy(1)");
            driverJoy = new Joystick(0);
            aimJoy = new Joystick(1);
        }
        else{
            System.out.println("ERROR: defaulting to single driver config");
            inputType = 0;
            joy = new Joystick(0);
        }
    }
    public static void inputPeriodic(){
        if(inputType == 1){
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

            if(joy.getRawAxis(2) > 0){
                Intake.store();
            }
            else if(joy.getRawButton(4) && joy.getRawButton(2) && joy.getRawButton(3)){
                //Intake.store();
                Intake.bothReverse();
            }
            else if(joy.getRawButton(2) && joy.getRawButton(3)){
                //Intake.store();
                Intake.spinIntakeReverse();
            }
            else if(joy.getRawButton(4) && joy.getRawButton(3)){
                Intake.moveTrackReverse();
            }
            else if(joy.getRawButton(4) && joy.getRawButton(2)){
                //Intake.store();
                Intake.both();
            }
            else if(joy.getRawButton(2)){
                //Intake.store();
                Intake.spinIntake();
            }
            else if(joy.getRawButton(4)){
                Intake.moveTrack();
            }
            else{
                Intake.none();
            }

            if(joy.getRawButton(1)){
                //Shooter.full();
                System.out.println("ALIGN");
                Shooter.altFull(); //Consider this if the balls hit the flywheels when they fall
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


            if(joy.getRawButtonPressed(7)){
                Vision.cellUp();
            }
            else if(joy.getRawButtonPressed(8)){
                Vision.cellDown();
            }
        }
        else{          
            if(driverJoy.getRawButton(9)){
                if(driverJoy.getRawAxis(4) > 0.1)
                    DriveTrain.slide(true);
                else if(driverJoy.getRawAxis(4) < 0.1)
                    DriveTrain.slide(false);
                //driveTrain.slide(joy.getRawAxis(4));
            }
            else{
                DriveTrain.setLeft(-driverJoy.getRawAxis(1));
                DriveTrain.setRight(-driverJoy.getRawAxis(5));
            }

            if(driverJoy.getRawAxis(3) > 0){
                //Intake.store();
                //Intake.moveTrack();
                Shooter.fire();
            }
            else{
                //Intake.none();
                Shooter.reset();
            }

            if(driverJoy.getRawButton(6)){
                Shooter.rev();
            }
            else{
                Shooter.none();
            }

            if(driverJoy.getRawAxis(2) > 0){
                Intake.store();
            }
            else if(driverJoy.getRawButton(4) && driverJoy.getRawButton(2) && driverJoy.getRawButton(3)){
                //Intake.store();
                Intake.bothReverse();
            }
            else if(driverJoy.getRawButton(2) && driverJoy.getRawButton(3)){
                //Intake.store();
                Intake.spinIntakeReverse();
            }
            else if(driverJoy.getRawButton(4) && driverJoy.getRawButton(3)){
                Intake.moveTrackReverse();
            }
            else if(driverJoy.getRawButton(4) && driverJoy.getRawButton(2)){
                //Intake.store();
                Intake.both();
            }
            else if(driverJoy.getRawButton(2)){
                //Intake.store();
                Intake.spinIntake();
            }
            else if(driverJoy.getRawButton(4)){
                Intake.moveTrack();
            }
            else{
                Intake.none();
            }

            if(driverJoy.getRawButton(1)){
                //Shooter.full();
                System.out.println("ALIGN");
                Shooter.altFull(); //Consider this if the balls hit the flywheels when they fall
            }

            if(driverJoy.getPOV() == 0 && driverJoy.getRawButton(5)){
                Lift.climb();
            }
            else if(driverJoy.getPOV() == 180 && driverJoy.getRawButton(5)){
                Lift.release();
            }
            else if(driverJoy.getPOV() == 0){
                Lift.raise();
            }
            else if(driverJoy.getPOV() == 180){
                Lift.lower();
            }
            else{
                Lift.none();
            }


            if(aimJoy.getRawButtonPressed(3)){
                Vision.cellUp();
            }
            else if(aimJoy.getRawButtonPressed(2)){
                Vision.cellDown();
            }
        }
        
    
    
    
    
    
    
    
    
    
    
/*
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

                if(joy.getRawAxis(2) > 0){
                    Intake.store();
                }
                else if(joy.getRawButton(4) && joy.getRawButton(2) && joy.getRawButton(3)){
                    //Intake.store();
                    Intake.bothReverse();
                }
                else if(joy.getRawButton(2) && joy.getRawButton(3)){
                    //Intake.store();
                    Intake.spinIntakeReverse();
                }
                else if(joy.getRawButton(4) && joy.getRawButton(3)){
                    Intake.moveTrackReverse();
                }
                else if(joy.getRawButton(4) && joy.getRawButton(2)){
                    //Intake.store();
                    Intake.both();
                }
                else if(joy.getRawButton(2)){
                    //Intake.store();
                    Intake.spinIntake();
                }
                else if(joy.getRawButton(4)){
                    Intake.moveTrack();
                }
                else{
                    Intake.none();
                }

                if(joy.getRawButton(1)){
                    //Shooter.full();
                    System.out.println("ALIGN");
                    Shooter.altFull(); //Consider this if the balls hit the flywheels when they fall
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
                
                
                if(joy.getRawButtonPressed(7)){
                    Vision.cellUp();
                }
                else if(joy.getRawButtonPressed(8)){
                    Vision.cellDown();
                }
                
                
            }
            else if(driveType == 1){

            }
        }
    }
*/
}
