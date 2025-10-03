package br.com.esotk.my_first_rest_with_java_spring.repository;

import br.com.esotk.my_first_rest_with_java_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.enabled = false WHERE u.id =:id")
    void disableUser(@Param("id") Long id);

}
