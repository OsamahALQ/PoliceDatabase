public class Vehicle{

//attributes
String make;
 String model;
 int year;
 String color;
 String plate;
 Driver owner;
 boolean reportedStolen;


//constructors
public Vehicle(String make, String model, int year, String color, String plate){
    this.make = make;
    this.model = model;
    this.year = year;
    this.color = color;
    this.plate = plate;
    reportedStolen = false;
}
public Vehicle(){
    this(null,null,null,null,null);
}

public String getPlate(){
    return plate;


}
public void setOwner(Driver newOwner){
    owner = newOwner;
}
public Driver getOwner(){
    return owner;
}
public void setReportStolen(){
    reportedStolen = true;
}
public boolean getReportStolen(){
    return reportedStolen;
}
//methods
public String toString() {
    return "A " + color + " " + year +" "+ make +" "+ model + " with plate " + plate ;
}

    
}