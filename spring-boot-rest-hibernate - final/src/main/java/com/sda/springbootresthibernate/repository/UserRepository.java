package com.sda.springbootresthibernate.repository;

import com.sda.springbootresthibernate.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {


    private EntityManager entityManager;

    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User save(final User user) {
        entityManager.persist(user);
        return user;
    }

    public Optional<User> findById(final Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    public void delete(final User user) {
        entityManager.remove(user);
    }

    public List<User> findByAttributes(final String firstName, final String lastName, final String email) {
        return entityManager.createQuery("" +
                "SELECT u FROM User u WHERE u.firstName =: firstName " +
                "OR u.lastName =: lastName OR u.email =: email")
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .setParameter("email", email)
                .getResultList();
    }

}
