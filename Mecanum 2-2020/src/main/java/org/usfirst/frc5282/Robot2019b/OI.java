
package org.usfirst.frc5282.Robot2019b;

import org.usfirst.frc5282.Robot2019b.commands.*;


import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is our subclass to control Operator Input.  Joysticks etc...
 */

public class OI {
       
   
    public XboxController xbox;  
    public JoystickButton buttonX1;
   
   

    public OI() {
 
       
        xbox = new XboxController(2);     
        buttonX1 = new JoystickButton(xbox, 1);   
        
        //=======================================================================================================
        //// TRIGGERING COMMANDS WITH BUTTONS
        
        // When pressed
        // button.whenPressed(new ExampleCommand());
        // Start the command when the button is pressed and let it run the command
        // until it is finished as determined by it's isFinished method.

        // While held
        // button.whileHeld(new ExampleCommand());
        // Run the command while the button is being held down and interrupt it once
        // the button is released.

        // When Released
        // button.whenReleased(new ExampleCommand());
        // Start the command when the button is released  and let it run the command
        // until it is finished as determined by it's isFinished method
        
        /** Primary xbox Joystick Buttons ____________________________________________________________*/

        buttonX1.whenPressed(new MT());  // A
       
       
        
        
        
       
 
        
        // Smart Dashboard Command
       // SmartDashboard.putData("SensorsInfo", new SensorInfo());
        
        
      




    }
    
  
    

    public XboxController getXbox() {
        return xbox;
    }

   

}


