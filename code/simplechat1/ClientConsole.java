// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;

import client.*;
import common.*;

/**
 * This class constructs the UI for a chat client.  It implements the
 * chat interface in order to activate the display() method.
 * Warning: Some of the code here is cloned in ServerConsole 
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge  
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */


public class ClientConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */
  ChatClient client;

  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */
  public ClientConsole(String host, int port) 
  {    
	  /////////////////////////////////////////Changed for E49b and E51 M.A/N.D
	  String loginCommand=null;
	  String loginID = ""; // New String variable used to store the value of the BufferedReader
	  BufferedReader fromConsole =  new BufferedReader(new InputStreamReader(System.in));   // Creates new reader for the input
	  String password = "";
	  try{
		  do {
	      System.out.println("Welcome to your bank!"); 
		  System.out.println("Please enter your User Name or enter 'New User'");  
		  loginID = fromConsole.readLine();  //Reads the line and sets it to the new variable
		  }
		  while (loginID == "");
		  
		  if (loginID.equals("new user"))
		  {
			  String p1;
			  System.out.println("Please select a UserName");  //user selects a user name
			  loginID = fromConsole.readLine();
			  System.out.println("Welcome, " + loginID + "!");
			  do{
				  System.out.println("Please select a Password - remember, they are case-sensitive!");   ///User selects a password
				  password = fromConsole.readLine();
			  	System.out.println("Please re-enter your Password");  // for password verification
			  	p1 = fromConsole.readLine();
			  	if (!p1.equals(password))
			  	{
				  System.out.println("The passwords do not match!");
			  	}
			  	else if (p1.equals("")) System.out.println("The password cannot be null");
			  }
			  while (!p1.equals(password) || p1.equals(""));
			  loginCommand=("~"+loginID+" "+password);
		  }
		  else 
		  {
			  System.out.println("Please enter your password");
			  password = fromConsole.readLine();
			  loginCommand=("&"+loginID+" "+password);
		  }
		 }
		  catch (Exception ex) //If an error occurs
		    {
		      System.out.println ("Unexpected error while reading from console!"); 
		    }
	
	  /////////////////////////////////////////Changed for E49b and E51 M.A/N.D
    try  
    {
	client= new ChatClient(host, 5555, loginID, this, password);  // creates a new client
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
      System.exit(1);
    }
    client.handleMessageFromClientUI(loginCommand);
  }
  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */
  public void accept() 
  {
    try
    {
      BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
    	do{
  		  System.out.println("Welcome! Select one of the following");
  		  System.out.println("***********************************");
  		  System.out.println("**       New - View - Close -  **");
  		  System.out.println("**       Deposit - Withdraw -    **");
  		  System.out.println("********  Transfer - Exit *********");
  		  System.out.println("*******Please enter a command******");
  		  System.out.println("***********************************");
        message = fromConsole.readLine().toLowerCase();
    	} while (!message.equals("new") && !message.equals("view") && !message.equals("close") && !message.equals("deposit") && !message.equals("withdraw") && !message.equals("transfer") && !message.equals("exit"));
    	handleUICommand(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
    
  }

  /**
   * The following handles commands entered by the user, and sends them
   * as a string of commands to the bankserver to perform the required actions. 
   */
  public void handleUICommand(String command) 
  {
	BufferedReader fromConsole =  new BufferedReader(new InputStreamReader(System.in));
	String accountType = "";
	String temp = " ";
	String name = " ";
	if (command.equals("new"))   //"new" branch
	{
		do {
			try{
		System.out.println("would you like a Savings Account (enter S) or Chequings Account (Enter C)"); //user must select one of the two
		accountType = fromConsole.readLine().toLowerCase();
			}
		    catch (Exception ex) 
		    {
		      System.out.println
		        ("Unexpected error while reading from console!");
		    }
		} while (!accountType.equals("s") && !accountType.equals("c"));
		command = command + " " + accountType;         //append to command, will be sent to server at the end
				
		System.out.println("How many funds would you like to deposit?");   //initial deposit
		try{
		temp = fromConsole.readLine();
		}
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
		System.out.println("Please enter a name for this account");   //initial deposit
		try{
		name = fromConsole.readLine();
		}
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
		command += "." + temp + "&" + name + "?";
		
		if (accountType.equals("s")){
		System.out.println("Congratulations! You have a new Savings Account!");
		System.out.println("Your current balance is " + temp + "$. ");
		System.out.println("Annual interest rate: 5%.");
		System.out.println("Annual fees: 25$");
		}
		else if (accountType.equals("c"))
		{
			System.out.println("Congratulations! You have a new Chequings Account!");
			System.out.println("Your current balance is " + temp + "$. ");
			System.out.println("Annual interest rate: 3%.");
			System.out.println("Annual fees: 0$");	
			
		}
        System.out.println("Please press Enter to continue");
        try{
        String dummy = fromConsole.readLine();
        client.handleMessageFromClientUI(command);   ///Send command to Server, to activate new account.   
        }	    
        catch (Exception ex) 
	    {
  	    }   
	}
	else if (command.equals("deposit"))
	{
		String id = "";
		String deposit = ""; 
		System.out.println("Please enter the account ID");
		try{
			id = fromConsole.readLine();   //read account id, as a string
		} catch (Exception ex) {};
		System.out.println("Please enter the amount of the deposit");
		try
		{deposit = fromConsole.readLine();}  //read deposit amount, as a string
		  catch (Exception ex)  {}//If an error occurs
		   
		command += " " + id + "." + deposit + "&"; //Put together data string
        client.handleMessageFromClientUI(command); //Send to BankServer to be analysed 
	}
	
	else if (command.equals("withdraw"))
	{
		String id = "";
		String withdrawAmount = "";
		System.out.println("Please enter the account id");
		try{id = fromConsole.readLine();} //read account id, as a string
		catch (Exception ex) {}
		System.out.println("Please enter the amount of the withdrawal");
		try {withdrawAmount = fromConsole.readLine();}
		catch (Exception ex) {}
		
		command += " " + id + "." + withdrawAmount + "&"; //Put together data string to send to server
		client.handleMessageFromClientUI(command);
	}
	else if (command.equals("transfer"))
	{
		String idFrom = "";
		String transferAmount = "";
		String idTo = "";
		System.out.println("Please enter the account id from which the funds will originate");
		try {idFrom = fromConsole.readLine();}
		catch (Exception ex) {}
		System.out.println("Please enter the account id to which the funds will go");
		try {idTo = fromConsole.readLine();}
		catch (Exception ex) {}
		System.out.println("Please enter the amount of the transfer");
		try {transferAmount = fromConsole.readLine();}
		catch (Exception ex) {}
		
		command += " " + idFrom + "." + transferAmount + "&" + idTo + "#";
		
		client.handleMessageFromClientUI(command);
	}
	else if (command.equals("close"))
	{
		String answer = "";
		String closingAccountID = "";
		System.out.println("Please enter the name of the account that you wish to close");
		try{
		closingAccountID = fromConsole.readLine();
		} catch (IOException ex) {};
		do {
			System.out.println("Are you sure? y/n");
			try {
				answer = fromConsole.readLine();
			} catch (IOException ex) {};
		} while (!answer.toLowerCase().equals("y") && !answer.toLowerCase().equals("n"));
		command += " " + closingAccountID + ".";
		client.handleMessageFromClientUI(command);
	}
	else if (command.equals("view"))
	{
		String view = "";
		System.out.println("Please enter the name of the account that you with to view");
		try {
			view = fromConsole.readLine();
		} catch (IOException ex) {};
		command += " " + view + ".";
		client.handleMessageFromClientUI(command);
	}
	else if (command.startsWith("exit"))
	{
		client.handleMessageFromClientUI(command);
		System.out.println("Thank you, come again!");
		System.exit(0);
	}
  }
  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */
  public void display(String message) 
  {
	  
 System.out.println("> " + message);
		 
  }

  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */
  public static void main(String[] args) 
  {
    String host = "";

    try
    {
      host = args[0];
    }
    catch(ArrayIndexOutOfBoundsException e)
    {
      host = "localhost";
    }
    ClientConsole chat= new ClientConsole(host, DEFAULT_PORT);
    chat.accept();  //Wait for console data
  }
}
//End of ConsoleChat class
