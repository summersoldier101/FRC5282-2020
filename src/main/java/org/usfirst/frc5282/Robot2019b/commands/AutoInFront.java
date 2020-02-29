
package org.usfirst.frc5282.Robot2019b.commands;

import org.usfirst.frc5282.Robot2019b.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;


/**
 * Autonomus comand
 */
public class AutoInFront extends InstantCommand {

  

    public AutoInFront() {
        requires(Robot.driveTrain);
       // requires(Robot.rotator);
       // requires(Robot.fire);
    }
 // Called just before this Command runs the first time
 @Override
 protected void initialize() {
 }

 // Called repeatedly when this Command is scheduled to run
 @Override
 protected void execute() {
   //Robot.driveTrain.PastLine();
   double percent;
   long startTime=0;
 
  
   //while(state<100);{
    // System.out.println("STATE WORKING");
    startTime=System.currentTimeMillis();
    //if (System.currentTimeMillis()>startTime+3){
      System.out.println("State 1 timed out.");
      Robot.driveTrain.ApplyMotorPower(-.26, -.25);
      Timer.delay(2);
      Robot.driveTrain.ApplyMotorPower(0, 0);
      Timer.delay(.5);
      Robot.driveTrain.TargetAdjustAuto();
      Timer.delay(1);
      Robot.rotator.RotateAuto();
      Timer.delay(.5);
      Robot.rotator.RotateAuto();
      Robot.barrel.BarrelOnAuto();
      Robot.fire.Ballfire();
      Timer.delay(6);
      Robot.driveTrain.ApplyMotorPower(0, 0);
      Timer.delay(3);

     
    //}
      
      
        // run for 4 seconds
/*
switch(state){
    case 0:
    s
    
    state = 1;
    break;

    case 1:
    if (System.currentTimeMillis()>startTime+10500){
      System.out.println("State 1 timed out.");
      state=2;
    }
    state = 2;
    break;

    case 2:
    Robot.driveTrain.ApplyMotorPower(-.5, -.5);
    Timer.delay(4.0);    // run for 4 seconds
    state = 3;
    break;

    case 3:
    state = 100;
    break;

    case 100:

    break;
  }
}
*/
 }

 // Make this return true when this Command no longer needs to run execute()
 @Override
 protected boolean isFinished() {
   return false;
 }

 // Called once after isFinished returns true
 @Override
 protected void end() {
 }

 // Called when another command which requires one or more of the same
 // subsystems is scheduled to run
 @Override
 protected void interrupted() {
 }
}
