package ru.mosquiito.services.hotel;

import ru.mosquiito.domain.Hotel;
import ru.mosquiito.domain.Tour;

import java.util.List;

public interface HotelDataAccess {
    List<Hotel> getAllByTour(Tour tour);
}
