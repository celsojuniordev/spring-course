package com.springcourse.dto;

import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestStageSaveDTO {

    private String description;

    @NotNull(message = "Estado é obrigatório")
    private RequestState state;

    @NotNull(message = "Pedido é obrigatório")
    private Request request;

    @NotNull(message = "Proprietario é obrigatório")
    private User owner;

    public RequestStage transformToReqeustStage() {
        RequestStage requestStage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
        return requestStage;
    }
}
