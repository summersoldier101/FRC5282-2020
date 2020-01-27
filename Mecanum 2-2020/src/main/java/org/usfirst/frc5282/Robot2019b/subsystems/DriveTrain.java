
package org.usfirst.frc5282.Robot2019b.subsystems;

import org.usfirst.frc5282.Robot2019b.Constants;
import org.usfirst.frc5282.Robot2019b.Robot;
import org.usfirst.frc5282.Robot2019b.commands.*;

import edu.wpi.first.wpilibj.GenericHID.Hand;  
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

    private TalonSRX DriveLF;   
    private TalonSRX DriveLB;  
    private TalonSRX DriveRF;
    private TalonSRX DriveRB;



    //=======================================================================================================
    // Genernal Constants
    private static final int kdTimeoutMs = 30;                      //set to zero to skip error checking
    private static final double kdDeadBand = 0.02;                      //dead band for motor power
  


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


        
        double r = Math.hypot(Jx, Jy);
        double robotAngle = Math.atan2(Jy, Jx) - Math.PI / 4;
        double rightX = Jz;
        var LF = r * Math.cos(robotAngle) + rightX;
        double RF = r * Math.sin(robotAngle) - rightX;
        double LR = r * Math.sin(robotAngle) + rightX;
        double RR = r * Math.cos(robotAngle) - rightX;
        System.out.println("LF:" + LF + "  RF:" + RF );
        System.out.println("LR:" + LR + "  RR:" + RR );
        System.out.println(" ");

        SmartDashboard.putNumber("LF", LF);
        SmartDashboard.putNumber("RF", RF);
        SmartDashboard.putNumber("LR", LR);
        SmartDashboard.putNumber("RR", RR);

        //leftFront.setPower(v1); old old from origianl math code example
        //rightFront.setPower(v2);
        //leftRear.setPower(v3)
        //rightRear.setPower(v4);
        
        
       
       // DriveRF.set(RF);         
        //DriveLR.set(LR);
        //DriveRR.set(RR);

        DriveLF.set(ControlMode.PercentOutput, LF, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive     
        DriveLB.set(ControlMode.PercentOutput, LR, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
       DriveRF.set(ControlMode.PercentOutput, RF, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
       DriveRB.set(ControlMode.PercentOutput, RR, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive



        //double leftYstick = 0.0;
        //double rightYstick = 0.0;

        //leftYstick = -1.0 * Robot.oi.leftStick.getY();
        //rightYstick = -1.0 * Robot.oi.rightStick.getY();
            
        //if(leftYstick==0.0 && rightYstick==0.0){                                // if no input from regular driver
        //    rightYstick = -1.0 * Robot.oi.xboxS.getY(Hand.kRight);       // check for 2nd xbox
        //    leftYstick = -1.0 * Robot.oi.xboxS.getY(Hand.kLeft);
        //}


        //ApplyMotorPower(leftYstick, rightYstick);                                   // apply power    
    
    }
    
 
 
 
    public void SensorInfo() {
        //System.out.println("Drive Train: Left "+DriveLF.getSelectedSensorPosition(0)+" Right"+DriveRF.getSelectedSensorPosition(0));   // zero is pidIdx
    }

    
    
    /* Zero drive train encoders */
	public void SensorZero() {
        DriveLF.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveLB.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveRF.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        DriveRB.setSelectedSensorPosition(0, 0, kdTimeoutMs);   /* Zero the sensor */
        System.out.println(".    SensorZero - Drive Train called.");
    }
    

    public void resetGyro() {
        //System.out.println("Gyro Reset Method Called");
        //Gyro1.reset();
    }

/*
    public double getGyroAngle() {
        //double angle = Gyro1.getAngle();
        return ahrs.getAngle();
    }
*/
    


    // this method is currently not used.
    public void ApplyMotorPower(double L, double R) {                    // Tankdrive Percent output
        // This modifies power applied to the motors directly.

        // Speed reduction
        // Original design:  Both triggers is reduction2, one trigger is reduction 1.
        // This allows either trigger to be used for reduction 1 and both results in reduction 2.
     


        //Apply the power to the drive.
        /*
        DriveLF.set(ControlMode.PercentOutput, L, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
        DriveRF.set(ControlMode.PercentOutput, R, DemandType.ArbitraryFeedForward, 0);  // With the zero at the end I don't think it does anything different   
        DriveLR.set(ControlMode.PercentOutput, L, DemandType.ArbitraryFeedForward, 0);  // this was oringaly used for arcade drive
        DriveRR.set(ControlMode.PercentOutput, R, DemandType.ArbitraryFeedForward, 0);  // With the zero at the end I don't think i
        */
    }

}
