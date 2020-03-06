
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
    public JoystickButton buttonX7;
    public JoystickButton buttonX8;
    public JoystickButton buttonX9;
    public JoystickButton buttonX10;
    public Trigger lTrigger;


   public XboxController xbox2; 
   public JoystickButton buttonXP1;
   public JoystickButton buttonXP2;

   public Joystick buttonboard;
   public JoystickButton WhiteB1;
   public JoystickButton WhiteB2;
   public JoystickButton WhiteB3;
   public JoystickButton RedB;
   public JoystickButton YellowB;
   public JoystickButton GreenB;
   public JoystickButton BlueB;

    public OI() {
       
       
        xbox = new XboxController(0);     
       
        buttonX1 = new JoystickButton(xbox, 1);   
        buttonX2 = new JoystickButton(xbox, 2);   
        buttonX3 = new JoystickButton(xbox, 3);   
        buttonX4 = new JoystickButton(xbox, 4);   
        buttonX5 = new JoystickButton(xbox, 5);   
        buttonX6 = new JoystickButton(xbox, 6);
        buttonX7 = new JoystickButton(xbox, 7);
        buttonX8 = new JoystickButton(xbox, 8);
        buttonX9 = new JoystickButton(xbox, 9);
        buttonX10 = new JoystickButton(xbox, 10);
        
      

        xbox2 = new XboxController(1);
        buttonXP1 = new JoystickButton(xbox2, 1);



        buttonboard = new Joystick(3);
        WhiteB1 = new JoystickButton(buttonboard, 1);
        WhiteB2 = new JoystickButton(buttonboard, 2);
        WhiteB3 = new JoystickButton(buttonboard, 3);
        RedB = new JoystickButton(buttonboard, 4);
        YellowB = new JoystickButton(buttonboard, 5);
        GreenB = new JoystickButton(buttonboard, 6);
        BlueB = new JoystickButton(buttonboard, 7);
        
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
        // button.whenReleased(new ExampleCommand(speen));
        // Start the command when the button is released  and let it run the command
        // until it is finished as determined by it's isFinished method
        
        /** Primary xbox Joystick Buttons ____________________________________________________________*/

       // buttonX1.whenPressed(new MT());  
        //buttonX2.whenPressed(new MT());  
        //buttonX3.whenPressed(new MT());  
        //buttonX4.whenPressed(new MT());  
       buttonX6.whileHeld(new TargetAdjust());
      // buttonX7.whenPressed(new PipelineTo0());
      // buttonX8.whenPressed(new PipelineTo1());
      // buttonX5.whileHeld(new DrivePastLine());
       //2nd xbox controller buttons
       // buttonXP1.whenPressed(new FireBall());
        
        
       //Button board buttons
       // buttonX3.whenPressed(new TubeExtend());
       // buttonX4.whenPressed(new TubeRetract());
        
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


