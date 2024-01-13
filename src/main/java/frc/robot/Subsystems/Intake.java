package frc.robot.subsystems;
import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    private TimeOfFlight sensor;
    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private Timer timer;
    private XboxController controller;

    public enum State {
        CLOSED,
        OPEN,
        CLOSING,
        OPENING
    }

    private State state = State.OPEN;

    // private XboxController controller;

    public Intake(XboxController controller) {
        sensor = new TimeOfFlight(00);
        motor1 = new CANSparkMax(31, MotorType.kBrushless);
        motor2 = new CANSparkMax(15, MotorType.kBrushless);
        motor1.setIdleMode(IdleMode.kBrake);
        motor1.setIdleMode(IdleMode.kBrake);
        timer = new Timer();
        timer.start();
        timer.get();
        timer.reset();
        this.controller = controller;
        
    }

    public double getDistance() {
        return sensor.getRange();
    }

    public void setSpeed(double speed) {
        motor1.set(speed);
        motor2.set(speed * 4 / 5 );
    }

    @Override //updates and checks value of distance, if 
    public void periodic() {
        System.out.println(sensor.getRange());
        
        if(state == State.OPEN) {
            if(getDistance() <= 190 && getDistance() >= 20 && timer.get() >= 4) {
                timer.reset();
                state = State.CLOSING;  
            }
        } else if (state == State.CLOSING) {
            setSpeed(-0.12);
            if (timer.get() > .7)  {
                
                setSpeed(0);
                state = State.CLOSED;
            }
        } else if (state == State.CLOSED) {
                if (controller.getAButton()) {
                    setSpeed(.10);
                    state = State.OPENING;
                    timer.reset();
                }
        } else if (state == State.OPENING) {
            if (timer.get() >= 1) {
                setSpeed(0);
                state = State.OPEN;
                timer.reset();
            }
        }
        // System.out.println(state);




    }


    private void TickMeter(int i) {
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.addDoubleProperty("Motor 1", motor1.getEncoder()::getPosition, null);
        builder.addDoubleProperty("Motor 2", motor2.getEncoder()::getPosition, null);
    }
}
