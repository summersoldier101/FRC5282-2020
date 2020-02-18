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

import org.usfirst.frc5282.Robot2019b.commands.WenchOn;

/**
 * Add your docs here.
 */
public class Wench extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX wench;

  private boolean buttonXP5= true;
  private boolean buttonXP6= true;

  public void Wench(){
    wench = new TalonSRX(8);
  } 

    public void WenchUp() {
      WenchPower(1);
    }
    
    private void WenchPower(int i) {
  }

  public void WenchDown() {
      WenchPower(-1);
    }
    
    private void WenchStop(){
      WenchPower(0);
    }
    
      public void WenchOn(){
         buttonXP5 = Robot.oi.xbox2.getRawButton(5);
         buttonXP6 = Robot.oi.xbox2.getRawButton(6);
    
        if(buttonXP5){
          WenchUp();
         
        } 
        else if(buttonXP5){
          //Do nothing
        }
        else if(buttonXP6){
           WenchDown();
        }
        else if(buttonXP6){
            // do nothing
        }    

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WenchOn());
  }


}
