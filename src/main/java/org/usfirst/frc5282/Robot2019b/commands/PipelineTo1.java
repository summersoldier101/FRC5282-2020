
package org.usfirst.frc5282.Robot2019b.commands;
import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc5282.Robot2019b.LimeLight;

/**
 *  
 */
public class PipelineTo1 extends InstantCommand {
  
  LimeLight lime;

  /**
   * Command changes limelight to pipeline 0
   */
  public PipelineTo1() {
    //requires();

  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    
   
    lime.setPipeline(1);
   
  }

}
