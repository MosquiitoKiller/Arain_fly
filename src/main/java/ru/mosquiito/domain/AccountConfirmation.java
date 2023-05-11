package ru.mosquiito.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account_confirmation")
@NoArgsConstructor
public class AccountConfirmation {
    @Id
    private Long id;

    private String code;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Account account;

    public AccountConfirmation(String code, Account account) {
        this.code = code;
        this.account = account;
    }
}
