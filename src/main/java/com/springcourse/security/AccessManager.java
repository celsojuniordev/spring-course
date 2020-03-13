package com.springcourse.security;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("accessManager")
public class AccessManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestService requestService;

    public boolean isOwner(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) throw new NotFoundException("Usuário não encontrado com o email ->" + email);

        User result = user.get();

        return result.getId() == id;
    }

    public boolean isRequestOwner(Long id) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) throw new NotFoundException("Usuário não encontrado com o email ->" + email);

        User result = user.get();

        Request request = requestService.findById(id);

        return result.getId() == request.getOwner().getId();
    }
}
