
package org.usfirst.frc5282.Robot2019b.subsystems;

import org.usfirst.frc5282.Robot2019b.Constants;
import org.usfirst.frc5282.Robot2019b.LimeLight;
import org.usfirst.frc5282.Robot2019b.Robot;
import org.usfirst.frc5282.Robot2019b.commands.*;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

 
import edu.wpi.first.networktables.NetworkTable;            //!! Limelight
import edu.wpi.first.networktables.NetworkTableEntry;       //!! Limelight
import edu.wpi.first.networktables.NetworkTableInstance;    //!! Limelight
import edu.wpi.first.wpilibj.DriverStation;

import java.sql.Driver;

//import org.usfirst.frc5282.Robot2019b.Gains;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

//import com.kauailabs.navx.frc.AHRS; // NavX
import edu.wpi.first.wpilibj.SPI;   // NavX


/**
 *
 */

public class DriveTrain extends Subsystem {

    //public AHRS ahrs;                               // NavX
    static final double kToleranceDegrees = 2.0f;   //NavX
    //private AnalogGyro analogGyro1; 
    //public AnalogGyro Gyro1;
LimeLight lime;
    private TalonSRX DriveLF;   //Speed controlers 
    private TalonSRX DriveLB;  
    private TalonSRX DriveRF;
    private TalonSRX DriveRB;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    //=======================================================================================================
    // Genernal Constants
    private static final int kdTimeoutMs = 30;                      //set to zero to skip error checking
    private static final double kdDeadBand = .25;                      //dead band for motor power COSTING!!!!!!
  
  private double AimingMultiplier=0.3;        // Power of steering assist multiplier.  Higher the more assist.
    private double AimingMinAngle=3.0;          // Do not adjust below this angle in degrees.  Increase this to stop oscilation.  Decrease to improve accuracy.
    private double DriftMinAngle=0;
    //Just Amining Assist
    private double AimThresholdPower=.2;       // Below this joystick input just aim. 2  If you are virtually stopped, just aim at the target.
    private double AimingPower=0.5;             // Just aim with this power   5           Power to just aim if you are virtually stoppped.
    
    //Variables for inputs
    private double jLeft = 0.0;              //Joystick left input
    private double jRight = 0.0;             // Joystick right input
    private double jAverage = 0.0;           

    //=======================================================================================================

   

