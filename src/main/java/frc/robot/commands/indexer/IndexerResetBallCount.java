/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Indexer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IndexerResetBallCount extends InstantCommand {
  private final Indexer m_IndexerSubsystem;
  
  public IndexerResetBallCount(Indexer subsystem) {
    m_IndexerSubsystem = subsystem;
  }

// Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_IndexerSubsystem.reset_ball_count();
  }
}
