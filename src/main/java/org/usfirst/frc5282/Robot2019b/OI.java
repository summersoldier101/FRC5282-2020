
package org.usfirst.frc5282.Robot2019b;

import org.usfirst.frc5282.Robot2019b.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.XboxController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This is our subclass to control Operator Input.  Joysticks etc...
 */

public class OI {
    public Joystick rightJoystick;
    public Joystick leftJoystick;
   
    public XboxController xbox;
     public JoystickButton buttonX1;
    public JoystickButton buttonX2;
    public JoystickButton buttonX3;
    public JoystickButton buttonX4;
    public JoystickButton buttonX5;
    public JoystickButton buttonX6;

   public XboxController xbox2;  
   public JoystickButton buttonXP1;


    public OI() {
        rightJoystick = new Joystick(2);
        leftJoystick = new Joystick(3);
       
        xbox = new XboxController(0);     
       
        buttonX1 = new JoystickButton(xbox, 1);   
        buttonX2 = new JoystickButton(xbox, 2);   
        buttonX3 = new JoystickButton(xbox, 3);   
        buttonX4 = new JoystickButton(xbox, 4);   
        buttonX5 = new JoystickButton(xbox, 5);   
        buttonX6 = new JoystickButton(xbox, 6);

        xbox2 = new XboxController(1);
        buttonXP1 = new JoystickButton(xbox2, 1);
        
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

        buttonX1.whenPressed(new MT());  
        //buttonX2.whenPressed(new MT());  
        //buttonX3.whenPressed(new MT());  
        //buttonX4.whenPressed(new MT());  
       buttonX6.whileHeld(new TargetAdjust());
       
       
       //2nd xbox controller buttons
        buttonXP1.whenPressed(new FireBall());
        
        
       
 
        
        // Smart Dashboard Command
       // SmartDashboard.putData("SensorsInfo", new SensorInfo());

       SmartDashboard.putData("TargetAdjust", new TargetAdjust());
       System.out.println("FireBall");
    




    }
    
  
    

    public XboxController getXbox() {
        return xbox;
    }

   public XboxController getxbox2(){
       return xbox2;
   }

}


