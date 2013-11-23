import java.util.*;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3194 modeling language!*/



// line 38 "model.ump"
// line 70 "model.ump"
public class Savings //extends Account
{
 
  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Savings Attributes
  private String interestRate;
  private String yearlyFee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Savings(int aBalance, String aId, String aInterestRate, String aYearlyFee)
  {
    //super(aBalance, aId, aDateCreated, aBankServer);
    interestRate = aInterestRate;
    yearlyFee = aYearlyFee;
  }

  //------------------------
  // INTERFACE
  //------------------------


public boolean setInterestRate(String aInterestRate)
  {
    boolean wasSet = false;
    interestRate = aInterestRate;
    wasSet = true;
    return wasSet;
  }

  public boolean setYearlyFee(String aYearlyFee)
  {
    boolean wasSet = false;
    yearlyFee = aYearlyFee;
    wasSet = true;
    return wasSet;
  }

  public String getInterestRate()
  {
    return interestRate;
  }

  public String getYearlyFee()
  {
    return yearlyFee;
  }
/*
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
  */
}