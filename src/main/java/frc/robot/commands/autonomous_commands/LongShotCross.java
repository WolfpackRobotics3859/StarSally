/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.LinearDrive;
import frc.robot.commands.indexer.IndexerShoot;
import frc.robot.commands.shooter.ShooterActive;
import frc.robot.commands.shooter.ShooterToggleShoot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LongShotCross extends SequentialCommandGroup {
  /**
   * Creates a new LongShotCross.
   */
  public LongShotCross(Shooter m_shooter, Indexer m_indexer, Drivetrain m_drivetrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ShooterActive(m_shooter).alongWith(new ShooterToggleShoot(m_shooter)).withTimeout(3).andThen(new IndexerShoot(m_indexer).withTimeout(3).andThen(new LinearDrive(m_drivetrain, 0.3).withTimeout(2).alongWith(new ShooterToggleShoot(m_shooter)))));
  }
}

