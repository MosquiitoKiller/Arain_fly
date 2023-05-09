package ru.mosquiito.data;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Tour;
import ru.mosquiito.repositories.TourRepository;
import ru.mosquiito.services.hotel.HotelTourDataAccess;
import ru.mosquiito.services.order.OrderTourDataAccess;

public class TourData implements OrderTourDataAccess, HotelTourDataAccess {

    @Inject
    private TourRepository tourRepository;


    @Override
    public Tour findById(Long id) {
        return tourRepository.findById(id).orElse(null);
    }

    @Override
    public Tour update(Tour tour) {
        return tourRepository.update(tour);
    }
}
