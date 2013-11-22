
public class User
{

	  //User Attributes
	  private String name;
	  private String sin;
	  private int ID;
	  private String sex;
	  private String birthday;
	  private int phoneNumber;
	/*
*/
  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aName, String aSin, int aID, String aSex, String aBirthday, int aPhoneNumber, BankServer aBankServer)
  {
    name = aName;
    sin = aSin;
    ID = aID;
    sex = aSex;
    birthday = aBirthday;
    phoneNumber = aPhoneNumber;
    //boolean didAddBankServer = setBankServer(aBankServer);
    //if (!didAddBankServer)
    //{
    //  throw new RuntimeException("Unable to create user due to bankServer");
    //}
  }

  
  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setSin(String aSin)
  {
    boolean wasSet = false;
    sin = aSin;
    wasSet = true;
    return wasSet;
  }

  public boolean setID(int aID)
  {
    boolean wasSet = false;
    ID = aID;
    wasSet = true;
    return wasSet;
  }

  public boolean setSex(String aSex)
  {
    boolean wasSet = false;
    sex = aSex;
    wasSet = true;
    return wasSet;
  }

  public boolean setBirthday(String aBirthday)
  {
    boolean wasSet = false;
    birthday = aBirthday;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(int aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getSin()
  {
    return sin;
  }

  public int getID()
  {
    return ID;
  }

  public String getSex()
  {
    return sex;
  }

  public String getBirthday()
  {
    return birthday;
  }

  public int getPhoneNumber()
  {
    return phoneNumber;
  }

  /*public BankServer getBankServer()
  //{
 //   return bankServer;
 // }

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
      existingBankServer.removeUser(this);
    }
    bankServer.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    BankServer placeholderBankServer = bankServer;
    this.bankServer = null;
    placeholderBankServer.removeUser(this);
  }
*/

  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "sin" + ":" + getSin()+ "," +
            "ID" + ":" + getID()+ "," +
            "sex" + ":" + getSex()+ "," +
            "birthday" + ":" + getBirthday()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  "+ outputString;
  }

}