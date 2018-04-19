package com.Robot;

import lejos.nxt.LCD;
import lejos.util.Delay;

public class Calibration 
{
	private Robot _roby;
	
	public Calibration(Robot roby)
	{
		_roby = roby;
	}
	
	public void startMonitor()
	{
		for(int i=0; i<100; ++i)
		{
			_roby.moveforward();
			Delay.msDelay(100);
			_roby.stopMove();
			LCD.drawInt(_roby.getLightSensorVal(), 0, i+2);
		}
		
	}
}
