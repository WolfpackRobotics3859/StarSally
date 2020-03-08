/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Drivetrain extends SubsystemBase {
  private WPI_TalonFX _leftMasterFx = new WPI_TalonFX(k_left_drive_falcon_0);
  private WPI_TalonFX _leftSlaveFx = new WPI_TalonFX(k_left_drive_falcon_1);
  private WPI_TalonFX _rightMasterFx = new WPI_TalonFX(k_right_drive_falcon_0);
  private WPI_TalonFX _rightSlaveFx = new WPI_TalonFX(k_right_drive_falcon_1);
  
  private DifferentialDrive _drive = new DifferentialDrive(_leftMasterFx, _rightMasterFx);

  public void sys_init() {
    _leftMasterFx.configFactoryDefault();
    _rightMasterFx.configFactoryDefault();
    _leftSlaveFx.configFactoryDefault();
    _rightSlaveFx.configFactoryDefault();

    _leftMasterFx.set(ControlMode.PercentOutput, 0);
    _rightMasterFx.set(ControlMode.PercentOutput, 0);
    _leftSlaveFx.set(ControlMode.PercentOutput, 0);
    _rightSlaveFx.set(ControlMode.PercentOutput, 0);

    _leftSlaveFx.follow(_leftMasterFx);
    _rightSlaveFx.follow(_rightMasterFx);

    _leftMasterFx.setInverted(k_left_drive_falcon_0_isInverted);
    _rightMasterFx.setInverted(k_right_drive_falcon_0_isInverted);
    _leftSlaveFx.setInverted(k_left_drive_falcon_1_isInverted);
    _rightSlaveFx.setInverted(k_right_drive_falcon_1_isInverted);

    _drive.setRightSideInverted(false);
    _drive.setMaxOutput(0.8);
    System.out.println("Drivetrain Initiated...");
  }

  public void arcadeDrive(double thrust_axis, double rotation_axis, boolean isSquared){
    double thrust, rotation = 0;
    thrust = thrust_axis * 0.8;
    rotation = -rotation_axis;
    _drive.arcadeDrive(thrust, rotation, isSquared);
  }

  public void setDrive(boolean is_squared) {
    _drive.arcadeDrive(thrust_axis_storage, rotation_axis_storage, is_squared);
  }

  private double thrust_axis_storage = 0;
  private double rotation_axis_storage = 0;

  // private boolean storage_timeout() {
  //   return false;
  // }

  // private void valueStorage(double thrust_value, double rotation_value) {
  //   thrust_axis_storage = thrust_value;
  //   rotation_axis_storage = rotation_value;
  // }

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");


  public void update_limelight_values(){
 //   double x = tx.getDouble(0.0);
 //   double y = ty.getDouble(0.0);
 //   double area = ta.getDouble(0.0);
    
    //post to smart dashboard periodically
 //   SmartDashboard.putNumber("LimelightX", x);
 //   SmartDashboard.putNumber("LimelightY", y);
 //   SmartDashboard.putNumber("LimelightArea", area);

    table.getEntry("ledMode").setNumber(1);

  }

  double Kp= -0.1;
  double min_command = 0.05;
  public double limelight_tracking(){
    double heading_error = -tx.getDouble(0.0);
    double steering_adjustment = 0.0;

    if(heading_error > 1){
      steering_adjustment = Kp*heading_error - min_command;
    } else if (heading_error < 1){
      steering_adjustment = Kp*heading_error + min_command;
    }
    return steering_adjustment;
  }

  @Override
  public void periodic() {
    update_limelight_values();
  }
}
