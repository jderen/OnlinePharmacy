package com.app.pharmacy.model.dto;

import com.app.pharmacy.model.Account;
import com.app.pharmacy.model.User;
import com.app.pharmacy.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String role;
    private String surname;
    private Long accountId;

    public static UserDto getUserDtoByUser(User modelUser){
        return UserDto.builder()
                .id(modelUser.getId())
                .email(modelUser.getEmail())
                .name(modelUser.getName())
                .surname(modelUser.getSurname())
                .role(modelUser.getRole() == null ? null : modelUser.getRole().name())
                .accountId(modelUser.getAccount() == null ? null : modelUser.getAccount().getId())
                .build();
    }

    public static User getUserByUserDto(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .role(Role.valueOf(userDto.getRole()))
                .account(new Account(userDto.accountId))
                .transactions(new ArrayList<>())
                .prescriptions(new ArrayList<>())
                .build();

    }

}