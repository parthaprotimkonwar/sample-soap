package controllers.rest;

import java.text.SimpleDateFormat;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.cdyne.ws.weatherws.Forecast;
import com.cdyne.ws.weatherws.ForecastReturn;
import com.cdyne.ws.weatherws.GetCityForecastByZIP;
import com.cdyne.ws.weatherws.GetCityForecastByZIPResponse;
import com.cdyne.ws.weatherws.Temp;

public class WebserviceInteraction extends WebServiceGatewaySupport {

	public GetCityForecastByZIPResponse getCityForecastByZip(String zipCode) {

		GetCityForecastByZIP request = new GetCityForecastByZIP();
		request.setZIP(zipCode);

		System.out.println("Requesting forecast for " + zipCode);
		GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						"http://wsf.cdyne.com/WeatherWS/Weather.asmx",
						request,
						new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

		return response;
	}

	public void printResponse(GetCityForecastByZIPResponse response) {

		ForecastReturn forecastReturn = response.getGetCityForecastByZIPResult();

		if (forecastReturn.isSuccess()) {
			System.out.println("Forecast for " + forecastReturn.getCity() + ", " + forecastReturn.getState());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (Forecast forecast : forecastReturn.getForecastResult().getForecast()) {

				Temp temperature = forecast.getTemperatures();
				System.out.println(String.format("New %s %s %s°-%s°", format.format(forecast.getDate().toGregorianCalendar().getTime()),
						forecast.getDesciption(), temperature.getMorningLow(), temperature.getDaytimeHigh()));
			}
		} else {
			System.out.println("No forecast received");
		}
	}

}
