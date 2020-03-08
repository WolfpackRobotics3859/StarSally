/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private WPI_TalonFX _extIntakeFx = new WPI_TalonFX(k_exterior_intake_falcon);
  DoubleSolenoid _extIntakeSolenoid = new DoubleSolenoid(1, 5);
  
  public void sys_init() {
    _extIntakeFx.configFactoryDefault();
    _extIntakeFx.set(ControlMode.PercentOutput, 0);
    _extIntakeFx.setInverted(k_exterior_intake_falcon_isInverted);
    System.out.println("Intake Initiated...");
  }

  public void set_intake_speed(double input_speed) {
    _extIntakeFx.set(ControlMode.PercentOutput, input_speed);
  }

  public void set_intake_extended(boolean input_position) {
    if(input_position){
      _extIntakeSolenoid.set(Value.kForward);
    } else {
      _extIntakeSolenoid.set(Value.kReverse);
    }
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
