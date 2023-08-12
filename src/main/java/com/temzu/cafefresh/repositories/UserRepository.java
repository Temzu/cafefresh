package com.temzu.cafefresh.repositories;

import com.temzu.cafefresh.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByLogin(String login);

  Optional<User> findByEmail(String email);

//  @Query("select case when count(c)> 0 then true else false end from User c where lower(c.login) like lower(:login)")
  boolean existsByLogin(String login);

  boolean existsByEmail(String email);
}
