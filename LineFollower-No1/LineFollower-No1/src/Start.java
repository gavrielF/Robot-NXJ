
import com.Robot.Calibration;
import com.Robot.Robot;

import lejos.nxt.Button;

public class Start 
{

	public static void main(String[] args) 
	{		
		System.out.println("Press to start v3");
		Button.waitForAnyPress();
		//getPoseProvider
		try
		{
			Robot roby = new Robot();
			roby.startTrackLine(23);
//			Calibration c = new Calibration(roby);
//			c.startMonitor();
//			c.printToFile();
		}
		catch(Exception e)
		{
			
		}

	}

}
