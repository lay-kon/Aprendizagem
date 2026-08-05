package com.tyler.dev.firstapirest.services;

import com.tyler.dev.firstapirest.models.User;
import com.tyler.dev.firstapirest.repositorys.TaskRepository;
import com.tyler.dev.firstapirest.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    public User findUserById(long id){

        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow( () -> new RuntimeException(
                "Usuário não encontrado ID : "+id+" | Tipo: "+User.class.getName()
        ));

    }

    @Transactional
    public User creteUser(User obj){

        obj.setId(null);

        return userRepository.save(obj);
    }

    @Transactional
    public User updateUser(User newObj){

        User oldObj = findUserById(newObj.getId());

        oldObj.setPassword(newObj.getPassword());

        return this.userRepository.save(oldObj);
    }

    public String deleteUser(Long id){
        findUserById(id);
        try {
            this.userRepository.deleteById(id);
            return "Usuário Deletado Com Sucesso";
        } catch (Exception e) {
            throw new RuntimeException("Impossivel Deletar Usuário, Possuí Relação No Banco.");
        }
    }
}
