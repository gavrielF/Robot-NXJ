package com.Robot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.util.Delay;

public class Calibration {
	private Robot _roby;
	private List<Integer> _list = new ArrayList<Integer>();
	final String fileName = "temp.txt";

	public Calibration(Robot roby) 
	{
		_roby = roby;
	}

	public void startMonitor() 
	{
		int myInt;
		for (int i = 0; i < 50; ++i) 
		{
			_roby.moveforward();
			Delay.msDelay(50);
			_roby.stopMove();

			myInt = _roby.getLightSensorVal();
			_list.add(myInt);

		}

	}

	public void printToFile() 
	{
		LCD.clear();
		LCD.drawInt(_list.get(0), 0, 0);
		

		try 
		{
			File f = new File(fileName);
			FileOutputStream fos = new FileOutputStream(f);
			for (int i = 0; i < _list.size(); i++) 
			{
				fos.write(_list.get(i));
			}
			fos.close();
		} 
		catch (IOException e) 
		{
			LCD.drawString(e.getMessage(), 0, 0);
		}
		Button.waitForAnyPress();

	}
}
