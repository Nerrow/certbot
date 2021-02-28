package ru.shift.certbotcft.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.shift.certbotcft.api.dto.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>, JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long Id);
}
