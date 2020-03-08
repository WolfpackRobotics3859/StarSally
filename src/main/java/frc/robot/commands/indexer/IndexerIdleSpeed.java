/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerIdleSpeed extends CommandBase {
  private final Indexer m_IndexerSubsystem;
  public IndexerIdleSpeed(Indexer subsystem) {
    m_IndexerSubsystem = subsystem;
    addRequirements(m_IndexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_IndexerSubsystem.set_filter(0.3);
    m_IndexerSubsystem.set_bands_speed(0.1);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IndexerSubsystem.set_filter(0);
    m_IndexerSubsystem.set_bands_speed(0);
    System.out.println("Indexer Idling...");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
