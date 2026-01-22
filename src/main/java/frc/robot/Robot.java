// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

  private SparkMax motor1, motor2;
  private DifferentialDrive couchDrive;
  private Joystick wheel;

  private double steering, throttle, brake, speed;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  public Robot() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    motor1 = new SparkMax(1,MotorType.kBrushless);
    motor2 = new SparkMax(2,MotorType.kBrushless);
    motor2.setInverted(true);

    couchDrive = new DifferentialDrive(motor1, motor2);

    wheel = new Joystick(0);
  }

  @Override
  public void teleopPeriodic() {
    steering = getSteering();
    throttle = -getThrottle();
    brake = -getBrake();
    speed = throttle;
    speed -= 0.02 * speed + brake;

    couchDrive.arcadeDrive(speed, steering);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}

  public double getSteering() {
      return wheel.getRawAxis(0);
  }
  public double getThrottle() {
      return wheel.getRawAxis(2);
  }
  public double getBrake() {
      return wheel.getRawAxis(3);
  }
}
