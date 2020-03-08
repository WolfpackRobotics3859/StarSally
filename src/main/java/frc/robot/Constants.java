/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
// Tuning Constants

    //Shooter Constants
    public static final int k_left_shoot_SlotIdx = 0;
    public static final int k_left_shoot_PIDLoopIdx = 0;
    public static final int k_left_shoot_TimeoutMs = 30;
    public static boolean k_left_shoot_SensorPhase = true;
    public static final Gains k_left_shoot_Gains = new Gains(0.25, 0, 0, 2048/200, 300, 1);

    public static final int k_shooter_goal_velocity = 450;

    public static final int k_flashlight = 0;


    //Intake Constants
    public static double active_intake_speed = 0.6;
    public static double active_bands_speed = 0.15;

    //Indexer Constants
    public static final int k_index_band_SlotIdx = 0;
    public static final int k_index_band_PIDLoopIdx = 0;
    public static final int k_index_band_TimeoutMs = 30;
    public static boolean k_index_band_SensorPhase = false;
 //   public static final Gains k_index_band_Gains = new Gains(0.7, 0, 0, 2048/200, 300, 1);
    public static final Gains k_index_band_Gains = new Gains(1, 0, 0, 2048/100, 300, 1);

    public static final int k_indexer_position_tolerance = 20;

 //   public static final int k_indexer_position_increment = 33000;
    public static final int k_indexer_position_increment = 35000;

    //Filter Wheels Constants
    public static final int k_filter_wheel_SlotIdx = 0;
    public static final int k_filter_wheel_PIDLoopIdx = 0;
    public static final int k_filter_wheel_TimeoutMs = 30;
    public static boolean k_filter_wheel_SensorPhase = false;
    public static final Gains k_filter_wheel_Gains = new Gains(0.7, 0, 0, 2048/450, 300, 1);
    
    public static final int k_filter_position_tolerance = 20;

    public static final int k_filter_position_increment = 8000;

// Hardware Identification Constants
    public static int k_left_drive_falcon_0 = 0;
    public static boolean k_left_drive_falcon_0_isInverted = false;

    public static int k_left_drive_falcon_1 = 1;
    public static boolean k_left_drive_falcon_1_isInverted = false;

    public static int k_right_drive_falcon_0 = 2;
    public static boolean k_right_drive_falcon_0_isInverted = true;

    public static int k_right_drive_falcon_1 = 3;
    public static boolean k_right_drive_falcon_1_isInverted = true;

    public static int k_right_shooter_falcon = 10;
    public static boolean k_right_shooter_falcon_isInverted = false;
    
    public static int k_left_shooter_falcon = 11;
    public static boolean k_left_shooter_falcon_isInverted = true;

    public static int k_climb_cartridge_falcon = 20;
    public static boolean k_climb_cartridge_falcon_isInverted = false;

    public static int k_exterior_intake_falcon = 30;
    public static boolean k_exterior_intake_falcon_isInverted = false;

    public static int k_intake_bands_viktor = 31;
    public static boolean k_intake_bands_viktor_isInverted = false;

    public static int k_green_intake_falcon = 32;
    public static boolean k_green_intake_falcon_isInverted = false;
}
