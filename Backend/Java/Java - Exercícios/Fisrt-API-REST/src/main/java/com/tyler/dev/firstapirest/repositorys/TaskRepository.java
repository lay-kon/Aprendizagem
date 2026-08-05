package com.tyler.dev.firstapirest.repositorys;

import com.tyler.dev.firstapirest.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
