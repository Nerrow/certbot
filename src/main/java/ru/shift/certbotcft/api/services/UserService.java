package ru.shift.certbotcft.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shift.certbotcft.api.dto.User;
import ru.shift.certbotcft.api.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Получить всех пользователей
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
    //Получить пользователя по id
    public Optional<User> getUserById(Integer userId){
        return userRepository.findById(userId);
    }
    //Добавление пользователя
    public void add(User user){
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
//        if(findUser.isEmpty()) {
//
//        }
    }
}
