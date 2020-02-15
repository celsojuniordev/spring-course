package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserSaveDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Email
    private String email;

    @Size(min = 7, max = 99, message = "Senha deve possuir de 7 a 99 caracteres.")
    private String password;

    @NotNull(message = "Papél é obrigatório")
    private Role role;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();

    public User transformToUser() {
        return new User(null, this.name, this.email, this.password, this.role, this.requests, this.stages);
    }
}
