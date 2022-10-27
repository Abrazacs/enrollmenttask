package com.gridnine.test.filter;

import com.gridnine.test.Flight;
import com.gridnine.test.Segment;

import java.util.ArrayList;
import java.util.List;

public class ArrivalBeforeDepartureFilter implements Filter{

    private List<Flight> filteredList;

    @Override
    public List<Flight> toFilter(List<Flight> flights) {
        filteredList = new ArrayList<>();
        for (Flight flight: flights) {
            for (Segment segment:flight.getSegments()){
                if(!segment.getArrivalDate().isBefore(segment.getDepartureDate())){
                    filteredList.add(flight);
                    break;
                }
            }
        }
        return filteredList;
    }
}
