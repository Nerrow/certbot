package ru.shift.certbotcft.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.stereotype.Service;
import ru.shift.certbotcft.api.dto.User;
import ru.shift.certbotcft.api.repository.UserRepository;
import ru.shift.certbotcft.common.exceptions.AlreadyExistException;
import ru.shift.certbotcft.common.exceptions.NotFoundException;
import ru.shift.certbotcft.common.utils.Encryption;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.function.Supplier;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

public static Supplier<? extends DataAccessException> getSupplier(String email){
        return () -> new NotFoundException(String.format("User [%s] not found", email));
}
    //Получить всех пользователей
    public Iterable<User> getAllUsers() throws Exception {
        return userRepository.findAll();
    }

    //Получить пользователя по id
    public Optional<User> getUserById(Integer userId) {
        return userRepository.findById(userId);
    }

//    public User getUserByEmail(String userEmail){
//        User user = userRepository.findByEmail(userEmail).orElseThrow(getSupplier(userEmail));
//        return new org.springframework.security.core.userdetails.User(user.getEmail(),Encryption.encryption(user.getPassword(),1),new ArrayList<>());
//    }

    //Добавление пользователя
    public void add(User user) {
        Optional<User> findUser = userRepository.findByEmail(user.getEmail());
        if (findUser.isEmpty()) {
            userRepository.saveAndFlush(User
                    .builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .personType(user.getPersonType())
                    .build());
        }

    }

    public Long edit(User user) throws AlreadyExistException {
        User userEdit = userRepository.findByEmail(user.getEmail()).orElseThrow(getSupplier(user.getEmail()));
        userEdit.setEmail(user.getEmail());
        userEdit.setPassword(user.getPassword());
        userEdit.setPersonType(user.getPersonType());
        return userRepository.saveAndFlush(userEdit).getId();
    }

//    public Iterable<User> getAllUserByType(String typeUser) {
//        if (typeUser.isEmpty()){
//            throw new AlreadyExistException("Product type not specified");
//        }
//    }
}
