/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerAcceptBall extends CommandBase {
  private Indexer m_IndexerSubsystem;
  private Timer m_Timer;

  public IndexerAcceptBall(Indexer subsystem) {
    m_IndexerSubsystem = subsystem;
    addRequirements(m_IndexerSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
//    goal_position = m_IndexerSubsystem.change_filter_position_by(k_filter_position_increment);
    m_IndexerSubsystem.increment_ball_count();
    m_IndexerSubsystem.set_filter(0.3);
    System.out.println("Accepting Ball...");
    m_Timer = new Timer();
    m_Timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IndexerSubsystem.set_filter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_Timer.get() > 0.35) {
      return true;
    } else {
      return false;
    }
  }
}
