package metallica.trade.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.forecast.dao.impl.ForecastDaoImpl;
import com.forecast.model.Daily;
import com.forecast.model.DailyDataPoint;
import com.forecast.model.Forecast;
import com.forecast.model.Latitude;
import com.forecast.model.Longitude;
import com.forecast.service.ForecastService;
import com.forecast.service.impl.ForecastServiceImpl;

@RunWith(SpringRunner.class)
public class ForecastServiceIntegrationTest {

	 
    @TestConfiguration
    static class TradeServiceTestContextConfiguration {
  
        @Bean
        public ForecastService forecastService() {
            return new ForecastServiceImpl();
        }
    }
 
    @Autowired
    private ForecastService forecastService;
 
    @MockBean
    private ForecastDaoImpl forecastDaoImpl;
 
    @Before
    public void setUp() {
        Forecast forecast = new Forecast();
        
        //setting sample data
        forecast.setLongitude(new Longitude(40));
        forecast.setLatitude(new Latitude(60));
        
        Daily daily = new Daily();
        DailyDataPoint dailyDataPoint = new DailyDataPoint();
        dailyDataPoint.setTemperatureLow(20.00);
        dailyDataPoint.setTemperatureHigh(30.00);
        List<DailyDataPoint> dailyDataPoints = new ArrayList<DailyDataPoint>();
        dailyDataPoints.add(dailyDataPoint);
        daily.setData(dailyDataPoints);
        forecast.setDaily(daily);
        
        List<Forecast> forecasts = new ArrayList<Forecast>();
        forecasts.add(forecast);
        Mockito.when(forecastDaoImpl.findAll())
          .thenReturn(forecasts);
    }
    
    @Test
    public void testNoOfForecasts() {
        List<Forecast> forecasts = forecastService.findAll();
        int noOfforecasts = forecasts.size();
        assertEquals(1, noOfforecasts);
    }
    
    @Test
    public void testGeoCoordinates() {
        List<Forecast> forecasts = forecastService.findAll();
        Forecast forecast = forecasts.get(0);
        
        // GeoCoordinates
        assertEquals(new Double(40), forecast.getLongitude().value());
        assertEquals(new Double(40), forecast.getLongitude().value());
    }
    
    @Test
    public void testForecastsHighTemperature() {

        List<Forecast> forecasts = forecastService.findAll();
        Forecast forecast = forecasts.get(0);
        
        assertEquals(new Double(30.00), forecast.getDaily().getData().get(0).getTemperatureHigh());
    }
    
    @Test
    public void testForecastsLowTemperature() {

        List<Forecast> forecasts = forecastService.findAll();
        Forecast forecast = forecasts.get(0);
        assertEquals(new Double(20.00), forecast.getDaily().getData().get(0).getTemperatureLow());
    }

    
}