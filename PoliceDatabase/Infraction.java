import java.util.Date;


public class Infraction{



//attributes

 float amount;
 String description;
 Date dateIssued;
 boolean outstanding;
 Driver driver;

//constructors

public Infraction(float amount, String description, Date dateIssued){
    this.amount = amount;
    this.description = description;
    this.dateIssued = dateIssued;
    driver = new Driver();
    outstanding = true;
}
public Infraction(){
    this(null,null,null);
}
//methods
public String toString(){
    String outstand;
    if(outstanding == true){
        outstand = "[OUTSTANDING]";

    }else{
        outstand = "[PAID IN FULL]";
    }
    //return String.format("%d infraction on %tc %s" ,amount,dateIssued,outstand);
    return String.format("$%.2f Infraction on %tc %s",amount,dateIssued,outstand);
}

public void setDriver(Driver aDriver){
    this.driver = aDriver;
}
public Driver getDriver(){
    return this.driver;
}
public boolean getOutstanding(){
    return outstanding;
}
public void pay(){
    outstanding = false;
}
}