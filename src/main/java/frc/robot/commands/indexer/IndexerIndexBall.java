/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import static frc.robot.Constants.*;

public class IndexerIndexBall extends CommandBase {
  private final Indexer m_IndexerSubsystem;
  private int goal_position;

  public IndexerIndexBall(Indexer subsystem) {
    m_IndexerSubsystem = subsystem;
    addRequirements(m_IndexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    goal_position = m_IndexerSubsystem.change_indexer_position_by(k_indexer_position_increment);
    m_IndexerSubsystem.increment_ball_count();
    System.out.println("Indexer Indexing...");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IndexerSubsystem.set_filter(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // m_IndexerSubsystem.sharp_sensor_timer_reset();
    m_IndexerSubsystem.set_indexer(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_IndexerSubsystem.is_indexer_within_tolerance(goal_position)) {
      return true;
    } else {
      return false;
    }
  }
}
