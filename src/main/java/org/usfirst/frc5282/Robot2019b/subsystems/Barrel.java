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
import org.usfirst.frc5282.Robot2019b.commands.Barrelon;

/**
 * Add your docs here.
 */
public class Barrel extends Subsystem {

  private TalonSRX Barrel;
  
 
  private boolean buttonXP1= true;
  private boolean buttonXP2= true;

  public Barrel(){
    Barrel = new TalonSRX(8);
    Barrel.setInverted(false);
    Barrel.configFactoryDefault();
    
  }

private void BarrelPower(double B){
  Barrel.set(ControlMode.PercentOutput, B, DemandType.ArbitraryFeedForward, 0);
}

private void BarrelUp(){
  BarrelPower(1);
}

private void BarrelDown(){
  BarrelPower(-1);
}

private void BarrelStop(){
  BarrelPower(0);
}

  public void BarrelOn(){
     buttonXP1 = Robot.oi.xbox2.getRawButton(1);
     buttonXP2 = Robot.oi.xbox2.getRawButton(2);

    if(buttonXP1){
      BarrelUp();
     
    } 
    else if(buttonXP1){
      //Do nothing
    }
    else if(buttonXP2){
       BarrelDown();
    }
    else if(buttonXP2){
        // do nothing
    }
     

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new Barrelon());
  }
}
