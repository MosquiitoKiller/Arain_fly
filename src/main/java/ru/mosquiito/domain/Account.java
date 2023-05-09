package ru.mosquiito.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mosquiito.domain.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "accountSeq")
    @SequenceGenerator(name = "accountSeq", sequenceName = "account_autoinc", allocationSize = 1)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private AccountStatus accountStatus;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AccountConfirmation accountConfirmation;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "account_roles",
            joinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles;

    public Account(String email, String password, AccountStatus accountStatus, Date createDate, List<Role> roles) {
        this.email = email;
        this.password = password;
        this.accountStatus = accountStatus;
        this.createDate = createDate;
        this.roles = roles;
    }
}
