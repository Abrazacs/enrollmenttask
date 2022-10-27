import com.gridnine.test.Flight;
import com.gridnine.test.FlightBuilder;
import com.gridnine.test.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.test.filter.DepartureBeforeCurrentTimeFilter;
import com.gridnine.test.filter.Filter;
import com.gridnine.test.filter.TimeSpentOnTheGroundFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {

    private List<Flight> flights;

    @BeforeEach
    void setUp(){
        flights = new ArrayList<>();
    }


    @org.junit.jupiter.api.Test
    void testArrivalBeforeDepartureFilter(){
        Filter filter = new ArrivalBeforeDepartureFilter();
        // arrival before departure
        flights.add(FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().minusDays(3)));
        Assertions.assertTrue(filter.toFilter(flights).isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testArrivalBeforeDepartureFilter2(){
        Filter filter = new ArrivalBeforeDepartureFilter();
        // arrival after departure
        flights.add(FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        Assertions.assertFalse(filter.toFilter(flights).isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testArrivalBeforeDepartureFilter3(){
        Filter filter = new ArrivalBeforeDepartureFilter();
        // arrival == departure
        flights.add(FlightBuilder.createFlight(LocalDateTime.now(), LocalDateTime.now()));
        Assertions.assertFalse(filter.toFilter(flights).isEmpty());
    }

    @org.junit.jupiter.api.Test
    void testDepartureBeforeCurrentTimeFilter(){
        Filter filter = new DepartureBeforeCurrentTimeFilter();
        flights.add(FlightBuilder.createFlight(LocalDateTime.now().minusDays(2),LocalDateTime.now().plusDays(2)));
        flights.add(FlightBuilder.createFlight(LocalDateTime.now(),LocalDateTime.now().plusDays(2)));
        Assertions.assertEquals(1, filter.toFilter(flights).size());
    }

    @org.junit.jupiter.api.Test
    void testTimeSpendOnTheGroundFilter(){
        long timeLimitOnTheGround = 7200L;
        Filter filter = new TimeSpentOnTheGroundFilter(timeLimitOnTheGround);
        flights.add((FlightBuilder.createFlight(
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusDays(2),
                LocalDateTime.now().plusDays(2).plusHours(3), LocalDateTime.now().plusDays(4))));
        flights.add(FlightBuilder.createFlight(LocalDateTime.now(),LocalDateTime.now().plusDays(2)));
        Assertions.assertEquals(1, filter.toFilter(flights).size());
    }

}
