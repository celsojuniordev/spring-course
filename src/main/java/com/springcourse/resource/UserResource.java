package com.springcourse.resource;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.dto.UserLoginDTO;
import com.springcourse.dto.UserSaveDTO;
import com.springcourse.dto.UserUpdateDTO;
import com.springcourse.dto.UserUpdateRoleDTO;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.security.JwtManager;
import com.springcourse.service.RequestService;
import com.springcourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtManager jwtManager;

    @Secured({ "ROLE_ADMINISTRATOR" })
    @PostMapping
    public ResponseEntity<User> save(@RequestBody @Valid UserSaveDTO userSaveDTO) {
        User user = userSaveDTO.transformToUser();
        User createdUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PreAuthorize("@accessManager.isOwner(#id)")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        User user = userUpdateDTO.transformToUser();
        user.setId(id);
        User updatedUser = service.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id")Long id) {
        User user = service.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<PageModel<User>> findAll(
            @RequestParam Map<String, String> params) {

        PageRequestModel pr = new PageRequestModel(params);
        PageModel<User> pm = service.listAllOnLazyModel(pr);

        return ResponseEntity.ok(pm);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication auth = authManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(auth);

        org.springframework.security.core.userdetails.User userSpring =
                (org.springframework.security.core.userdetails.User) auth.getPrincipal();

        String email = userSpring.getUsername();
        List<String> roles = userSpring.getAuthorities()
                .stream()
                .map(authority -> authority.getAuthority())
                .collect(Collectors.toList());
        String jwt = jwtManager.createToken(email, roles);

        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/{id}/requests")
    public ResponseEntity<PageModel<Request>> findAllRequestsById(
            @PathVariable("id") Long id,
            @RequestParam Map<String, String> params) {

        PageRequestModel pr = new PageRequestModel(params);
        PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, pr);

        return ResponseEntity.ok(pm);
    }

    @Secured({ "ROLE_ADMINISTRATOR" })
    @PatchMapping("/role/{id}")
    public ResponseEntity<?> updateRole(@RequestBody @Valid UserUpdateRoleDTO userDTO, @PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setRole(userDTO.getRole());

        service.updateRole(user);
        return ResponseEntity.ok().build();
    }
}
