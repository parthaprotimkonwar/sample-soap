package com.cdyne.ws.weatherws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.1
 * 2015-12-15T21:40:48.049+05:30
 * Generated source version: 2.6.1
 * 
 */
@WebService(targetNamespace = "http://ws.cdyne.com/WeatherWS/", name = "WeatherHttpPost")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface WeatherHttpPost {

    /**
     * Gets Information for each WeatherID
     */
    @WebMethod(operationName = "GetWeatherInformation")
    @WebResult(name = "ArrayOfWeatherDescription", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    public ArrayOfWeatherDescription getWeatherInformation();

    /**
     * Allows you to get your City's Weather, which is updated hourly. U.S. Only
     */
    @WebMethod(operationName = "GetCityWeatherByZIP")
    @WebResult(name = "WeatherReturn", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    public WeatherReturn getCityWeatherByZIP(
        @WebParam(partName = "ZIP", name = "ZIP", targetNamespace = "")
        java.lang.String zip
    );

    /**
     * Allows you to get your City Forecast Over the Next 7 Days, which is updated hourly. U.S. Only
     */
    @WebMethod(operationName = "GetCityForecastByZIP")
    @WebResult(name = "ForecastReturn", targetNamespace = "http://ws.cdyne.com/WeatherWS/", partName = "Body")
    public ForecastReturn getCityForecastByZIP(
        @WebParam(partName = "ZIP", name = "ZIP", targetNamespace = "")
        java.lang.String zip
    );
}
