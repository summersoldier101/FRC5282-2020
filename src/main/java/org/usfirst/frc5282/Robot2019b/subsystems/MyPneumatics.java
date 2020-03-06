/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5282.Robot2019b.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import org.usfirst.frc5282.Robot2019b.Robot;
import org.usfirst.frc5282.Robot2019b.commands.TubeExtend;

import edu.wpi.first.wpilibj.Solenoid;


/**
 * Add your docs here.
 */
public class MyPneumatics extends Subsystem {
   //Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Solenoid tube;
  boolean buttonX3 = false;
  boolean buttonX4 = false;

  public MyPneumatics(){
    tube = new Solenoid(1);
    addChild("Tube", tube);
  

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
   setDefaultCommand(new TubeExtend());
  }



  @Override
  public void periodic(){


  }

  public void tubeExtend(){
    //System.out.println(tube.get());
    buttonX3 = Robot.oi.xbox.getRawButton(3);
    buttonX4 = Robot.oi.xbox.getRawButton(4);
    if(buttonX3){ 
    tube.set(true);
    System.out.println(tube.get());
    }
   if(buttonX4){
   // System.out.println("B4 %Working");
    tube.set(false);
   }
  
  /*
  public void tubeToggle(){
    boolean extended=tube.get();

    if(extended){
      tube.set(true);
    }
    else{
      tube.set(false);
    }
  }
  */
}
}
