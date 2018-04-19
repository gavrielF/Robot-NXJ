package com.Robot;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.util.PilotProps;

public class Robot 
{
	private final RotateMoveController _pilot;
	private final LightSensor _lightSensor;

	public Robot() throws Exception
	{
		PilotProps pp = new PilotProps();
    	pp.loadPersistentValues();
    	float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.96"));
    	float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "13.0"));
    	RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
    	RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
    	boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"false"));
    	
		// Change last parameter of Pilot to specify on which 
		// direction you want to be "forward" for your vehicle.
		// The wheel and axle dimension parameters should be
		// set for your robot, but are not critical.
		_pilot = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		_lightSensor = new LightSensor(SensorPort.S1);
		
	}
	
	public void moveForeard(int val)
	{
		
	}
	
	public int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public void moveforward()
	{
		_pilot.forward();
	}
	
	public void stopMove()
	{
		_pilot.stop();
	}
	
	
	public RotateMoveController getPilot()
	{
		return _pilot;
	}
	
}
