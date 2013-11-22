
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3194 modeling language!*/



// line 38 "model.ump"
// line 70 "model.ump"
public class Savings extends Account
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Savings Attributes
  private int interestRate;
  private int yearlyFee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Savings(int aBalance, int aId, String aDateCreated, BankServer aBankServer, int aInterestRate, int aYearlyFee)
  {
    super(aBalance, aId, aDateCreated, aBankServer);
    interestRate = aInterestRate;
    yearlyFee = aYearlyFee;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setInterestRate(int aInterestRate)
  {
    boolean wasSet = false;
    interestRate = aInterestRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setYearlyFee(int aYearlyFee)
  {
    boolean wasSet = false;
    yearlyFee = aYearlyFee;
    wasSet = true;
    return wasSet;
  }

  public int getInterestRate()
  {
    return interestRate;
  }

  public int getYearlyFee()
  {
    return yearlyFee;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "interestRate" + "=" + (getInterestRate() != null ? !getInterestRate().equals(this)  ? getInterestRate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "yearlyFee" + "=" + (getYearlyFee() != null ? !getYearlyFee().equals(this)  ? getYearlyFee().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}