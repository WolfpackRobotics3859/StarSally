/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drive.LinearDrive;
import frc.robot.commands.indexer.IndexerShoot;
import frc.robot.commands.shooter.ShooterActive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootAndCross extends SequentialCommandGroup {
  // private final Shooter m_ShooterSubsystem;
  // private final Indexer m_IndexerSubstem;
  // private final Drivetrain m_DrivetrainSubsystem;

  public ShootAndCross(Shooter m_shooter, Indexer m_indexer, Drivetrain m_drivetrain) {

    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new ShooterActive(m_shooter).raceWith(new LinearDrive(m_drivetrain, -0.2).withTimeout(2)).andThen(new IndexerShoot(m_indexer).withTimeout(5).andThen(new LinearDrive(m_drivetrain, 0.3).withTimeout(2))));
  }
}