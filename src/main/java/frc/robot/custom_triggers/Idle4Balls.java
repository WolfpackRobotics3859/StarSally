/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.custom_triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Indexer;

public class Idle4Balls extends Trigger {
    Indexer m_IndexerSubsytem;

    public Idle4Balls(Indexer subsystem){
        m_IndexerSubsytem = subsystem;
    }

    @Override
    public boolean get() {
        return m_IndexerSubsytem.has_4_balls();
    }
}