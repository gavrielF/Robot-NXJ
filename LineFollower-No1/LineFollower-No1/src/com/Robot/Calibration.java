package com.Robot;
import java.io.*;
import java.io.BufferedWriter;
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
		
		
        FileOutputStream fileStream = null;
        try {
            fileStream = new FileOutputStream(new File("TestCali.txt"));
        } catch (Exception e) {
            LCD.drawString("Can't make a file", 0, 0);
            System.exit(1);
        }

        DataOutputStream dataStream = new DataOutputStream(fileStream);

        for(int i=0; i<_list.size(); i++)
        {
                try 
                {
                   dataStream.writeBytes(String.valueOf(_list.get(i)));
                   dataStream.writeBytes(" ");                   
                   fileStream.flush();               
                } 
                catch (IOException e) 
                {
                    LCD.drawString("Can't write to the file", 0, 1);
                    System.exit(1);
                }
        }

        try
        {
            fileStream.close();
        }
        catch (IOException e)
        {
            LCD.drawString("Can't save the file", 0, 1);
            System.exit(1);
        }
		
		
		Button.waitForAnyPress();

	}
}
