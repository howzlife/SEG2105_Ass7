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
			  }
			  while (!p1.equals(password));
		  }
		  else 
		  {
			  System.out.println("Please enter your password");
			  password = fromConsole.readLine();
		  }
		  
		  System.out.println("Welcome! Select one of the following");
		  System.out.println("***********************************");
		  System.out.println("***********************************");
		  System.out.println("**       New - Access - Close -  **");
		  System.out.println("**       Deposit - Withdraw -    **");
		  System.out.println("********  Transfer - Exit *********");
		  System.out.println("***********************************");
		  System.out.println("***********************************");
		  // Asks the user for the port number
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
        message = fromConsole.readLine();
        client.handleMessageFromClientUI(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
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
    int port = 0;  //The port number

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
