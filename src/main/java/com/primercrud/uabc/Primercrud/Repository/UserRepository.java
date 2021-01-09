package com.primercrud.uabc.Primercrud.Repository;

import com.primercrud.uabc.Primercrud.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
    * @return the user coincidence with email.
     * @param email to find.
     * */
    Optional<User> findByEmail(String email);
    public User findByToken(String token);
}
