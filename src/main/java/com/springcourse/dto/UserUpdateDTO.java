package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserUpdateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Size(min = 7, max = 99, message = "Password must be between 7 and 99")
    private String password;

    private List<Request> requests = new ArrayList<>();
    private List<RequestStage> stages = new ArrayList<>();

    public User transformToUser() {
        return new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
    }
}
