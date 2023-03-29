package ru.mosquiito.services.order;

import ru.mosquiito.domain.Tour;

public interface OrderTourDataAccess {
    Tour findById(Long id);
    Tour update(Tour tour);
}
