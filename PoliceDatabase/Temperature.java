/**
 * A Temperature object represents temperature (with a value and scale)
 * Assignment 2
 */

public class Temperature{


    private double temp;
    private Scale scale = Scale.CELSIUS;
    private String stringScale;
/** Initializes a temperature object with given value in Celcius
 *
 *  If the initial temperature is less than -273.15C then the temperature
 *  object will be initialized with -273.15C.
 *
 * @param temp is the initial temperature in Celsius.
 */
  
  public Temperature(double temp){
      if (temp < -273.15){
        this.temp = -273.15;
      }else{
      this.temp = temp;
      }
  }


/** Initializes a temperature object with given value using the specified scale
 * <par>
 * If the temperature is lower than absolute zero, in the given scale,
 * then set the temperature to absolute zero (in that scale).
 * <par>
 * Example: new Temperature(12.3, Scale.KELVIN)
 *
 * @param temp is the initial temperature
 * @param scale is the scale of initial temperature and must a constant
 *        defined in the Scale enum type.
 */
  public Temperature(double temp, Scale scale){
    this.scale = scale;
    if (scale == Scale.CELSIUS && temp < -273.15){
      this.temp = -273.15;
    }else if (scale == Scale.FAHRENHEIT && temp < -459.67){
      this.temp = -459.67;
    }else if (scale == Scale.KELVIN && temp < 0){
      this.temp = 0;
    }else{
      this.temp = temp;
  }
}


/** Initializes a temperature object with given value using the specified scale
 * <par>
 * If the temperature is lower than absolute zero, in the given scale,
 * then set the temperature to absolute zero (in that scale).
 * <par>
 * Example: each of the following will create the same temerature 12.3K
 *          new Temperature(12.3, "Kelvin")
 *          new Temperature(12.3, "k")
 *          new Temperature(12.3, "kel"
 *
 * @param temp is the initial temperature
 * @param scale is a the scale of initial temperature. As long as the input string
 *        can uniquely identify one of the three scales it is acceptable.
 *        Case is not important. Abbreviations are allowed.
 */
  public Temperature(double temp, String scale){
    
    stringScale= scale.toUpperCase();
    String kel = Scale.KELVIN.toString();
    String cel = Scale.CELSIUS.toString();
    String feh = Scale.FAHRENHEIT.toString();
    if (stringScale.charAt(0) == kel.charAt(0)){
    for (int i = 0 ; i < scale.length() ; i++){
      if (stringScale.charAt(i) == kel.charAt(i)){
        this.scale = Scale.KELVIN;
        if (temp < 0){
          this.temp = 0;
        }else{
          this.temp = temp;
      }
      }
    }}else if (stringScale.charAt(0) == cel.charAt(0)){
    for (int i = 0 ; i < scale.length() ; i++){
      if (stringScale.charAt(i) == cel.charAt(i) ){
        this.scale = Scale.CELSIUS;
        if (temp < -273.15){
          this.temp = -273.15;
      } else {this.temp = temp;}
    }else{
      return;
    }
  }}else if (stringScale.charAt(0) == feh.charAt(0)){
    for (int i = 0 ; i < scale.length() ; i++){
      if (stringScale.charAt(i) == feh.charAt(i)){
        this.scale = Scale.FAHRENHEIT;
        if (temp < -459.67){
          this.temp = -459.67;
      }else{this.temp = temp;}
    }else{
    this.temp = temp;  
    }
  }
}
}




/** getter for the scale. The output will always be one of the enum objects from Scale.
 *
 * @return the current scale of this object. 
 */
  public Scale getScale(){
    return scale;
  }

 /** getter for the temperature
  *
  * @return the temperature of the object using the current scale
  */
  public double getTemp(){
    return temp;
  }


  /** setter for scale
  *
  * @param scale is the new scale of the temperature and must be
  *        a constant from the Scale enum type.
  */
  public void setScale(Scale scale){
      this.scale= scale;
  }



  /** setter for temperature
  *  <par>
  * If the temperature is lower than absolute zero, in the given scale,
  * then sets the temperature to absolute zero (in that scale).
  *
  * @param temp is the new temperature
  * @param scale is the scale of the new temperature. It must be
  *        a constant from the Scale enum type.
  */
  public void setTemp(double temp, Scale scale){
      this.temp = temp;
      this.scale = scale;
  }










/* ------------------------------------------------- */
/* ------------------------------------------------- */
/* do not change anything below this                 */
/* ------------------------------------------------- */
/* ------------------------------------------------- */



  /** String representation of a temperature object    */
  @Override
  public String toString(){
    return "" + this.getTemp() + this.getScale().name().charAt(0);
  }





    

}