/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  DoubleSolenoid _breakSolenoid = new DoubleSolenoid(2, 6);
  DoubleSolenoid _shifterSolenoid = new DoubleSolenoid(3, 7);
  
  public void sys_init() {
    _breakSolenoid.set(Value.kReverse);
    _shifterSolenoid.set(Value.kReverse);
  }
  
  public void toggle_climb_true() {
      _shifterSolenoid.set(Value.kForward);
      _breakSolenoid.set(Value.kForward);
  }

  public void toggle_climb_false() {
    _breakSolenoid.set(Value.kReverse);
    _shifterSolenoid.set(Value.kReverse);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
