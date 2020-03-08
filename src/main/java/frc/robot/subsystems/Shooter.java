/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import static frc.robot.Constants.*;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  private final WPI_TalonFX _shooterLeftFx = new WPI_TalonFX(k_left_shooter_falcon);
  private final WPI_TalonFX _shooterRightFx = new WPI_TalonFX(k_right_shooter_falcon);
  DoubleSolenoid _hoodSolenoid = new DoubleSolenoid(0, 4);
  Relay _flashlight = new Relay(1);
  CameraServer camera;
  
  public void sys_init() {
    _flashlight.set(edu.wpi.first.wpilibj.Relay.Value.kForward);
    _hoodSolenoid.set(Value.kReverse);
    _shooterLeftFx.configFactoryDefault();
    _shooterRightFx.configFactoryDefault();

    _shooterLeftFx.set(ControlMode.PercentOutput, 0);
    _shooterRightFx.set(ControlMode.PercentOutput, 0);

    _shooterLeftFx.setInverted(k_left_shooter_falcon_isInverted);
    _shooterRightFx.setInverted(k_right_shooter_falcon_isInverted);

    _shooterLeftFx.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 
                                                 k_filter_wheel_PIDLoopIdx, k_filter_wheel_TimeoutMs);

                                  
    _shooterLeftFx.setSensorPhase(true);
    
    _shooterLeftFx.configNominalOutputForward(0, k_index_band_TimeoutMs);
		_shooterLeftFx.configNominalOutputReverse(0, k_index_band_TimeoutMs);
		_shooterLeftFx.configPeakOutputForward(1, k_index_band_TimeoutMs);
    _shooterLeftFx.configPeakOutputReverse(-1, k_index_band_TimeoutMs);
    
    _shooterLeftFx.configAllowableClosedloopError(0, k_filter_wheel_PIDLoopIdx, k_filter_wheel_TimeoutMs);

    _shooterLeftFx.config_kF(k_filter_wheel_PIDLoopIdx, k_left_shoot_Gains.kF, k_filter_wheel_TimeoutMs);
		_shooterLeftFx.config_kP(k_filter_wheel_PIDLoopIdx, k_left_shoot_Gains.kP, k_filter_wheel_TimeoutMs);
		_shooterLeftFx.config_kI(k_filter_wheel_PIDLoopIdx, k_left_shoot_Gains.kI, k_filter_wheel_TimeoutMs);
    _shooterLeftFx.config_kD(k_filter_wheel_PIDLoopIdx, k_left_shoot_Gains.kD, k_filter_wheel_TimeoutMs);

    _shooterRightFx.follow(_shooterLeftFx);
    _shooterLeftFx.configClosedloopRamp(1);
    _shooterLeftFx.setNeutralMode(NeutralMode.Brake);

    camera = CameraServer.getInstance();

    System.out.println("Shooter Initiated...");
  }
  
  private boolean hoodEnabled = false;
  public void toggle_shooter_hood() {
    if(hoodEnabled){
      _hoodSolenoid.set(Value.kReverse);
      hoodEnabled = false;
    } else {
      _hoodSolenoid.set(Value.kForward);
      hoodEnabled = true;
    }
  }

  public void set_shooter_velocity(int velocity) {
    _shooterLeftFx.set(ControlMode.Velocity, velocity);
  }

  public void stop_shooter() {
    _shooterLeftFx.set(ControlMode.PercentOutput, 0);
  }

  public void flashlight_on(){
    flashlight_on_or_off = true;
  }

  public void flashlight_off(){
    flashlight_on_or_off = false;
  }

  private boolean flashlight_on_or_off = false;
  public Relay.Value on_or_off(){
    if(flashlight_on_or_off){
      return Relay.Value.kForward;
    } else {
      return Relay.Value.kOff;
    }
  }

  @Override
  public void periodic() {
    _flashlight.set(on_or_off());
  }
}
