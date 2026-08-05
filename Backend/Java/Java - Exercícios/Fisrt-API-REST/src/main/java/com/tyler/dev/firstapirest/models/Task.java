package com.tyler.dev.firstapirest.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name=Task.TABLE_NAME)
public class Task {

    //Interfaces Etiquetas
    public interface createTask{}
    public interface updateTask{}

    public static final String TABLE_NAME="task"; //Definindo o nome da tabela no banco de dados

    @Id
    @Column(name = "id",unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //O task é dependente do user
    @ManyToOne //Server para infromar ao banco que é uma relação muitos(Many) para(To) um(One)
    @JoinColumn(name = "user_id",updatable = false,nullable = false)//Server para infromar ao banco que é uma relação entre tabelas

    private User user;

    @Column(name = "descrition_task",nullable = false)
    @NotNull(groups = {Task.createTask.class,Task.updateTask.class})
    @NotEmpty(groups = {Task.createTask.class,Task.updateTask.class})
    @Size(groups = {Task.createTask.class,Task.updateTask.class}, min = 3, max = 255)
    private String descritionTask;


    //Construtotres

    public Task(){

    }
    public Task(Long id, String descritionTask){
        this.id=id;
        this.descritionTask = descritionTask;
    }



    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescritionTask() {
        return descritionTask;
    }

    public void setDescritionTask(String descritionTask) {
        this.descritionTask = descritionTask;
    }
}
