package com.springcourse.dto;

import com.springcourse.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRoleDTO {

    private Long id;
    private Role role;

}
