package ru.mosquiito.services.hotel;

import jakarta.inject.Inject;
import ru.mosquiito.domain.Tour;
import ru.mosquiito.dto.HotelDto;

import java.util.List;

public class HotelService implements IHotelService {

    @Inject
    private HotelDataAccess hotelDataAccess;


    @Inject
    private HotelTourDataAccess tourDataAccess;

    @Override
    public List<HotelDto> getAllByTour(Long tourId) {
        Tour tour = tourDataAccess.findById(tourId);
        return tour == null ? null : hotelDataAccess.getAllByTour(tour).stream().map(HotelDto::new).toList();
    }
}
