/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.custom_triggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Indexer;

public class ReadyToAcceptBall_1_3 extends Trigger {
    Indexer m_IndexerSubsytem;

    public ReadyToAcceptBall_1_3(Indexer subsystem){
        m_IndexerSubsytem = subsystem;
    }

    @Override
    public boolean get() {
        return m_IndexerSubsytem.ready_to_index_ball_1_to_3();
    }
}
