package com.trainingapps.userms.dao;

import com.trainingapps.userms.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser>findByUsername(String username);

}
