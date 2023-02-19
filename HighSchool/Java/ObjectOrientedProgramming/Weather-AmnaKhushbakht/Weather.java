public class Weather {

  String weatherCondition;
  double temperature;

  // first constructor
  public Weather() {
    weatherCondition = "Sunny";
    temperature = 20.00;
  }

  // second constructor
  public Weather(String weather, double temp) {
    // validity check
    if (weather.matches("[a-zA-Z]+")) {
      weatherCondition = weather;
    } else {
      weatherCondition = "Sunny";
    }
    temperature = temp;
  }

  // accessor for weatherCondition
  public String getWeatherCondition() {
    return weatherCondition;
  }

  // accessor for temperature
  public double getTemperature() {
    return temperature;
  }

  // mutator for weatherCondition
  public void setWeatherCondition(String weather) {
    // validity check
    if (weather.matches("[a-zA-Z]+")) {
      weatherCondition = weather;
    } else {
      weatherCondition = "Sunny";
    }
  }

  // mutator for temperature
  public void setTemperature(double temp) {
    temperature = temp;
  }

  // convert C to F
  public double fahrenheitTemperature() {
    double fahrenheitTemp = (temperature * 9 / 5) + 32;
    return fahrenheitTemp;
  }

  // String output
  public String toString() {
    return "The current weather is " + weatherCondition + " and it is " + String.format("%.02f", temperature)
        + " degrees celsius";

  }
}