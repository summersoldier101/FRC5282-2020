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

import org.usfirst.frc5282.Robot2019b.commands.WinchOn;

/**
 * Add your docs here.
 */
public class Winch extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX Winch;

  private boolean WhiteB1 = false;
  private boolean WhiteB2= false;
  private boolean WhiteB3= false;

  public void Winch(){
    Winch = new TalonSRX(8);
  } 

    public void WinchUp() {
      WinchPower(.5);
    }
    
    private void WinchPower(double w) {
     //Winch.set(ControlMode.PercentOutput, w, DemandType.ArbitraryFeedForward, 0);
  }

  public void WinchDown() {
    WinchPower(-.5);
    }
    
    private void WinchStop(){
      WinchPower(0);
    }
    
      public void WinchOn(){
         WhiteB1 = Robot.oi.buttonboard.getRawButton(1);
         WhiteB2 = Robot.oi.buttonboard.getRawButton(2);
         WhiteB3 = Robot.oi.buttonboard.getRawButton(3);
        
         if((WhiteB1&&WhiteB2)||(!WhiteB1&&!WhiteB2)){
          WinchStop();
         }
         else
         {
          if(WhiteB1){
            WinchUp();
          }
          if(WhiteB2){
            WinchDown();
          }
         }
        }    

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new WinchOn());
  }


}
