/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.commands.indexer.IndexerProcessStarting;
import frc.robot.commands.indexer.IndexerProcessEnded;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class IndexBallProcess extends SequentialCommandGroup {

  public IndexBallProcess(Indexer subsystem) {
    super(new IndexerProcessStarting(subsystem), new StopFilterWheels(subsystem), new IndexerIndexBall(subsystem), new IndexerProcessEnded(subsystem));
  }
}
