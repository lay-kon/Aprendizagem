package com.tyler.dev.firstapirest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)

public class User {

    //Interfaces Etiquetas
    public interface creteUser{}
    public interface updateUser{}

    public static final String TABLE_NAME = "user";//Definindo o nome da tabela no banco de dados

    @Id//essa anotação server para informar ao banco que esse atributo é um id
    @Column(name = "id", unique = true)//essa anotação server para colocar informações ou propriedades da coluna no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)//essa anotação server para informar ao banco que o atributo deve ser auto-incrementado (auto-increment)
    private long id;


    @Column(name="username")//essa anotação server para colocar informações ou propriedades da coluna no banco de dados
    @NotNull(groups = {creteUser.class,updateUser.class})//essa anotação server para garantir que na hora de criar o user, não seje permitido, username nulo (null)
    @NotEmpty(groups = {creteUser.class, updateUser.class})//essa anotação server para garantir que na hora de criar o user, não seje permitido, username vazio ("")
    @Size(groups = {creteUser.class, updateUser.class}, min = 5, max = 100)//essa anotação server para garantir que na hora de criar o user, não seje permitido, caracteres que passe do limite estabelecido
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//essa anotação server para não retornar a pass do user no body em json
    @Column(name="passowrd")//essa anotação server para colocar informações ou propriedades da coluna no banco de dados
    @NotNull(groups = {creteUser.class,updateUser.class})//essa anotação server para garantir que na hora de criar e atualizar a senha do user, não seje permitido, senha nula
    @NotEmpty(groups = {creteUser.class, updateUser.class})//essa anotação server para garantir que na hora de criar e atualizar a senha do user, não seje permitido, senha vazia
    @Size(groups = {creteUser.class, updateUser.class}, min = 3, max = 20)//essa anotação server para garantir que na hora de criar e atualizar a senha do user, não seje permitido, caracteres que passe do limite estabelecido
    private String password;


    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();


    //Contrutores

    public User(){

    }
    public User(long id,String username, String password){
        this.id=id;
        this.username=username;
        this.password=password;
    }


    //Getters e Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
