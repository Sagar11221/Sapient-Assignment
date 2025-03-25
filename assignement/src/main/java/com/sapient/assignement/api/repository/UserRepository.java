package com.sapient.assignement.api.repository;


import com.sapient.assignement.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByRole(String role);


    List<User> findAllByOrderByAgeAsc();


    List<User> findAllByOrderByAgeDesc();


    User findByIdOrSsn(Long id, String ssn);

    User findByUsername(String username);
}
