package ru.mosquiito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    private Integer count;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "create_date")
    private Date createDate;

    private Boolean paid;

    @Column(name = "update_date")
    private Date updateDate;

    public Order(Account account, Tour tour, Integer count, Double totalPrice, Date createDate, Boolean paid, Date updateDate) {
        this.account = account;
        this.tour = tour;
        this.count = count;
        this.totalPrice = totalPrice;
        this.createDate = createDate;
        this.paid = paid;
        this.updateDate = updateDate;
    }
}
