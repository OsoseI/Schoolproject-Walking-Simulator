//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 Walking Sim
// Course:   CS 300 Fall 2022
//
// Author:   Osose Inegbenoise
// Email:    inegbenoise@wisc.edu
// Lecturer: Jeff Nyhoff
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    None
// Partner Email:   None
// Partner Lecturer's Name: None
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:        Online office hours; helped  with the understanding of the mouse over concept
// Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class WalkingSim {
	private static Random randGen;
	private static int bgColor;
	
	private static PImage[] frames;

	private static Walker[] walkers;
	
	public static void setup() {
		randGen = new Random();
		bgColor = randGen.nextInt();
		frames = new PImage[Walker.NUM_FRAMES];
		walkers = new Walker [8];
		walkers[0] = null;
		
		int walkersRand = randGen.nextInt(8) + 1 ;
		
		for(int i=0; i < Walker.NUM_FRAMES; i++) {
			frames[i] = Utility.loadImage("images" + File.separator + "walk-"+ i + ".png");
		}
		
		for(int i=0; i < walkersRand; i++ ) {
			
			float walkerHeight = randGen.nextFloat(Utility.height());
			float walkerWidth = randGen.nextFloat(Utility.width());
			
			walkers[i] = new Walker(walkerHeight,walkerWidth);
		}
		
	}
	
	
	
	public static boolean isMouseOver (Walker walker) {
		float frameWidth = frames[walker.getCurrentFrame()].width/2; 
		float frameHeight = frames[walker.getCurrentFrame()].height/2; 

		float mouseX = Utility.mouseX();
		float mouseY = Utility.mouseY();
		float walkerX = walker.getPositionX();
		float walkerY = walker.getPositionY();
		
		if( mouseX > walkerX - frameWidth && mouseX < walkerX + frameWidth
				&& mouseY > walkerY - frameHeight && mouseY < walkerY + frameHeight) {
			return true;
		}
		return false;
	}
	
	public static void keyPressed(char c) {
		for(int i =0; i < walkers.length; i++) {
			if(walkers[i] != null) {
				if(c == 'a' || c == 'A') {
					walkers[i] = new Walker();
				}
				if(c == 's' || c == 'S') {
					if(walkers[i] != null) {
						walkers[i].setWalking(false);
					}
				}
			}
		}
	}
	public static void mousePressed() {
		for(int i =0; i < walkers.length; i++) {
			if(walkers[i] != null) {
				if(isMouseOver(walkers[i]) == true) {
					walkers[i].setWalking(true);
					break;
				} 
			}
		}
	}
	
	public static void draw() {
		Utility.background(bgColor);
		for(int i =0; i < walkers.length; i++) {
			if(walkers[i] != null) {
				if(walkers[i].isWalking()) {
						walkers[i].setPositionX((walkers[i].getPositionX() + 3)% Utility.width());
					}
				

				Utility.image(frames[walkers[i].getCurrentFrame()], walkers[i].getPositionX(), walkers[i].getPositionY());
				if(walkers[i].isWalking()) {
					walkers[i].update();
				}
				
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Utility.runApplication();
		
	}

}
