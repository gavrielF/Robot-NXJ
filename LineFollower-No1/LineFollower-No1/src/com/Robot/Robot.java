package com.Robot;

import com.behavior.BehaviorMoveOnLine;
import com.behavior.BehaviorRelocationOnLine;
import com.behavior.BehaviorStopRobotMoving;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.util.PilotProps;

public class Robot 
{
	private final RotateMoveController _pilot;
	private final LightSensor _lightSensor;
	private final UltrasonicSensor _sonar;
	
	private boolean _isFinish;
	

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
		_sonar = new UltrasonicSensor(SensorPort.S4);
		
		
		_pilot.setTravelSpeed(20); // cm/sec
	//	_pilot.setRotateSpeed(180); // deg/sec
		
		_isFinish = false;

		
	}
	
	public void startTrackLine(int val)
	{
		BehaviorRelocationOnLine relocationOnLine = new BehaviorRelocationOnLine(this);
		BehaviorMoveOnLine moveOnLine = new BehaviorMoveOnLine(this);
		BehaviorStopRobotMoving stopRobotMoving = new BehaviorStopRobotMoving(this);
		
		Behavior[] bArray = {relocationOnLine, moveOnLine, stopRobotMoving};
	    (new Arbitrator(bArray)).start();
	}
	
	public int getLightSensorVal()
	{
		return _lightSensor.readValue();
	}
	
	public int getSonarVal()
	{
		return _sonar.getDistance();
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
	
	public boolean isFinish()
	{
		return _isFinish;
	}
	
	public void setFinish()
	{
		_isFinish = true;
	}
	
}
