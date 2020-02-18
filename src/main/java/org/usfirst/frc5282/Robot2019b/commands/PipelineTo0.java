/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package org.usfirst.frc5282.Robot2019b.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc5282.Robot2019b.LimeLight;
import org.usfirst.frc5282.Robot2019b.Robot;

/**
 *  
 */
public class PipelineTo0 extends InstantCommand {
  
  LimeLight lime;

  /**
   * Command changes limelight to pipeline 0
   */
  public PipelineTo0() {
    

  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    
   
    lime.setPipeline(0);
   
  }

}
