package com.gridnine.test.filter;

import com.gridnine.test.Flight;
import com.gridnine.test.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DepartureBeforeCurrentTimeFilter implements Filter{

    private List<Flight> filteredList;

    @Override
    public List<Flight> toFilter(List<Flight> flights) {
        filteredList = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now();

        for (Flight flight: flights) {
            for (Segment segment:flight.getSegments()){
                if(!segment.getDepartureDate().isBefore(currentTime)){
                    filteredList.add(flight);
                    break;
                }
            }
        }
        return filteredList;
    }
}
