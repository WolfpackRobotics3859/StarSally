/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.autonomous_commands.LongShotCross;
//import frc.robot.commands.autonomous_commands.ShootAndCross;
import frc.robot.commands.climb.ClimbDisabled;
import frc.robot.commands.climb.ClimbEnabled;
import frc.robot.commands.drive.JoystickDrive;
import frc.robot.commands.indexer.IndexerAcceptBall;
import frc.robot.commands.indexer.IndexerClimb;
import frc.robot.commands.indexer.IndexerForceOut;
import frc.robot.commands.indexer.IndexerIdleSpeed;
import frc.robot.commands.indexer.IndexerIndexBall;
import frc.robot.commands.indexer.IndexerResetBallCount;
import frc.robot.commands.indexer.IndexerShoot;
import frc.robot.commands.indexer.IndexerStandby;
import frc.robot.commands.indexer.StopFilterWheels;
import frc.robot.commands.intake.IntakeActive;
import frc.robot.commands.intake.IntakeDormant;
import frc.robot.commands.intake.IntakeReverse;
import frc.robot.commands.shooter.ShooterActive;
import frc.robot.commands.shooter.ShooterDormant;
import frc.robot.commands.shooter.ShooterFlashlightOff;
import frc.robot.commands.shooter.ShooterFlashlightOn;
import frc.robot.commands.shooter.ShooterToggleShoot;
import frc.robot.custom_triggers.FlashlightTrigger;
import frc.robot.custom_triggers.Idle4Balls;
import frc.robot.custom_triggers.ReadyToAcceptBall4;
import frc.robot.custom_triggers.ReadyToAcceptBall_1_3;
import frc.robot.custom_triggers.ReadyToIdle;
import frc.robot.custom_triggers.SpinUpTrigger;
import frc.robot.custom_triggers.XboxTriggerAxis;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final Drivetrain m_Drivetrain = new Drivetrain();
  private final Intake m_Intake = new Intake();
  private final static Indexer m_Indexer = new Indexer();
  private final Shooter m_Shooter = new Shooter();
  private final Climb m_Climb = new Climb();

  private final JoystickDrive m_JoystickDrive = new JoystickDrive(m_Drivetrain);
  private final IntakeDormant m_IntakeDormant = new IntakeDormant(m_Intake);
  public final IndexerIdleSpeed m_IndexerIdleSpeed = new IndexerIdleSpeed(m_Indexer);
  private final ShooterDormant m_ShooterDormant = new ShooterDormant(m_Shooter);


//  private ShootAndCross m_ShootAndCross = new ShootAndCross(m_Shooter, m_Indexer, m_Drivetrain);
  private LongShotCross m_LongShotCross = new LongShotCross(m_Shooter, m_Indexer, m_Drivetrain);

  public static XboxController controller_1 = new XboxController(0);
  public static XboxController controller_2 = new XboxController(1);

  public static Joystick controller_1_joy = new Joystick(0);
  public static XboxTriggerAxis controller_1_right_trigger_axis = new XboxTriggerAxis(controller_1_joy, 3);
  public static XboxTriggerAxis controller_1_left_trigger_axis = new XboxTriggerAxis(controller_1_joy, 2);
  public static FlashlightTrigger flashlightTrigger = new FlashlightTrigger(controller_1_joy, 2);
  public static SpinUpTrigger spinUpTrigger = new SpinUpTrigger(controller_1_joy, 2);
  public static ReadyToAcceptBall4 readyToAcceptBall4 = new ReadyToAcceptBall4(m_Indexer);
  public static ReadyToAcceptBall_1_3 readyToAcceptBall_1_3 =  new ReadyToAcceptBall_1_3(m_Indexer);
  public static ReadyToIdle readyToIdle = new ReadyToIdle(m_Indexer);
  public static Idle4Balls idle4Balls = new Idle4Balls(m_Indexer);


  public static Button xbox_1_a = new JoystickButton(controller_1, 1);
  public static Button xbox_1_lb = new JoystickButton(controller_1, 5);
  public static Button xbox_1_lstick = new JoystickButton(controller_1, 9);
  public static Button xbox_1_rstick = new JoystickButton(controller_1, 10);
  public static Button xbox_2_a = new JoystickButton(controller_2, 1);
  public static Button xbox_2_b = new JoystickButton(controller_2, 2);
  public static Button xbox_2_x = new JoystickButton(controller_2, 3);
  public static Button xbox_2_select = new JoystickButton(controller_2, 7);
  public static Button xbox_2_y = new JoystickButton(controller_2, 4);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureSubsytems();
    configureButtonBindings();
  }

  private void configureSubsytems() {
    m_Indexer.sys_init();

    m_Drivetrain.sys_init();
    m_Drivetrain.setDefaultCommand(m_JoystickDrive);

    m_Intake.sys_init();
    m_Intake.setDefaultCommand(m_IntakeDormant);

    m_Shooter.sys_init();
    m_Shooter.setDefaultCommand(m_ShooterDormant);

    m_Climb.sys_init();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    controller_1_right_trigger_axis.whileActiveContinuous(new IntakeActive(m_Intake));
    flashlightTrigger.whenActive(new ShooterFlashlightOn(m_Shooter))
                     .whenInactive(new ShooterFlashlightOff(m_Shooter));
    spinUpTrigger.whileActiveContinuous(new ShooterActive(m_Shooter));

    xbox_1_lb.whileActiveContinuous(new IndexerShoot(m_Indexer), false);
    xbox_1_lstick.whenPressed(new ShooterToggleShoot(m_Shooter));

    xbox_2_select.whenPressed(new IndexerResetBallCount(m_Indexer));

    xbox_2_a.whileHeld(new IndexerClimb(m_Indexer), false)
            .whileHeld(new ClimbEnabled(m_Climb), false);

    xbox_2_x.whenPressed(new ClimbDisabled(m_Climb));

    xbox_2_y.whileActiveContinuous(new IndexerForceOut(m_Indexer), false)
            .whileActiveContinuous(new IntakeReverse(m_Intake), false);

    readyToAcceptBall_1_3.whileActiveContinuous(new StopFilterWheels(m_Indexer))
                         .whenActive(new IndexerIndexBall(m_Indexer), false);
    readyToAcceptBall4.whenActive(new IndexerAcceptBall(m_Indexer), false)
                      .whileActiveContinuous(new StopFilterWheels(m_Indexer));
    readyToIdle.whileActiveContinuous(new IndexerIdleSpeed(m_Indexer));

    idle4Balls.whileActiveContinuous(new IndexerStandby(m_Indexer));

  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomousr
   */
  public Command getAutonomousCommand() {
    return m_LongShotCross;
  }
}
