package bowling;
/*
 * PinSetterView/.java
 *
 * Version:
 *   $Id$
 *
 * Revision:
 *   $Log$
 */

/**
 *  constructs a prototype PinSetter GUI
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;


public class PinSetterView implements PinsetterObserver {


    private Vector pinVect = new Vector ( );
    private JPanel firstRoll;
    private JPanel secondRoll;

    /**
     * Constructs a Pin Setter GUI displaying which roll it is with
     * yellow boxes along the top (1 box for first roll, 2 boxes for second)
     * and displays the pins as numbers in this format:
     *
     *                7   8   9   10
     *                  4   5   6
     *                    2   3
     *                      1
     *
     */
    

	private JFrame frame;
    
    public PinSetterView ( int laneNum ) {
	
	frame = new JFrame ( "Lane " + laneNum + ":" );
	
	Container cpanel = frame.getContentPane ( );
	
	JPanel pins = new JPanel ( );
	
	pins.setLayout ( new GridLayout ( 4, 7 ) );
	
	//********************Top of GUI indicates first or second roll
	
	JPanel top = new JPanel ( );
	
	firstRoll = new JPanel ( );
	firstRoll.setBackground( Color.yellow );
	
	secondRoll = new JPanel ( );
	secondRoll.setBackground ( Color.black );
	
	top.add ( firstRoll, BorderLayout.WEST );
	
	top.add ( secondRoll, BorderLayout.EAST );
	
	//******************************************************************
	
	//**********************Grid of the pins**************************
	
	
	JPanel one = new JPanel ();
	JLabel oneL = new JLabel ( "1" );
	one.add (oneL);
	JPanel two = new JPanel ();
	JLabel twoL = new JLabel ( "2" );
	two.add (twoL);
	JPanel three = new JPanel ();
	JLabel threeL = new JLabel ( "3" );
	three.add (threeL);
	JPanel four = new JPanel ();
	JLabel fourL = new JLabel ( "4" );
	four.add (fourL);
	JPanel five = new JPanel ();
	JLabel fiveL = new JLabel ( "5" );
	five.add (fiveL);
	JPanel six = new JPanel ();
	JLabel sixL = new JLabel ( "6" );
	six.add (sixL);
	JPanel seven = new JPanel ();
	JLabel sevenL = new JLabel ( "7" );
	seven.add (sevenL);
	JPanel eight = new JPanel ();
	JLabel eightL = new JLabel ( "8" );
	eight.add (eightL);
	JPanel nine = new JPanel ();
	JLabel nineL = new JLabel ( "9" );
	nine.add (nineL);
	JPanel ten = new JPanel ();
	JLabel tenL = new JLabel ( "10" );
	ten.add (tenL);
	
	//This Vector will keep references to the pin labels to show
	//which ones have fallen.
	
	pinVect.add ( oneL );
	pinVect.add ( twoL );
	pinVect.add ( threeL );
	pinVect.add ( fourL );
	pinVect.add ( fiveL );
	pinVect.add ( sixL );
	pinVect.add ( sevenL );
	pinVect.add ( eightL );
	pinVect.add ( nineL );
	pinVect.add ( tenL );	
	
	
	//******************************Fourth Row**************
	
	pins.add ( seven );
	pins.add ( new JPanel ( ) );
	pins.add ( eight );
	pins.add ( new JPanel ( ) );
	pins.add ( nine );
	pins.add ( new JPanel ( ) );
	pins.add ( ten );
	
	//*****************************Third Row***********
		
	pins.add ( new JPanel ( ) );
	pins.add ( four );
	pins.add ( new JPanel ( ) );
	pins.add ( five );
	pins.add ( new JPanel ( ) );
	pins.add ( six );
	
	//*****************************Second Row**************
 
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	pins.add ( two );
	pins.add ( new JPanel ( ) );
	pins.add ( three );
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	
	//******************************First Row*****************
	
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	pins.add ( one );
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	pins.add ( new JPanel ( ) );
	//*********************************************************
	
	top.setBackground ( Color.black );
	
	cpanel.add ( top, BorderLayout.NORTH );
	
	pins.setBackground ( Color.black );
	pins.setForeground ( Color.yellow );
	
	cpanel.add ( pins, BorderLayout.CENTER );
	
	frame.pack();
	
	
//	frame.show();
    }
    
    
    /**
     * This method receives a pinsetter event.  The event is the current
     * state of the PinSetter and the method changes how the GUI looks
     * accordingly.  When pins are "knocked down" the corresponding label
     * is grayed out.  When it is the second roll, it is indicated by the
     * appearance of a second yellow box at the top.
     *
     * @param e    The state of the pinsetter is sent in this event.
     */
    

    public void receivePinsetterEvent(PinsetterEvent pe){
	if ( !(pe.isFoulCommited()) ) {
	    	JLabel tempPin = new JLabel ( );
	    	for ( int c = 0; c < 10; c++ ) {
				boolean pin = pe.pinKnockedDown ( c );
				tempPin = (JLabel)pinVect.get ( c );
				if ( pin ) {
		    		tempPin.setForeground ( Color.lightGray );
				}
	    	}
    	}
		if ( pe.getThrowNumber() == 1 ) {
	   		 secondRoll.setBackground ( Color.yellow );
		}
	if ( pe.pinsDownOnThisThrow() == -1) {
		for ( int i = 0; i != 10; i++){
			((JLabel)pinVect.get(i)).setForeground(Color.black);
		}
		secondRoll.setBackground( Color.black);
	}
    }
    
    public void show() {
    	frame.show();
    }

    public void hide() {
    	frame.hide();
    }
    
    public static void main ( String args [ ] ) {
		PinSetterView pg = new PinSetterView ( 1 );
    }
    
}
