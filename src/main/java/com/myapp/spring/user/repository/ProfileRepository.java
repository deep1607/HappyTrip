package com.myapp.spring.user.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.spring.user.model.Profile;



@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
 Optional<Profile> findByEmail(String email);
}
