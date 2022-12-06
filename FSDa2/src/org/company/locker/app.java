package org.company.locker;

import org.company.locker.screens.WelcomeScreen;

public class app {

    public static void main(String[] args) {
    	
    	WelcomeScreen welcome = new WelcomeScreen();
    	welcome.introWS();
    	welcome.GetUserInput();

    }
}
