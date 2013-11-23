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
			 handleCommand(message, client);
	  }
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
  private void handleCommand(String command, ConnectionToClient client) //New method created to handle server commands
  {	
	  
	  int amount = 5;
	  System.out.println(command);
		 if (command.startsWith("exit")) 
		 {
			 sendToAllClients("Thank You, Come Again!");
			 System.exit(0);  //Shuts down the server
		 }
		 
		 else if (command.startsWith("~"))  //Commands for loging in
		 {
			 String fileName = ("Accounts.txt");
			 command = command.substring(1);
			 try{     
		          PrintWriter pw = new PrintWriter(new FileOutputStream(fileName, true)); 
		        pw.println(command);  
		        pw.close(); 
		        String accountID=command;
				accountID=accountID.substring(0,accountID.indexOf(' '));
				 File file = new File(accountID +".txt");
				 file.createNewFile();
				 System.out.println("New file created");
		    }
	    catch(IOException iox) {
	    System.out.println("Problem writing " + fileName);
	    }
			 
			 
			 
		 }
		 
		 else if (command.startsWith("&"))  //Commands for loging in
		 {
			 String fileName = ("Accounts.txt");
			 command= command.substring(1);
			 try{
			 BufferedReader br = new BufferedReader(new FileReader(fileName));
			 String line;
			 boolean found=false;
			 while ((line = br.readLine()) != null) {
				 System.out.println(line+" Line input");
				 System.out.println(command+" command input");
			    if(line.equals(command))
			    	{
			    	System.out.println("test");
			    	found=true;
			    	break;
			    	}
			 }
			 br.close();
			 if(!found)
			 {
				 client.sendToClient("Error:Wrong password or login");
				 close();
			 }
			 else
			 {
				 client.sendToClient("Login succesful");
			 }
			 
		 }
		    catch(IOException iox) {
		    System.out.println("Problem reading " + fileName);
		    }
			 
		 }
	 	
		 else if (command.startsWith("new"))
		 {
			 
			 String interestRate;
			 String yearlyFee;
			 
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String accountType = command.substring(a, b);
			 	
			  	if (accountType.equals("c"))
			  	{
			  		interestRate = "3%";
			  		yearlyFee = "0$";
			  	}
			  	else
			  	{
			  		interestRate= "5%";
			  		yearlyFee = "25$";
			  	}
			  	
			  	
				a = command.indexOf('.') + 1;
			  	b = command.indexOf('&');
			  	String initial = command.substring(a, b);
			  	amount = Integer.parseInt(initial);
			  	
			  	Savings sAccount = new Savings(amount, "001", interestRate, yearlyFee); //construct new savings account instance
		}
		    
		 else if (command.startsWith("access"))
		 {
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String accessAccountID = command.substring(a, b);    //read account ID to be accessed
		 }
		 
		 else if (command.startsWith("close")) 
		 {
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String closedAccountID = command.substring(a, b);    //read account ID to be closed
		 }
		 
		 else if (command.startsWith("deposit"))
		 {
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String accountID = command.substring(a, b);    //read account ID to be deposited into
				a = command.indexOf('.') + 1;
			  	b = command.indexOf('&');
			  	String deposit = command.substring(a, b);
			  	int depositAmount = Integer.parseInt(deposit);  //read deposit amount to deposit
		 }
		 
		 else if (command.startsWith("withdraw"))
		 {
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String accountID = command.substring(a, b);    //read account ID to be deposited into
				a = command.indexOf('.') + 1;
			  	b = command.indexOf('&');
			  	String withdrawal = command.substring(a, b);
			  	int withdrawAmount = Integer.parseInt(withdrawal);  //read withdrawal amount to withdraw
		 }
		 else if (command.startsWith("transfer"))
		 {
				int a = command.indexOf(' ') + 1;
			  	int b = command.indexOf('.');
			  	String fromAccountID = command.substring(a, b);    //read account ID to be deposited into
				a = command.indexOf('.') + 1;
			  	b = command.indexOf('&');
			  	String transfer = command.substring(a, b);
			  	int transferAmount = Integer.parseInt(transfer);  //read transfer amount to transfer
				a = command.indexOf('&') + 1;
			  	b = command.indexOf('#');
			  	String toAccountID = command.substring(a, b); //read destination account information
		 }
  }
  
  
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
