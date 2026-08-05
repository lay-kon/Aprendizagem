package com.tyler.dev.firstapirest.services;

import com.tyler.dev.firstapirest.models.Task;
import com.tyler.dev.firstapirest.models.User;
import com.tyler.dev.firstapirest.repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TaskService {

    //Preciso listar, criar, eliminar, editar tarefas

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    public Task findTaskById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(()->new RuntimeException("Tarefa Não Encontrada ID: "+id+" Tipo: "+Task.class.getName()));
    }

    @Transactional
    public Task createTask(Task obj){

        User user = this.userService.findUserById(obj.getUser().getId());

        obj.setId(null);
        obj.setUser(user);

        return this.taskRepository.save(obj);
    }

    @Transactional
    public Task updateTask(Task newObj){

        Task oldObj = findTaskById(newObj.getId());

        oldObj.setDescritionTask(newObj.getDescritionTask());

        return this.taskRepository.save(oldObj);
    }

    public String deleteTask(Long id){
        findTaskById(id);
        try {
            this.taskRepository.deleteById(id);
            return "Task Eliminada Com Sucesso";
        }catch (Exception ex){
            throw new RuntimeException("Erro ao Deletar, Possuí Relação No Banco.");
        }
    }
}