package ru.mosquiito.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Integer stars;

    private Integer beds;

    private Boolean wifi;

    @Column(name = "card_payment")
    private Boolean cardPayment;

    private Boolean pool;

    @Column(name = "adapted_for_disabled")
    private Boolean adaptedForDisabled;
}
