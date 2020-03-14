package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdateDTO {

    @NotBlank(message = "Assunto é obrigatório")
    private String subject;

    private String description;

    @NotNull(message = "Estado é obeigatório")
    private RequestState state;

    @NotNull(message = "Proprietario é obrigatório")
    private User owner;
    private List<RequestStage> stages = new ArrayList<>();

    public Request transformToRequest() {
        return new Request(null, this.subject, this.description, null, this.state, this.owner, stages, null);
    }
}
