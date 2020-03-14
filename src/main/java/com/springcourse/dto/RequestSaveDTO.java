package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
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
public class RequestSaveDTO {

    @NotBlank(message = "Assunto é obrigatório")
    private String subject;

    private String description;

    @NotNull(message = "Proprietario é obrigatório")
    private User owner;
    private List<RequestStage> stages = new ArrayList<>();

    public Request transformToRequest() {
        return new Request(null, this.subject, this.description, null, null, this.owner, stages, null);
    }
}
