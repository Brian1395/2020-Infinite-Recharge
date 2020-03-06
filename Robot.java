/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.SubSys.DriveTrain;
import frc.robot.SubSys.Intake;
import frc.robot.SubSys.Shooter;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String LeftSpot = "LeftSpot";
  private static final String CenterSpot = "CenterSpot";
  private static final String RightSpot = "RightSpot";
  private static final String Blind = "Blind";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //DriveTrain driveTrain = new DriveTrain();
  //Inputs input = new Inputs();
  
  private int AUTO_STAGE = -1;
  private float AUTO_DETATCH_TIME = 1;
  private int fowardDist = 3;//THESE ARE THE DISTANCES FOR AUTO IN FEET
  private int sideDist = 2;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Center Starting Position", CenterSpot);
    m_chooser.addOption("Left Starting Position", LeftSpot);
    m_chooser.addOption("Right Starting Position", RightSpot);
    m_chooser.addOption("Blind Shooting", Blind);
    SmartDashboard.putData("Auto choices", m_chooser);
    //shuffleboard.add("Auto choices", m_chooser,4,0,2,1);
    
    //shuffleboard.setup();
    LiveWindow.setEnabled(false);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
    DriveTrain.setup();
    AUTO_STAGE = -1; //SHOULD BE -1
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    //Intake.releaseIntake();
    switch (m_autoSelected) {
      case Blind:
        if(AUTO_STAGE == -1){
          Intake.startTimer();
          Intake.spinIntake();
          if(Intake.hasPassedSec(AUTO_DETATCH_TIME)){
            Intake.spinIntake(0);
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 0){
          if(DriveTrain.moveFeetAuto(fowardDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else{
          Shooter.semi();
        }
        break;
      case LeftSpot:
        if(AUTO_STAGE == -1){
          Intake.startTimer();
          Intake.spinIntake();
          if(Intake.hasPassedSec(AUTO_DETATCH_TIME)){
            Intake.spinIntake(0);
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 0){
          if(DriveTrain.moveFeetAuto(fowardDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 1){
          if(DriveTrain.slideFeetAuto(sideDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else{
          Shooter.altFull();
        }
        break;
      case CenterSpot:
      default:
        if(AUTO_STAGE == -1){
          Intake.startTimer();
          Intake.spinIntake();
          if(Intake.hasPassedSec(AUTO_DETATCH_TIME)){
            Intake.spinIntake(0);
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 0){
          if(DriveTrain.moveFeetAuto(fowardDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else{
          //DriveTrain.setBoth(0);
          Shooter.altFull();
        }
        break;
      case RightSpot:
        if(AUTO_STAGE == -1){
          Intake.startTimer();
          Intake.spinIntake();
          if(Intake.hasPassedSec(AUTO_DETATCH_TIME)){
            Intake.spinIntake(0);
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 0){
          if(DriveTrain.moveFeetAuto(fowardDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else if(AUTO_STAGE == 1){
          if(DriveTrain.slideFeetAuto(-sideDist)){
            DriveTrain.setup();
            AUTO_STAGE = AUTO_STAGE + 1;
          }
        }
        else{
          //Shooter.full();
          Shooter.altFull();
        }
        break;
    }
    //System.out.println(AUTO_STAGE);
  }

  @Override
  public void teleopInit() {
    Inputs.joySetup();
  }

  @Override
  public void teleopPeriodic() {
    //driveTrain.slide(true);
    Inputs.inputPeriodic();
    //Shooter.periodicRun();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    System.out.println("Chamber:");
    System.out.println(Shooter.isLoaded());
    System.out.println("Intake:");
    System.out.println(!Intake.lim.get());
  }


  //Custom Functs
  
}
