package com.gridnine.test;

import com.gridnine.test.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.test.filter.DepartureBeforeCurrentTimeFilter;
import com.gridnine.test.filter.Filter;
import com.gridnine.test.filter.TimeSpentOnTheGroundFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long timeLimitOnTheGround = 7200L;
        List<Flight> flights = FlightBuilder.createFlights();
        List<Filter> filters = new ArrayList<>();
        filters.add(new DepartureBeforeCurrentTimeFilter());
        filters.add(new ArrivalBeforeDepartureFilter());
        filters.add(new TimeSpentOnTheGroundFilter(timeLimitOnTheGround));

        System.out.println("Все полеты");
        flights.forEach(System.out::println);
        System.out.println();

        int i = 1;
        for (Filter f: filters) {
            System.out.println("Фильтр № "+f.getClass().getSimpleName());
            f.toFilter(flights).forEach(System.out::println);
            System.out.println();
            i++;
        }
        Arrays.bina

    }
}
