package com.myapp.spring.repsoitory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.myapp.spring.model.User;
import com.myapp.spring.repository.UserRepository;
 
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
 
    @Autowired
    private TestEntityManager entityManager;
     
    @Autowired
    private UserRepository repo;
     
    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("rajkumar@gmail.com");
        user.setPassword("raj2021");
        user.setFirstName("Raj");
        user.setLastName("Kumar");
         
        User savedUser = repo.save(user);
         
        User existUser = entityManager.find(User.class, savedUser.getId());
         
        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
         
    }
}
