public class Driver{


//attributes
 String license;
 String name;
 String street;
 String city;
 String province;

//constructors
public Driver(){
    this(null,null,null,null,null);
}  
public Driver(String license, String name, String street, String city, String province){
    this.license = license;
    this.name = name;
    this.street = street; 
    this.city = city;
    this.province = province;
}


 public String getName(){
     return name;
 }
 public String getStreet(){
     return street;
 }
 public String getCity(){
     return city;
 }
 public String getProvince(){
     return province;
 }
public String getLicense(){
    return license;
}
//methods
public String toString(){
    return license + " " + name + " living at " + street + "," + city + "," + province;
}



    
}