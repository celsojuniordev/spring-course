package com.springcourse.dto;

import com.springcourse.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserUpdateRoleDTO {

    @NotNull(message = "Role required")
    private Role role;

}
