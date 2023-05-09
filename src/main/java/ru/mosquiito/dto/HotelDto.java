package ru.mosquiito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mosquiito.domain.Hotel;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private String name;

    private Long cityId;

    private Integer stars;

    private Integer beds;

    private Boolean wifi;

    private Boolean cardPayment;

    private Boolean pool;

    private Boolean adaptedForDisabled;

    public HotelDto(Hotel hotel) {
        this.name = hotel.getName();
        this.cityId = hotel.getCity().getId();
        this.stars = hotel.getStars();
        this.beds = hotel.getBeds();
        this.wifi = hotel.getWifi();
        this.cardPayment = hotel.getCardPayment();
        this.pool = hotel.getPool();
        this.adaptedForDisabled = hotel.getAdaptedForDisabled();
    }
}
