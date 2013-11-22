// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

import java.io.*;
import java.util.*;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract 
 * superclass in order to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class BankServer extends AbstractServer 
{
  //Class variables *************************************************
  
  /**
   * The default port to listen on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Constructors ****************************************************
  
  /**
   * Constructs an instance of the echo server.
   *
   * @param port The port number to connect on.
   */
  public BankServer(int port) 
  {
    super(port);
  }

  
  //Instance methods ************************************************
  
  /**
   * This method handles any messages received from the client.
   *
   * @param msg The message received from the client.
   * @param client The connection from which the message originated.
   */
  public void handleMessageFromClient
    (Object msg, ConnectionToClient client)
  {
	  String message = msg.toString();
	  String login = "";
	  
	  if (message.startsWith("#login")) //Checks for the login string
	  {
		  if (client.getInfo("loginID") == null)  //Checks if the Info for this client is null
		  {
			int loginA = message.indexOf('<') + 1;
		  	int loginB = message.indexOf('>');
		  	login = message.substring(loginA, loginB);
		  	client.setInfo("loginID",  login);  //Sets the login info to the login in the string
		  }
		  else
		  {
			  try
			  {
			  client.sendToClient("You are already logged in!");
			  }
			  catch (IOException ex)
			  {
			  }
		  return;
		  }
	  }
	  
	  else if (client.getInfo("loginID") == null)
			  {
		  		try
		  		{
		  		client.sendToClient("Error: You need to login before you can chat. ");
		  		client.close();
		  		}
		  		catch (IOException ex)
		  		{	
		  		}
			  }
	  else 
	  {	  // send to command handling
			 handleCommand(message, login);
	  }
	  ///////////////////////////////////////////ADDED FOR E51B     MA/ND
    System.out.println("Message received: " + client.getInfo("loginID") + ": " + msg);
  }
  
   
  /**
   * This method overrides the one in the superclass.  Called
   * when the server starts listening for connections.
   */
  protected void serverStarted()
  {
    System.out.println
      ("BankServer is now listening for client" +
      		" connections on port " + getPort());
  }
  
  /**
   * This method overrides the one in the superclass.  Called
   * when the server stops listening for connections.
   */
  protected void serverStopped()
  {
    System.out.println
      ("Server has stopped listening for connections.");
  }
  
  /////////////////////////Changed for E49 M.A/N.D
  
  protected void clientConnected(ConnectionToClient client){   //Override Hook method Abstract Server
	  System.out.println("Client has Connected");  //Message for when client Connects
  }
  
  synchronized protected void clientDisconnected(ConnectionToClient client) {   //Override Hook method Abstract Server
	  System.out.println("Client has Disconnected"); //Message for when client disconnects
  }
  
  synchronized protected void clientException(ConnectionToClient client, Throwable exception) {   //Override Hook method Abstract Server
	  System.out.println("Client has Disconnected"); //Message for when client disconnects
  }
  
  
  ////////////////////////Changed for E49C M.A/N.D
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of 
   * the server instance (there is no UI in this phase).
   *
   * @param args[0] The port number to listen on.  Defaults to 5555 
   *          if no argument is entered.
   */

  
  /////////////////////////////////////////////Added 50C      MA/ND
  private void handleCommand(String command, String loginID) //New method created to handle server commands
  {	
	  System.out.println(command);
	  BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));   // Creates new reader for the input
	  String answer = "";
	  Date dt = new Date();
		 if (command.toLowerCase().equals("exit")) 
		 {
			 sendToAllClients("Thank You, Come Again!");
			 System.exit(0);  //Shuts down the server
		 }
	 	
		 else if (command.equals("new"))
		 {
			 do{
				 try{
				 System.out.println("Please enter S (savings) or C (chequings)");
				 answer = fromConsole.readLine().toLowerCase();
				 }
						 catch (IOException ex) //If an error occurs
				 {
				 }
			 } 
			 while (!answer.equals("s") || !answer.equals("c"));
	
			 if (answer.equals("s"))
			 {
				 Savings save1 = new Savings(0, "0001", dt, loginID, 5, 50); //create savings account
				 System.out.println("Savings account created. Your balance is");
				 System.out.println("currently 0$. Please select one of the following:");
				 System.out.println("Deposit, Withdraw, Transfer, Close ");
				 Boolean active = true;
				 do{
				 	try {
					String response = fromConsole.readLine();
				 	}
			     	catch (IOException ex) //If an error occurs
				 	{
				 	};	 
				 	active = false;
				 }
				 while (active == true);
			 }
		}
		 
		 else if (command.equals("&#close"))
		 {
			 stopListening();
			 try{
			 close();  //Closes the server
			 }
			 catch (IOException ex) //If an error occurs
			    {
			      System.out.println ("Unexpected error while trying to close!"); 
			    }
			 
		 }
		 
		 else if (command.equals("&#setport")) 
		 {
			 System.out.println("Please enter new port");
			 try{
				  int newPort; // New String variable used to store the value of the BufferedReader
				  System.out.println("Enter New host(IP)");
				  newPort = Integer.parseInt(fromConsole.readLine());  //Reads the line and sets it to the new variable
				  
				  setPort(newPort);
				  System.out.println("The new port is"+newPort);
				  }
				  catch (Exception ex) //If an error occurs
				    {
				      System.out.println ("Unexpected error while reading from console!"); 
				    }
		 }
		 
		 else if (command.equals("&#start"))
		 {
			 if (!isListening())  //Checks if the server is currently listening
			 {
				 try{
					 listen();  //Makes the server start listening
					 }
					 catch (IOException ex) //If an error occurs
					    {
					      System.out.println ("Unexpected error while trying to start listening!"); 
					    }
			 }
		 }
		 
		 else if (command.equals("&#getport"))
		 {
			 int port;
			 port = getPort();  //Gets the current port
			 System.out.println("The current port is:"+port); //Prints the current port
		 }
		 
  }
  /////////////////////////////////////////////////////ADDED for E50C MA/ND
  
  
  public static void main(String[] args) 
  {
    int port = 0; //Port to listen on

    try
    {
      port = Integer.parseInt(args[0]); //Get port from command line
    }
    catch(Throwable t)
    {
      port = DEFAULT_PORT; //Set port to 5555
    }
	
    BankServer sv = new BankServer(port);
    
    try 
    {
      sv.listen(); //Start listening for connections
    } 
    catch (Exception ex) 
    {
      System.out.println("ERROR - Could not listen for clients!");
    }
  }
}
//End of BankServer class
