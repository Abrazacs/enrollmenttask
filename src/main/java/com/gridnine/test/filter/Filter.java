package com.gridnine.test.filter;

import com.gridnine.test.Flight;

import java.util.List;

public interface Filter {

    List<Flight> toFilter (List<Flight> flights);
}
