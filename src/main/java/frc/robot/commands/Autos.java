/gtad// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands; 

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Conveyor;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static CommandBase exampleAuto(Conveyor subsystem) {
    return new SequentialCommandGroup(
      new InstantCommand(() -> {
        subsystem.setSpeed(0.5);
      }, subsystem),
      new WaitCommand(0.5),
      new InstantCommand(() -> {
        subsystem.setSpeed(0);
      }, subsystem)
    );
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
