/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc5282.Robot2019b.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc5282.Robot2019b.Robot;
import org.usfirst.frc5282.Robot2019b.LimeLight;
 
import edu.wpi.first.networktables.NetworkTable;            //!! Limelight
import edu.wpi.first.networktables.NetworkTableEntry;       //!! Limelight
import edu.wpi.first.networktables.NetworkTableInstance;    //!! Limelight

 

public class TargetAdjust extends Command {

    LimeLight lime;
     
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    //private double m_PipeLine;

     // ******************************************************
    // This code is currently designed so that the user can overpower the assist.
    // Pressing the button guides the user, but if they apply full power in one direction it will still go that way.
    // This can help when aquiring the wrong target.
    // The amount of over powering automated control is dictated by the AimingMultiplier 
    // Note that the motors max out at 1.o so above the Multipler amount 
    // (if multiplier of 0.3, then above 0.7 power on joystick) has reduced effect, since the motors can not be set to 1.3 and 0.7.  They get 1.0 and 0.7;
    // aka. Slow down a little to aim.

    //Driving Assist
    //private double TargetSize=2.3;              // Not used...

    private double AimingMultiplier=0.3;        // Power of steering assist multiplier.  Higher the more assist.
    private double AimingMinAngle=3.0;          // Do not adjust below this angle in degrees.  Increase this to stop oscilation.  Decrease to improve accuracy.
    private double DriftMinAngle=0;
    //Just Amining Assist
    private double AimThresholdPower=.2;       // Below this joystick input just aim. 2  If you are virtually stopped, just aim at the target.
    private double AimingPower=0.3;             // Just aim with this power   5           Power to just aim if you are virtually stoppped.
    
    //Variables for inputs
    private double jLeft = 0.0;              //Joystick left input
    private double jRight = 0.0;             // Joystick right input
    private double jAverage = 0.0;           // average of the joystick input



   
    public TargetAdjust() {
        requires(Robot.driveTrain);
        //m_PipeLine = PipeLine;
    }

    // Called just before this Command runs the first time

    @Override
    protected void initialize() {
 
    }


    // Called repeatedly when this Command is scheduled to run


    @Override
    protected void execute() {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tl = table.getEntry("tv");
        
        
        /* This was for the LimeLight Class
        lime.update(); 
        double Tx = lime.getTx();
        boolean Tv = lime.hasTarget();
        */


        double Tx = tx.getDouble(-1);
        double Ty = ty.getDouble(-1);
        double Tl = tl.getDouble(-1);
        double Tarea = ta.getDouble(0.0);
        System.out.println("Limelight Data Tx "+Tx+" Ty "+Ty);


        jLeft = -Robot.oi.xbox.getY();     // Get joystick input
        jRight = -Robot.oi.xbox2.getX();                        
        jAverage = (jLeft+jRight)/2;      

        System.out.println("Target Aim Data:  x"+Tx+" y"+Ty+" l"+Tl+" a"+Tarea);
        System.out.println("                 jleft"+jLeft+" jRight"+jRight+"jAverage"+jAverage);
       
        
        //Data ouput
        //System.out.println("____________________________________________________________");
        //System.out.println("TargetAimHeld command");
        //System.out.println("Aim Data: cv"+cv+" cx"+cx+" cy"+cy+" ca"+ca+" cs"+cs);
        //System.out.println("PipeLine: "+lime.getPipeline());
        
        //System.out.println("    Gyro:"+Robot.driveTrain.getGyroAngle());
        
              
        
        if (Tl==1){                                  // Is there a target?        
            
            if (jAverage>=AimThresholdPower){                               // Driver Forwards and Aim
                if(Tx > AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.5+AimingMultiplier), jRight*(1-AimingMultiplier));            // Adjust Right    used to be 1
                }
                else if (Tx < -AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.5-AimingMultiplier), jRight*(1+AimingMultiplier));            // Adjust Left
                }
                else {
                    Robot.driveTrain.ApplyMotorPower(jLeft, jRight);            // Below angle adjustment. Adjust none. 
                }
            }
            else if (jAverage<=-AimThresholdPower){                         // Drive backwards and Aim
                if(Tx > AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.5-AimingMultiplier), jRight*(1+AimingMultiplier));            // Adjust Right
                }
                else if (Tx < AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.5+AimingMultiplier), jRight*(1-AimingMultiplier));            // Adjust Left   
                }
                else {
                    Robot.driveTrain.ApplyMotorPower(jLeft, jRight);            // Below minimum needed angle adjustment. Don't adjust
                }
            } else {                                                             // Just aim, NO driving
                if(Tx > AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(AimingPower, -AimingPower);            // Adjust Right
                }
                else if (Tx < -AimingMinAngle){
                    Robot.driveTrain.ApplyMotorPower(-AimingPower, AimingPower);            // Adjust Left
                }
                else {Robot.driveTrain.ApplyMotorPower(jLeft,jRight);}            // Not Driving, Just Aim
            }
        }
        else{
            Robot.driveTrain.ApplyMotorPower(jLeft, jRight);        // No target, just drive
        }
    }









/*


        if (Tl==1){                                  // Is there a target?        
            
            if (jAverage>=AimThresholdPower){                               // Driver Forwards and Aim
                if(Ty > DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.05+AimingMultiplier), jRight*(.05+AimingMultiplier));            // Adjust Right    used to be 1
                }
                else if (Ty < -DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.05-AimingMultiplier), jRight*(.05-AimingMultiplier));            // Adjust Left
                }
                else {
                    Robot.driveTrain.ApplyMotorPower(jLeft, jRight);            // Below angle adjustment. Adjust none. 
                }
            }
            else if (jAverage<=-AimThresholdPower){                         // Drive backwards and Aim
                if(Ty > DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.05+AimingMultiplier), jRight*(.05+AimingMultiplier));            // Adjust Right
                }
                else if (Ty < DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(jLeft*(.05-AimingMultiplier), jRight*(.05-AimingMultiplier));            // Adjust Left   
                }
                else {
                    Robot.driveTrain.ApplyMotorPower(jLeft, jRight);            // Below minimum needed angle adjustment. Don't adjust
                }
            } else {                                                             // Just aim, NO driving
                if(Ty > DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(AimingPower, AimingPower);            // Adjust Right
                }
                else if (Ty < -DriftMinAngle){
                    Robot.driveTrain.ApplyMotorPower(-AimingPower, -AimingPower);            // Adjust Left
                }
                else {Robot.driveTrain.ApplyMotorPower(jLeft,jRight);}            // Not Driving, Just Aim
            }
        }
        else{
            Robot.driveTrain.ApplyMotorPower(jLeft, jRight);        // No target, just drive
        }
    }
*/
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.ApplyMotorPower(0.0, 0.0);  // Should change this to DriveWithJoystick, but currently works.  Probably causes a short delay.
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
