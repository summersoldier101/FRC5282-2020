/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5282.Robot2019b.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc5282.Robot2019b.Robot;

//imports for the speed controller Tallons -Jordan
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
  public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  // classify things ussing private, public, static

  private TalonSRX intake;
  private Spark slider;

  private static final double deadbandintake = 0.02;    


  public Intake() {
    //identify new things here Blank =  new Blank

    intake =  new TalonSRX(5);
    intake.setInverted(false);
    intake.configNeutralDeadband(deadbandintake);


    slider = new Spark(6);
    slider.setInverted(false);

}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand(new DriveWithXbox()); example for what will go here later

  }

public void BallIntake(){
ArmPower(.7);



}

public void Ballfire(){
  ArmPower(-.7);


}



/*
public void ArmPower(double P) {                    // Non Joystick control
        
  if (P>=1.0){            // Max input is 1.0 to -1.0.
      P=1.0;
      }
  else if (P<=-1.0){
      P=-1.0;
  }
  

  if (Math.abs(P)<=0.08){
      P=0.00;
  }
  

  slider.set(P);
}
*/



public void ArmPower(double P) {                    // Tankdrive
        
        
  intake.set(ControlMode.PercentOutput, P);
  

}
  }
