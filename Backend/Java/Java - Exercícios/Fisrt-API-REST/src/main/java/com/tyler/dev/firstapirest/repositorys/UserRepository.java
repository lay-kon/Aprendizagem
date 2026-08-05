package com.tyler.dev.firstapirest.repositorys;

import com.tyler.dev.firstapirest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
