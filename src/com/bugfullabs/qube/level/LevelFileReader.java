package com.bugfullabs.qube.level;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

public class LevelFileReader{
	
	XMLReader xr;
	SAXParser sp; 
	SAXParserFactory spf;
	
	BaseGameActivity activity;
	
	String mFile;
	
	LevelHandler handler;
	
	
	public LevelFileReader(BaseGameActivity e, String file){
	
		mFile = file;
		activity = e;
		
	}
	
	public Level getLevel(){
		
		try {
			readLevel();
		} catch (SAXNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Level level = handler.getLevel();
		return level;
	}
	
	private void readLevel() throws  SAXNotSupportedException{
		
	try {
		spf = SAXParserFactory.newInstance();
		sp = spf.newSAXParser();
		xr = sp.getXMLReader();

		handler = new LevelHandler();
		
		sp.parse(activity.getAssets().open("levels/"+mFile+".xml"), handler);
		
		
		} catch (Exception e) {
		System.out.println("XML Pasing Excpetion = " + e);
		}
	
	}
	
}