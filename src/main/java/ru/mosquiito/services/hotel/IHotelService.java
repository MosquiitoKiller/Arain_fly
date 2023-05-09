package ru.mosquiito.services.hotel;

import ru.mosquiito.dto.HotelDto;

import java.util.List;

public interface IHotelService {
    List<HotelDto> getAllByTour(Long tourId);
}
