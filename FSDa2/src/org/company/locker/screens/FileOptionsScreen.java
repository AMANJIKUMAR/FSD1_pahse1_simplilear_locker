package org.company.locker.screens;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.company.locker.entities.Directory;
import org.company.locker.services.ScreenService;


public class FileOptionsScreen implements Screen {
	
	private Directory dir = new Directory();
	
	private ArrayList<String> options = new ArrayList<>();

    public FileOptionsScreen() {
    	
    	options.add("1. Want to Add A File");
        options.add("2. Want to Delete A File");
        options.add("3. Want to Search A File");
        options.add("4. Return to Menu");
        
    }
    
    @Override
    public void Show() {
        System.out.println();
    	System.out.println("File Options Menu");
        for (String s : options) {
            System.out.println(s);
        }

    }

    public void GetUserInput() {
        int selectedOption;
        while ((selectedOption = this.getOption()) != 4) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option) {
        
    	switch(option) {

            case 1: // Add File
                this.AddFile();
                
                this.Show();
                break;
            case 2: // Delete File
                this.DeleteFile();
                
                this.Show();
                break;
            case 3: // Search File
                this.SearchFile();
                this.Show();
                break;
            
                
            case 4: // Return to Menu
            	
            	ScreenService.setCurrentScreen(ScreenService.WelcomeScreen);
                ScreenService.getCurrentScreen().Show();
                ScreenService.getCurrentScreen().GetUserInput();
                
                break;
                
            default:
            System.out.println("--------------");
                System.out.println("Invalid Option");
                break;
                
                
        }

    }
    
    
    public void AddFile() {
        System.out.println();
        System.out.println("-------------------------");
        System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();
        System.out.println();

        System.out.println("You are adding a file named: " + fileName);
        System.out.println("--------------------------------------");
        
		try {
			Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
			File file = new File(dir.getName() + fileName);
			
		      if (file.createNewFile()) {
                System.out.println();
                System.out.println("-----------------------");
		    	  System.out.println("File created: " + file.getName());
		    	  dir.getFiles().add(file);
		    	  
		      } else {
                System.out.println();
		        System.out.println("This File Already Exits, no need to add another");
                System.out.println("-------------------------------------------------");
            }
		}catch (IOException e){
			System.out.println(e);
		}
	}
        
    
    
    public void DeleteFile() {
    	System.out.println();
    	System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();
        System.out.println();
        System.out.println("You are deleting a file named: " + fileName);
        
        
	    
        
		Path path = FileSystems.getDefault().getPath(Directory.name + fileName).toAbsolutePath();
		File file = path.toFile();
	      if (file.delete()) {
            System.out.println();
	    	  System.out.println("Deleted File: " + file.getName());
              System.out.println("-------------------------");
	    	  dir.getFiles().remove(file);
	      } else {
            System.out.println("---------------------");
	        System.out.println("Failed to delete file:" + fileName + ", file was not found.");
            System.out.println("---------------------");
        }
    }
    
    public void SearchFile() {
    	
    	Boolean found = false;
    	System.out.println();
    	System.out.println("Please Enter the Filename:");

        System.out.println();
        String fileName = this.getInputString();

        System.out.println();
        System.out.println("You are searching for a file named: " + fileName);
        
       
        
        ArrayList<File> files = dir.getFiles();
        
        
        for(int i = 0; i < files.size(); i++) {
			if(files.get(i).getName().equals(fileName)) {
				System.out.println("Found " + fileName);
				found = true;
			}
        }
        if (found == false) {
            System.out.println();
        	System.out.println("File not found");
            System.out.println("------------------");
        }
    }
    
    private String getInputString() {

        Scanner in = new Scanner(System.in);
        return(in.nextLine());

    }
    
    private int getOption() {
        Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println();
        	System.out.println("Invalid input");
            System.out.println("-------------------");
        }
        return returnOption;

    }

}
