package ru.mosquiito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    private Integer stars;

    private Integer beds;

    private Boolean wifi;

    private Boolean card_payment;

    private Boolean pool;

    @Column(name = "adapted_for_disabled")
    private Boolean adaptedForDisabled;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Tour> tours;
}
