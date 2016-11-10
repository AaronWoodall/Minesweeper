package minesweeper.controllers;

import java.util.Scanner;

import minesweeper.models.Player;

public class UserInteraction {
	
	private Scanner scanner;
	private Player player;
	
	public UserInteraction(){
		scanner = new Scanner(System.in);
		initPlayer();
	}
	
	private void initPlayer(){
		System.out.println("What is your name?");
		player = new Player(scanner.nextLine());
	}
	
	public int promptForInt(String prompt, int minLength, int maxLength){
		int selection = maxLength;
		boolean isValidInput;
		do{
			isValidInput = true;
			System.out.println(prompt);		
			String input = scanner.nextLine();
			try{
				selection = Integer.parseInt(input);
				if(!(selection > minLength && selection < maxLength)){
					System.out.println("Invalid input");
					isValidInput = false;
				}
			}
			catch(NumberFormatException e){
				System.out.println("Invalid input");
				isValidInput = false;
			}
		}
		while(!isValidInput);		
		return selection;
	}
	
	public void promptOptions(String[] prompts){
		for(int i = 0; i < prompts.length; i++){
			respondToUser("(" + i + ") " + prompts[i]);				
		}
	}
	
	public void respondToUser(String response){
		System.out.println(response);
	}
}
