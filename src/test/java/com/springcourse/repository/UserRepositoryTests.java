package com.springcourse.repository;

import com.springcourse.domain.User;
import com.springcourse.enums.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void AsaveTest() {
        User user = new User(null, "Teste", "spring.teste@gmail.com", "123", Role.ADMINISTRATOR, null, null);
        User userCreated = userRepository.save(user);

        assertThat(userCreated.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest() {
        User user = new User(1L, "Teste Celso", "spring.teste@gmail.com", "123", Role.ADMINISTRATOR, null, null);
        User updateUser = userRepository.save(user);

        assertThat(updateUser.getName()).isEqualTo("Teste Celso");
    }

    @Test
    public void getByIdTest() {
        Optional<User> result = userRepository.findById(1L);
        User user = result.get();

        assertThat(user.getPassword()).isEqualTo("123");
    }

    @Test
    public void listTest() {
        List<User> result = userRepository.findAll();

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void loginTest() {
        Optional<User> result = userRepository.login("spring.teste@gmail.com", "123");
        User loggedUser = result.get();

        assertThat(loggedUser.getId()).isEqualTo(1L);
    }

    @Test
    public void updateRole() {
        int affectedRows = userRepository.updateRole(3L, Role.ADMINISTRATOR);

        assertThat(affectedRows).isEqualTo(1);
    }
}
