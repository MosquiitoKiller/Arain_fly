package ru.mosquiito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.mosquiito.domain.Account;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;

    private String email;

    private Integer accountStatus;

    private Date createDate;

    private List<RoleDto> roles;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.accountStatus = account.getAccountStatus().getStatus();
        this.createDate = account.getCreateDate();
        this.roles = account.getRoles().stream().map(RoleDto::new).toList();
    }
}
