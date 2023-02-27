package ru.mosquiito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order {

    @Id
    private Long id;

    @ManyToOne
    private Account account;

    private Double price;

    private Date createDate;

    private Boolean paid;

    private Date updateDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="order_tours",
            joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="tour_id", referencedColumnName="id")}
    )
    private List<Tour> tours;
}
