
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3194 modeling language!*/



// line 45 "model.ump"
// line 58 "model.ump"
public class User
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String name;
  private String sin;
  private int ID;
  private String sex;
  private String birthday;
  private int phoneNumber;

  //User Associations
  private BankServer bankServer;

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
    boolean didAddBankServer = setBankServer(aBankServer);
    if (!didAddBankServer)
    {
      throw new RuntimeException("Unable to create user due to bankServer");
    }
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
            "  " + "bankServer = "+(getBankServer()!=null?Integer.toHexString(System.identityHashCode(getBankServer())):"null")
     + outputString;
  }
}