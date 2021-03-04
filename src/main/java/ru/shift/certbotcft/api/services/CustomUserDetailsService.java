package ru.shift.certbotcft.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.shift.certbotcft.api.dto.User;
import ru.shift.certbotcft.api.repository.UserRepository;
import ru.shift.certbotcft.common.utils.Encryption;

import java.util.ArrayList;

import static ru.shift.certbotcft.api.services.UserService.getSupplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(getSupplier(email));
        System.out.println(user);
        System.out.println(user.getPassword());
        String passw = Encryption.encryption(user.getPassword(), 1);
        System.out.println(passw);
        System.out.println(Encryption.encryption(passw, 2));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());

    }
}
