public class Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private int balance;
  private int id;
  private String dateCreated;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(int aBalance, int aId, String aDateCreated, BankServer aBankServer)
  {
    balance = aBalance;
    id = aId;
    dateCreated = aDateCreated;
    //boolean didAddBankServer = setBankServer(aBankServer);
    //if (!didAddBankServer)
    //{
     // throw new RuntimeException("Unable to create account due to bankServer");
   // }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBalance(int aBalance)
  {
    boolean wasSet = false;
    balance = aBalance;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(int aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setDateCreated(String aDateCreated)
  {
    boolean wasSet = false;
    dateCreated = aDateCreated;
    wasSet = true;
    return wasSet;
  }

  public int getBalance()
  {
    return balance;
  }

  public int getId()
  {
    return id;
  }

  public String getDateCreated()
  {
    return dateCreated;
  }
/*
  public BankServer getBankServer()
  {
    return bankServer;
  }

  public boolean setBankServer(BankServer aBankServer)
  {
    boolean wasSet = false;
    if (aBankServer == null)
    {
      return wasSet;
    }

    BankServer existingBankServer = bankServer;
    bankServer = aBankServer;
    if (existingBankServer != null && !existingBankServer.equals(aBankServer))
    {
      existingBankServer.removeAccount(this);
    }
    bankServer.addAccount(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BankServer placeholderBankServer = bankServer;
    this.bankServer = null;
    placeholderBankServer.removeAccount(this);
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "balance" + ":" + getBalance()+ "," +
            "dateCreated" + ":" + getDateCreated()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "id" + "=" + (getId() != null ? !getId().equals(this)  ? getId().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + ""+(getBankServer()!=null?int.toHexString(System.identityHashCode(getBankServer())):"null")
     + outputString;
  }
  */
  
}