package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.playingwithfusion.TimeOfFlight;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Conveyor extends SubsystemBase {

    private CANSparkMax motor1;
    private CANSparkMax motor2;
    private TimeOfFlight sensor;
    private int count;

    public Conveyor(XboxController controller) {
        motor1 = new CANSparkMax(31, MotorType.kBrushless);
        motor2 = new CANSparkMax(15, MotorType.kBrushless);
        sensor = new TimeOfFlight(00);
        count = 0;
    }

    public Conveyor() {
        
    }
    
    public void setSpeed(double speed) {
        motor1.set(speed);
        motor2.set(speed);
    }


    public double getDistance() {
        return sensor.getRange();
    }

    @Override //updates and checks value of distance, if object is detected
    public void periodic() {
        //  if (getDistance() > 130) {
        //     TickMeter(3);
        //     count++;
        // } 
        // if(count == 3){
        //     motor1.set(.5);
        //     motor2.set(.5);



            
            
            
            
            



        // }

        
        

        

        


    }

    // private void when(boolean b) {
    // }

    private void TickMeter(int i) {
    }

    public int getCount() {
        return count;
    }
}