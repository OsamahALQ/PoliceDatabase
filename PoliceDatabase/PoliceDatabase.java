//import javax.security.auth.PrivateCredentialPermission;
import java.util.Date;
import java.util.Arrays;
//import jdk.vm.ci.meta.Constant;

import org.graalvm.compiler.phases.graph.InferStamps;

public class PoliceDatabase{

public static Vehicle [] vehicles;
public int numVehicles =0;
public static Driver [] drivers;
public int numDrivers= 0;
public static Infraction [] infractions;
public int numInfractions = 0;


private static final int DRIVER= 2000;
private static final int VEHICLE= 1000;
private static final int INFRACTION= 800;


public PoliceDatabase(){ 
    drivers = new Driver [DRIVER];
    vehicles = new Vehicle[VEHICLE];
    infractions = new Infraction[ INFRACTION];
}
public void registerDriver(Driver aDriver){
    if (numDrivers < drivers.length){
    drivers[numDrivers] = aDriver;
    numDrivers +=1;
    }

}

public void registerVehicle(Vehicle aVehicle, String license){
    
        if (numVehicles < vehicles.length){
                vehicles[numVehicles]= aVehicle;
                for (int i = 0 ; i < drivers.length; i++){
                        if(drivers[i] != null){
                                if (license.equals(drivers[i].getLicense())){
                                vehicles[numVehicles].setOwner(drivers[i]);
                                }   
                        }       
                }
                numVehicles += 1;
        }   
}       
public void unregisterVehicle(String plate) {
        for (int i = 0; i < vehicles.length ; i++){
                if (plate.equals(vehicles[i].getPlate())){
                    numVehicles-=1;
                    for (int j = i ; j < vehicles.length ; j++){
                            if (j == vehicles.length - 1){
                                return;
                            }
                            vehicles[j] = vehicles[j+1];
                    }
                    return;
                }
            }

}

public void reportStolen(String plate){
    for (int i = 0; i < vehicles.length;i++){
        if(vehicles[i] != null){
                if(plate.equals(vehicles[i].getPlate())){
                        vehicles[i].setReportStolen();
                }
        }
    }
}

public void changeOwner(String plate, String license){
        for ( int i = 0 ; i < vehicles.length ; i++){
            if(vehicles[i] != null){
                if (plate.equals(vehicles[i].getPlate())){
                        for (int j = 0; j< drivers.length ; j++){
                                if(drivers[j] != null){
                                        if(license.equals(drivers[j].getLicense())){
                                                vehicles[i].setOwner(drivers[j]);
                                        }   
                                }
                        }
                }
            }
        }
}
public Infraction issueInfraction(String license, float amount, String description, Date date){
        Infraction o = new Infraction(amount ,description,date);
        infractions[numInfractions]=o;
        for (int i = 0 ; i < drivers.length ; i++){
                if(drivers[i] != null){
                        if (license.equals(drivers[i].getLicense())){
                                infractions[numInfractions].setDriver(drivers[i]);
                        }
                }

        }
        numInfractions +=1;
        return o;
}
public boolean hasOutstandingInfractions(Driver d){
        for (int i = 0 ; i < infractions.length ;i++){
                if (infractions[i] !=null){
                        if (d.equals(infractions[i].getDriver())){
                           if (infractions[i].getOutstanding()){
                                return true;
                                 }
                         }
                 }
        }
        return false;
}

public boolean shouldStopVehicle(String plate){
        for (int i = 0 ; i < vehicles.length ; i++){
                if (vehicles[i] !=null){
                if (plate.equals(vehicles[i].getPlate())){
                        Driver z = vehicles[i].getOwner();
                        boolean n =false;
                        for (int j = 0 ; j < infractions.length ; j++){
                                if (infractions[j] !=null){
                                if (z.equals(infractions[j].getDriver())){
                                        if (hasOutstandingInfractions(z)){
                                                n = true;
                                        }
                                }
                        }
                        }
                        if (vehicles[i].getReportStolen() || n){
                                return true;
                        }
                        else{
                                return false;
                        }

                }
                
        }
}
        return false;

}
public void showInfractionsFor(String license){
        int total = 0;
        for (int i =0; i < infractions.length ; i++){
                if (infractions[i] != null){
                        //System.out.println("***");
                        if (license.equals(infractions[i].getDriver().getLicense())){
                                //ystem.out.println("000");
                        System.out.println(infractions[i]);
                        total+=1;
                }
        }
}
        System.out.println("Total outstanding infractions = "+ total );
}

public Driver[] cleanDrivers(){
        int counter = 0;
        int counter2 = 1;
        int totalInfractions=0;
        //if (infractions != null){
        //        x[0] = infractions[0].getDriver();
        //}
        for (Infraction p : infractions){
                if (p != null){
                        totalInfractions++;
                }
        }
        for (int i = 0; i < infractions.length; i++){
                for (int j = counter2 ; j < infractions.length;j++){
                        if (infractions[i] != null && infractions[j] != null){
                        if (!(infractions[i].getDriver().getLicense()).equals(infractions[j].getDriver().getLicense()) && infractions[j] != infractions[j-1]){
                                //System.out.println(infractions[i].getDriver().getLicense());
                                counter ++;
                                counter2 ++;
                                break;
                        }else{
                                counter2++;
                                break;
                        }
                }
       
        }}
        counter2 = 1;
        Infraction [] badInfraction = new Infraction[counter];
        int infractionCounter=0;
        for (int i = 0; i < infractions.length; i++){

                for (int j = counter2 ; j < infractions.length;j++){

                        if (infractions[i] != null && infractions[j] != null){

                        if (!(infractions[i].getDriver().getLicense()).equals(infractions[j].getDriver().getLicense()) && infractions[j] != infractions[j-1]){
                                //System.out.println(infractions[i].getDriver().getLicense());
                                badInfraction[infractionCounter]=infractions[i];
                                //System.out.println("here are the bad infractions ;"+badInfraction[i].getDriver().getLicense());
                                infractionCounter+=1;
                                counter2 ++;
                                break;
                        }else{
                                counter2++;
                                break;
                        }}
                }
        }
        Driver[] x;
        //System.out.println(totalInfractions-counter);
        int xcounter = 0;
        boolean check;
        x = new Driver[totalInfractions - counter];
        for (int i = 0 ; i < drivers.length ; i++){
                check = true;
                

                if (drivers[i] !=null){
                        for (int j = 0; j < badInfraction.length;j++){
                                if (badInfraction[j]!=null){
                                        if(badInfraction[j].getDriver().getLicense().equals(drivers[i].getLicense())){
                                                check = false;
                                                break;
                                        }
                                }
                        }
                
                if (check){
                x[xcounter++]=drivers[i];
                }
        }
        }
        return x;
}
public void showInfractionReport(){
        System.out.println();
        boolean visited[] = new boolean[infractions.length]; 
        Arrays.fill(visited, false); 
        float total=0;
        for (int i = 0; i < infractions.length; i++) { 
                if(infractions[i] !=null ){
            if (visited[i] == true) 
                continue; 
            int count = 1; 
            for (int j = i + 1; j < infractions.length; j++) { 
                    if (count == 1){
                        if (!(infractions[i].getOutstanding())){
                                total = infractions[i].amount;}
                        }
                if(infractions[j] !=null ){
                if (infractions[i].getDriver().getLicense().equals( infractions[j].getDriver().getLicense())) { 
                    visited[j] = true; 
                    count++; 
                    if (!(infractions[j].getOutstanding())){
                        total += infractions[j].amount;
                }
                }
                } 
             }
            if (infractions[i].getDriver().name != null)
            System.out.println(String.format("%20s: %d infractions, total paid = $%.2f)",infractions[i].getDriver().name,count,total)); 
            total = 0;
            }
        } } 
public static PoliceDatabase example() { // Register all drivers and their vehicles
    PoliceDatabase pdb = new PoliceDatabase();

    pdb.registerDriver(new Driver("L1567-34323-84980", "Matt Adore",
            "1323 Kenaston St.", "Gloucester", "ON"));
    pdb.registerDriver(new Driver("L0453-65433-87655", "Bob B. Pins",
            "32 Rideau Rd.", "Greely", "ON"));
    pdb.registerDriver(new Driver("L2333-45645-54354", "Stan Dupp",
            "1355 Louis Lane", "Gloucester", "ON"));
    pdb.registerDriver(new Driver("L1234-35489-99837", "Ben Dover",
            "2348 Walkley Rd.","Ottawa", "ON"));
    pdb.registerDriver(new Driver("L8192-87498-27387", "Patty O'Lantern",
            "2338 Carling Ave.", "Nepean", "ON"));
    pdb.registerDriver(new Driver("L2325-45789-35647", "Ilene Dover",
            "287 Bank St.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1213-92475-03984", "Patty O'Furniture",
            "200 St. Laurant Blvd.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1948-87265-34782", "Jen Tull",
            "1654 Stonehenge Cres.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L0678-67825-83940", "Jim Class",
            "98 Oak Blvd.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L0122-43643-73286", "Mark Mywords",
            "3 Third St.", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L6987-34532-43334", "Bob Upandown",
            "434 Gatineau Way", "Hull", "QC"));
    pdb.registerDriver(new Driver("L3345-32390-23789", "Carrie Meehome",
            "123 Thurston Drive", "Kanata", "ON"));
    pdb.registerDriver(new Driver("L3545-45396-88983", "Sam Pull",
            "22 Colonel By Drive", "Ottawa", "ON"));
    pdb.registerDriver(new Driver("L1144-26783-58390", "Neil Down",
            "17 Murray St.", "Nepean", "ON"));
    pdb.registerDriver(new Driver("L5487-16576-38426", "Pete Reedish",
            "3445 Bronson Ave.", "Ottawa", "ON"));
    pdb.registerVehicle(new Vehicle("Honda", "Civic", 2015, "yellow", "W3EW4T"),
            "L0453-65433-87655");
    pdb.registerVehicle(new Vehicle("Pontiac","Grand Prix",2007,"dark green","GO SENS"),
            "L0453-65433-87655");
    pdb.registerVehicle(new Vehicle("Mazda", "RX-8", 2004, "white", "OH YEAH"),
            "L2333-45645-54354");
    pdb.registerVehicle(new Vehicle("Nissan","Altima",2017,"bergundy", "Y6P9O7"),
            "L1234-35489-99837");
    pdb.registerVehicle(new Vehicle("Saturn", "Vue", 2002, "white", "9R6P2P"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Honda", "Accord", 2018, "gray", "7U3H5E"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Chrysler", "PT-Cruiser", 2006, "gold", "OLDIE"),
            "L2325-45789-35647");
    pdb.registerVehicle(new Vehicle("Nissan", "Cube", 2010, "white", "5Y6K8V"),
            "L1948-87265-34782");
    pdb.registerVehicle(new Vehicle("Porsche", "959", 1989, "silver", "CATCHME"),
            "L0678-67825-83940");
    pdb.registerVehicle(new Vehicle("Kia", "Soul", 2018, "red", "J8JG2Z"),
            "L0122-43643-73286");
    pdb.registerVehicle(new Vehicle("Porsche", "Cayenne", 2014, "black", "EXPNSV"),
            "L6987-34532-43334");
    pdb.registerVehicle(new Vehicle("Nissan", "Murano", 2010, "silver", "Q2WF6H"),
            "L3345-32390-23789");
    pdb.registerVehicle(new Vehicle("Honda", "Element", 2008, "black", "N7MB5C"),
            "L3545-45396-88983");
    pdb.registerVehicle(new Vehicle("Toyota", "RAV-4", 2010, "green", "R3W5Y7"),
            "L3545-45396-88983");
    pdb.registerVehicle(new Vehicle("Toyota", "Celica", 2006, "red", "FUNFUN"),
            "L5487-16576-38426");

    return pdb;
}




}




