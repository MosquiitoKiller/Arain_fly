package ru.mosquiito.services.hotel;

import ru.mosquiito.domain.Tour;

public interface HotelTourDataAccess {
    Tour findById(Long tourId);
}
