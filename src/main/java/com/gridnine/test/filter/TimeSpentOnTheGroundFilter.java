package com.gridnine.test.filter;

import com.gridnine.test.Flight;
import com.gridnine.test.Segment;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class TimeSpentOnTheGroundFilter implements Filter {

    private List<Flight> filteredList;

    private long seconds;

    public TimeSpentOnTheGroundFilter (long seconds){
        this.seconds = seconds;
    }

    @Override
    public List<Flight> toFilter(List<Flight> flights) {
        filteredList = new ArrayList<>();
        long timeOnTheGround = 0L;
        for (Flight flight: flights) {
            List<Segment> segments = flight.getSegments();
            if(segments.size()==1){
                filteredList.add(flight);
            }
            else {
                for (int i = 1; i <segments.size() ; i++) {
                    timeOnTheGround = timeOnTheGround + segments.get(i).getDepartureDate().toEpochSecond(ZoneOffset.UTC)-segments.get(i-1).getArrivalDate().toEpochSecond(ZoneOffset.UTC);
                }
                if(timeOnTheGround<=seconds){
                    filteredList.add(flight);
                    timeOnTheGround = 0L;
                }
            }

        }
        return filteredList;
    }

    public void setSeconds(long seconds){
        this.seconds = seconds;
    }

    public long getSeconds(){
        return seconds;
    }
}
