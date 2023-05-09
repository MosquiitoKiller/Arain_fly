package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Hotel;
import ru.mosquiito.domain.Tour;
import ru.mosquiito.repositories.HotelRepository;
import ru.mosquiito.services.hotel.HotelDataAccess;

import java.util.List;

public class HotelData implements HotelDataAccess {

    @Inject
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllByTour(Tour tour) {
        return hotelRepository.findByTour(tour);
    }
}
