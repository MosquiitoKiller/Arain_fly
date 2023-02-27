package ru.mosquiito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour {

    @Id
    private Long id;

    private String name;

    @ManyToOne
    private Hotel hotel;

    @OneToOne
    @JoinColumn(name = "start_city")
    private City startCity;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    private Boolean active;
}
