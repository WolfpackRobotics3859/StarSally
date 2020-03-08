/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.custom_triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Add your docs here.
 */
public class XboxTriggerAxis extends Trigger {
    Joystick m_Joystick;
    int m_axis;

    public XboxTriggerAxis(Joystick input_joystick, int raw_axis){
        m_Joystick = input_joystick;
        m_axis = raw_axis;
    }

    @Override
    public boolean get() {
      if(Math.abs(m_Joystick.getRawAxis(m_axis)) > 0.1){
        return true;
      } else {
          return false;
      }
    }
}