    public DriveTrain() {
       
     
    

        //Gyro1 = new AnalogGyro(1);
        //Gyro1.setSensitivity(0.007);
        //Gyro1.reset();
        //Gyro1.calibrate();
/*
        try {       // NavX
            ahrs = new AHRS(SPI.Port.kMXP); 
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
        ahrs.reset();  // Resets navx
       */

        //________ Left Front Motor

        DriveLF = new TalonSRX(1);                        // CAN ID
        DriveLF.configFactoryDefault();                   // rest to default
        DriveLF.setInverted(false);                       // Motor Direction with respect to green LED
        DriveLF.setSensorPhase(true);                    // Sensor diretion with respect to green LED
        DriveLF.configNeutralDeadband(kdDeadBand);
        DriveLF.setNeutralMode(NeutralMode.Coast);

        DriveLF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kdTimeoutMs);  //Just setting type of sensor, PID loop id 0, and the timeout
        DriveLF.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kdTimeoutMs);        // Setting PID can utilization at 10ms.
                
        DriveLF.configNominalOutputForward(0, kdTimeoutMs); // Set the peak and nominal outputs
        DriveLF.configNominalOutputReverse(0, kdTimeoutMs);
        DriveLF.configPeakOutputForward(1, kdTimeoutMs);
        DriveLF.configPeakOutputReverse(-1, kdTimeoutMs);
        
        //________ Left Rear Motor

        DriveLB = new TalonSRX(4);                        // CAN ID
        DriveLB.configFactoryDefault();                   // rest to default
        DriveLB.setInverted(false);                       // Motor Direction with respect to green LED
        DriveLB.setSensorPhase(true);                    // Sensor diretion with respect to green LED
        DriveLB.configNeutralDeadband(kdDeadBand);
        DriveLB.setNeutralMode(NeutralMode.Coast);

        DriveLB.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kdTimeoutMs);  //Just setting type of sensor, PID loop id 0, and the timeout
        DriveLB.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kdTimeoutMs);        // Setting PID can utilization at 10ms.
                
        DriveLB.configNominalOutputForward(0, kdTimeoutMs); // Set the peak and nominal outputs
        DriveLB.configNominalOutputReverse(0, kdTimeoutMs);
        DriveLB.configPeakOutputForward(1, kdTimeoutMs);
        DriveLB.configPeakOutputReverse(-1, kdTimeoutMs);
        
        //________ Right Front Motor

        DriveRF = new TalonSRX(2);                        // CAN ID
        DriveRF.configFactoryDefault();                   // rest to default
        DriveRF.setInverted(true);                       // Motor Direction with respect to green LED
        DriveRF.setSensorPhase(true);                    // Sensor diretion with respect to green LED
        DriveRF.configNeutralDeadband(kdDeadBand);
        DriveRF.setNeutralMode(NeutralMode.Coast);

        DriveRF.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kdTimeoutMs);  //Just setting type of sensor, PID loop id 0, and the timeout
        DriveRF.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kdTimeoutMs);        // Setting PID can utilization at 10ms.
                
        DriveRF.configNominalOutputForward(0, kdTimeoutMs); // Set the peak and nominal outputs
        DriveRF.configNominalOutputReverse(0, kdTimeoutMs);
        DriveRF.configPeakOutputForward(1, kdTimeoutMs);
        DriveRF.configPeakOutputReverse(-1, kdTimeoutMs);
        

       //________ Right Rear Motor

       DriveRB = new TalonSRX(3);                        // CAN ID
       DriveRB.configFactoryDefault();                   // rest to default
       DriveRB.setInverted(true);                       // Motor Direction with respect to green LED
       DriveRB.setSensorPhase(true);                    // Sensor diretion with respect to green LED
       DriveRB.configNeutralDeadband(kdDeadBand);
       DriveRB.setNeutralMode(NeutralMode.Coast);

       DriveRB.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kdTimeoutMs);  //Just setting type of sensor, PID loop id 0, and the timeout
       DriveRB.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kdTimeoutMs);        // Setting PID can utilization at 10ms.
               
       DriveRB.configNominalOutputForward(0, kdTimeoutMs); // Set the peak and nominal outputs
       DriveRB.configNominalOutputReverse(0, kdTimeoutMs);
       DriveRB.configPeakOutputForward(1, kdTimeoutMs);
       DriveRB.configPeakOutputReverse(-1, kdTimeoutMs);
        
        //________ zeros encoder sensors when robot is turned on
        
        SensorZero();
        
    }

    
    @Override
    public void initDefaultCommand() {
        
        // setDefaultCommand(new DriveWithJoy()); // rest to joystick after auton commands
        setDefaultCommand(new DriveWithXbox());

    }


    @Override
    public void periodic() {
        // Put code here to be run every loop              
       // System.out.println("DriveTrain Subsystem Gyro:"+Robot.driveTrain.getGyroAngle()); 
       
       //SmartDashboard.putNumber("NavX", ahrs.getAngle());
       //SmartDashboard.putNumber("Gyro Angle", Gyro1.getAngle());
       //SmartDashboard.putNumber("Gyro Rate", Gyro1.getRate());
       //SmartDashboard.putNumber("Gyro Offset", Gyro1.getOffset());
       
    }

  


    //_________________________________________________________________________
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    
    //________ Joystick

    public void DriveWithXbox() { 
        
        
        double Jy=-1*Robot.oi.xbox.getY(Hand.kLeft)*1;
        double Jx=Robot.oi.xbox.getX(Hand.kLeft)*1;
        double Jz=Robot.oi.xbox.getX(Hand.kRight)*1;

       

        MechPower(Jx,Jy,Jz);

      

       
        
                                
    
    }


    public void MechPower(double Jx, double Jy, double Jz) { 
        double r = Math.hypot(Jx, Jy);
        double robotAngle = Math.atan2(Jy, Jx) - Math.PI / 4;
        double rightX = Jz;
        var LF = r * Math.cos(robotAngle) + rightX;
        double RF = r * Math.sin(robotAngle) - rightX;
        double LR = r * Math.sin(robotAngle) + rightX;
        double RR = r * Math.cos(robotAngle) - rightX;
        System.out.println("LF:" + LF + "  RF:" + RF );
        System.out.println("LR:" + LR + "  RR:" + RR );
        System.out.println("SubsystemDriveWithXbox ");

        SmartDashboard.putNumber("LF", LF);
        SmartDashboard.putNumber("RF", RF);
        SmartDashboard.putNumber("LR", LR);
        SmartDashboard.putNumber("RR", RR);


        DriveLF.set(ControlMode.PercentOutput, LF, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive     
        DriveLB.set(ControlMode.PercentOutput, LR, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
       DriveRF.set(ControlMode.PercentOutput, RF, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
       DriveRB.set(ControlMode.PercentOutput, RR, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade                      
    
    }





    /*
    public void Move(double LB){
        DriveLB.set(ControlMode.PercentOutput, LB, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive

    }
 */
 
public void TargetAdjust(){

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tl = table.getEntry("tv");

    double Tx = tx.getDouble(-1);
    double Ty = ty.getDouble(-1);
    double Tl = tl.getDouble(-1);
    double Tarea = ta.getDouble(0.0);
    System.out.println("Limelight Data Tx "+Tx+" Ty "+Ty);
    /*
    Jy = -Robot.oi.xbox.getY();     // Get joystick input jleft
    Jx = -Robot.oi.xbox2.getX();     // jright  
    */                 
    double Jy=-1*Robot.oi.xbox.getY(Hand.kLeft)*1;
    double Jx=Robot.oi.xbox.getX(Hand.kLeft)*1;
    double Jz=Robot.oi.xbox.getX(Hand.kRight)*1;
    
    System.out.println("Target Aim Data:  x"+Tx+" y"+Ty+" l"+Tl+" a"+Tarea);
    System.out.println(" Jy"+Jy+" Jx"+Jx+"jAverage"+jAverage);

    
    if (Tl==1){                                  // Is there a target?        
        
                                   // Driver Forwards and Aim
            if(Tx > 10){
                     Jz = Jz+.3;
            }
            else if (Tx> 6) {
                Jz= Jz + (Tx-2)/(10-2)*.3;

            }
                 else if (Tx> 2){
                     Jz=Jz + (Tx-2)/(10-2)*.3;
                 }
                 else if (Tx> 0){
                    //do nothing

                 
                 }        
            else if (Tx < -10){
                Jz= Jz-.3;  
            }
            else if (Tx<- 6) {
                Jz= Jz + (-Tx-2)/(10-2)*.3;

            }
                else if (Tx< -2){
                    Jz=Jz - (-Tx-2)/(10-2)*.3;
                }
                else if (Tx< 0){
                    
                }
            }
            
          Robot.driveTrain.MechPower(Jx, Jy, Jz);
        }



/*
if(Tl==1){
    if(Ty > 10){
        Jx=Jx +.5;

    }
    else if (Tx > 6){
        Jx= Jx + (Ty-2)/(10-2)*.3;
    }
    else if (Tx > 2){
        Jx=Jx + (Ty-2)/(10-2)*.3;

    }
    else if (Tx>0){
        //do nothing
    }
    Robot.driveTrain.MechPower(Jx, Jy, Jz);

}

*/
        /* move at target
        if(Tl==1){
            if(Ty > -25){
                Jy=Jy +.5;

            }
            else if (Ty > -13.5){
                Jy= Jy + (Ty-2)/(10-2)*.3;
            }
            else if (Ty > -5){
                Jy=Jy + (Ty-2)/(10-2)*.3;

            }
            else if (Ty>0){
                //do nothing
            }
            Robot.driveTrain.MechPower(Jx, Jy, Jz);

        }

*/






        
    

    

/* old tank drive aiming code
if (jAverage>=AimThresholdPower){                               // Driver Forwards and Aim
    if(Tx > AimingMinAngle){
        Robot.driveTrain.ApplyMotorPower(Jy*(.5+AimingMultiplier), Jx*(1-AimingMultiplier));            // Adjust Right    used to be 1
    }
    else if (Tx < -AimingMinAngle){
        Robot.driveTrain.ApplyMotorPower(Jy*(.5-AimingMultiplier), Jx*(1+AimingMultiplier));            // Adjust Left
    }
    else {
        Robot.driveTrain.ApplyMotorPower(Jy, Jx);            // Below angle adjustment. Adjust none. 
    }
}
else if (jAverage<=-AimThresholdPower){                         // Drive backwards and Aim
    if(Tx > AimingMinAngle){
        Robot.driveTrain.ApplyMotorPower(Jy*(.5-AimingMultiplier), Jx*(1+AimingMultiplier));            // Adjust Right
    }
    else if (Tx < AimingMinAngle){
        Robot.driveTrain.ApplyMotorPower(Jy*(.5+AimingMultiplier), Jx*(1-AimingMultiplier));            // Adjust Left   
    }
    else {
        Robot.driveTrain.ApplyMotorPower(Jy, Jx);            // Below minimum needed angle adjustment. Don't adjust
    }
} else {                                                             // Just aim, NO driving
    if(Tx > AimingMinAngle){
         if(Tx> AimingMinAngle+5){AimingPower= .3;}          // Reduce power .3

        Robot.driveTrain.ApplyMotorPower(AimingPower, -AimingPower);  
                   }
    else if (Tx < -AimingMinAngle){
        if(Tx< -AimingMinAngle+5){AimingPower= .3;}        // Reduce power .3
        Robot.driveTrain.ApplyMotorPower(-AimingPower, AimingPower);    
        
    }
    else {Robot.driveTrain.ApplyMotorPower(Jy,Jx);}            // Not Driving, Just Aim
}
}
*/




public void SensorInfo() {
        //System.out.println("Drive Train: Left "+DriveLF.getSelectedSensorPosition(0)+" Right"+DriveRF.getSelectedSensorPosition(0));   // zero is pidIdx
    }/*
public void MotorPower(double L, double R) {                    // Tankdrive
        
        
        DriveLF.set(ControlMode.PercentOutput, L);
        DriveRB.set(ControlMode.PercentOutput, R);
      
    }
    */
    
    
    /* Zero drive train encoders */
	public void SensorZero() {
        DriveLF.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveLB.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveRF.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveRB.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        System.out.println(".    SensorZero - Drive Train called.");
    }
    

 




    // this method is currently not used.
    public void ApplyMotorPower(double L, double R) {                    // Tankdrive Percent output
        
        
        DriveLB.set(ControlMode.PercentOutput, L, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
        DriveRF.set(ControlMode.PercentOutput, R, DemandType.ArbitraryFeedForward, 0);  // With the zero at the end I don't think it does anything different   
        DriveLB.set(ControlMode.PercentOutput, L, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
        DriveRB.set(ControlMode.PercentOutput, R, DemandType.ArbitraryFeedForward, 0);  // With the zero at the end I don't think i
        
    }
    public void sideways(double X, double Y){
        DriveLB.set(ControlMode.PercentOutput, X, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
        DriveLF.set(ControlMode.PercentOutput, Y, DemandType.ArbitraryFeedForward, 0);

    }




/*
    public void TargetAdjust(){
        
    //private double m_PipeLine;

     // ******************************************************
    // This code is currently designed so that the user can overpower the assist.
    // Pressing the button guides the user, but if they apply full power in one direction it will still go that way.
    // This can help when aquiring the wrong target.
    // The amount of over powering automated control is dictated by the AimingMultiplier 
    // Note that the motors max out at 1.o so above the Multipler amount 
    // (if multiplier of 0.3, then above 0.7 power on joystick) has reduced effect, since the motors can not be set to 1.3 and 0.7.  They get 1.0 and 0.7;
    // aka. Slow down a little to

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tl = table.getEntry("tv");

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
*/

    }

