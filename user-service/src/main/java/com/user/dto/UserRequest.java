package com.user.dto;

import com.user.util.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserRequest {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phoneNumber;
    private String password;
    private UserRole role;

}
