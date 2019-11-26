package com.app.pharmacy.model.dto;

import com.app.pharmacy.model.Account;
import com.app.pharmacy.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    @Column(unique = true)
    private String login;
    private String password;
    private Long userId;


    public static Account getAccountByAccountDto(AccountDto aDto){
        return Account.builder()
                .id(aDto.id)
                .login(aDto.login)
                .password(aDto.password)
                .user(new User())
                .build();
    }

    public static AccountDto getAccountDtoByAccount(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .login(account.getLogin())
                .password(account.getPassword())
                .userId(account.getUser() == null ? null : account.getUser().getId())
                .build();
    }
}