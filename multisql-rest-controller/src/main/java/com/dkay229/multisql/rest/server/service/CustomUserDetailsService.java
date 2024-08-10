package com.dkay229.multisql.rest.server.service;
import com.dkay229.multisql.rest.server.entity.MultiUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");

    public void createUser(String username, String password) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        MultiUser user = new MultiUser();
        user.setUsername(username);
        user.setPassword(password);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
        em.close();
        return users;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Replace this with your own user retrieval logic
        if ("user".equals(username)) {
            return User.withUsername(username)
                    .password("{noop}password") // {noop} indicates no password encoding
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
