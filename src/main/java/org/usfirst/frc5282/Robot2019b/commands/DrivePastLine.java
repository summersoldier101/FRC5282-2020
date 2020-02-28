
package org.usfirst.frc5282.Robot2019b.commands;

import org.usfirst.frc5282.Robot2019b.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.InstantCommand;


/**
 * Autonomus comand
 */
public class DrivePastLine extends InstantCommand {

  private int state;

    public DrivePastLine() {
        requires(Robot.driveTrain);
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

   while(state<100);{
    System.out.println("STATE WORKING");
    
}
switch(state){
    case 0:
    startTime=System.currentTimeMillis();
    
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
