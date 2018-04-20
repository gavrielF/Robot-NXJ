package com.behavior;

import com.Robot.Robot;

import lejos.robotics.subsumption.Behavior;

public class BehaviorRelocationOnLine implements Behavior
{
	private Robot _roby;
	private boolean suppress = false;
	
	public BehaviorRelocationOnLine(Robot roby)
	{
		_roby = roby;
	}
	
	@Override
	public boolean takeControl() 
	{
		if(!_roby.isFinish())
		{
			return _roby.getLightSensorVal() > 42;
		}
		
		return false;
				
	}

	@Override
	public void action() 
	{
		double sweep = 5;
		while (!suppress) 
		{
			_roby.getPilot().rotate(sweep,true);
			while (!suppress && _roby.getPilot().isMoving()) Thread.yield();
			sweep *= -1.5;
		}
		_roby.getPilot().stop();
		suppress = false;
		
	}

	@Override
	public void suppress() 
	{
		suppress = true;
		
	}
	
}
