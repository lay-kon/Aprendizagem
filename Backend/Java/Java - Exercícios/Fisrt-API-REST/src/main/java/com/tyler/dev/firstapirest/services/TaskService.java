package com.tyler.dev.firstapirest.services;

import com.tyler.dev.firstapirest.models.Task;
import com.tyler.dev.firstapirest.models.User;
import com.tyler.dev.firstapirest.repositorys.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    //Preciso listar, criar, eliminar, editar tarefas

    @Autowired
    TaskRepository taskRepository;

    @Autowired //server para infomar o spring que queremos usar uma instancia disso sem, necessidade de usar o new
    UserService userService;

    public Task findTaskById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(()->new RuntimeException("Tarefa Não Encontrada ID: "+id+" Tipo: "+Task.class.getName()));
    }

    public List<Task> findAllTaskById(Long userId){
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional //Essa anotação serve para informar ao spring que o método vai fazer alteração no banco

    public Task createTask(Task obj){

        User user = this.userService.findUserById(obj.getUser().getId()); //Ele retorna (o findUser/TaskByID) o user nesse id

        obj.setId(null); //seta o id da tarefa como nulo
        obj.setUser(user); //garante que o a tarefa tenha o id certo do user, uma vez que manda todo objecto

        return this.taskRepository.save(obj); //salva o a tarefa
    }

    @Transactional
    public Task updateTask(Task newObj){

        Task oldObj = findTaskById(newObj.getId());//Ele retorna (o findUser/TaskByID) a tarefa nesse id

        oldObj.setDescritionTask(newObj.getDescritionTask()); //atualiza a tarefa

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