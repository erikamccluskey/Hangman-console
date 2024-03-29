/* Phase 2 - Lab01
Hangman game
Author: Erika McCluskey
*/

import java.io.*;

public class Hangman
{
    public static void main(String[] args)
    {
	WelcomeAndRules();	
	Play();
	System.out.println("Thanks for playing. Come again! :) ");
    }

    private static void WelcomeAndRules()
    {
	System.out.println("\n");
	System.out.println("Welcome to hangman!");
	System.out.println("Rules:");
	System.out.println("You have 5 attemps to guess the word.");
	System.out.println("If you enter more than 1 character on the guess, you will be assumed to be guessing the word. If you guess wrong, the game is over, if you guess right, you win.");
	System.out.println("Good luck!\n");
    }
    
    private static void Play()
    {
	Console cons = System.console();
	String secretWord = new String(cons.readPassword("Please enter the secret word: "));
	String hiddenWord = "*".repeat(secretWord.length());
	System.out.println("The word is: " + hiddenWord);
	
	int attempts = 0;

	while(attempts < 5)
	    {
		System.out.println("Guess: ");
		String guess = System.console().readLine();	   

		if(guess.length() > 1)
		    {
			String endGameMessage;

			if(guess.matches(secretWord))
			    System.out.println("Congratulations, you guessed the word! You are a winner!");
			else
			    System.out.println("Unfortunately, you did not guess the word right. You lost.");

			return;
		    }
		
		if (secretWord.contains(guess))
		    {
			System.out.println("Correct.  You have " + (5-attempts) + " attempts left.");
			hiddenWord = UncoverGuessedLetter(hiddenWord, secretWord, guess);
		    }
		else
		    {
			attempts++;
			System.out.println("Wrong. You have " + (5-attempts) + " attempts left.");
		    }	      	  

		System.out.println("The word is: " + hiddenWord);
	    }
	
	if(hiddenWord.matches(secretWord))
	   System.out.println("Congratulations, you guessed the word! You are a winner!");
	else   
	    System.out.println("You have run out of attempts. The word was " + secretWord + ". Better luck next time.");
    }

    private static String UncoverGuessedLetter(String hiddenWord, String secretWord, String guess)
    {
	char guessedChar = guess.charAt(0);
       	char[] hiddenWordChars = hiddenWord.toCharArray();
	int index = secretWord.indexOf(guessedChar);

	while (index >= 0)
       	    {
		hiddenWordChars[index] = guessedChar;
	       	index = secretWord.indexOf(guessedChar, index+1);
       	   }

	return String.valueOf(hiddenWordChars);	    
    }
}

   	
