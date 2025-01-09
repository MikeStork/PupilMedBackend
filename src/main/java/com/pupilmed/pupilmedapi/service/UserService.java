package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.User;
import com.pupilmed.pupilmedapi.model.UserAuthData;
import com.pupilmed.pupilmedapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user){
        user.setHaslo(encoder.encode(user.getHaslo()));
        return userRepository.save(user);
    }

    public UserAuthData verify(String username, String password){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if(authentication.isAuthenticated()){
            User user = userRepository.findByNumerTelefonu(username);
            UserAuthData userAuthData = new UserAuthData();
            userAuthData.setId(user.getId());
            userAuthData.setHaslo(user.getHaslo());
            userAuthData.setNumerTelefonu(user.getNumerTelefonu());
            userAuthData.setRola(user.getRola());
            userAuthData.setToken(jwtService.generateToken(user));
            userAuthData.setAktywny(user.isAktywny());
//            return jwtService.generateToken(user);
            return userAuthData;
        }
        throw new RuntimeException("Invalid username or password");
    }

    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("USER/GET: User not found");
        }else{
            return user.get();
        }
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Transactional
    public User update(User user){
        Optional<User> found_user = userRepository.findById(user.getId());
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/UPDATE: User not found");
        }
        User userToUpdate = found_user.get();
        userToUpdate.setAktywny(user.isAktywny());
        userToUpdate.setHaslo(encoder.encode(user.getHaslo()));
        userToUpdate.setNumerTelefonu(user.getNumerTelefonu());
        return userRepository.save(userToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<User> found_user = userRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/DELETE: User not found");
        }
        System.out.println("Attempting to delete user with id: " + id);  // Logowanie przed usunięciem
        userRepository.deleteById(id);  // Usuwanie użytkownika
        System.out.println("User with id: " + id + " has been deleted");  // Logowanie po usunięciu
        userRepository.flush();

    }
    @Transactional
    public void deactivate(int id){
        Optional<User> found_user = userRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/DEACTIVATE: User not found");
        }
        System.out.println("Attempting to deactivate user with id: " + id);  // Logowanie przed usunięciem
        User updatedUser = found_user.get();
        updatedUser.setAktywny(false);
        userRepository.save(updatedUser);
         // Deaktywacja
        System.out.println("User with id: " + id + " has been deleted");  // Logowanie po usunięciu
        userRepository.flush();

    }

    @Transactional
    public Integer changePassword(int id, String password, String newPassword){
        Optional<User> found_user = userRepository.findById(id);
        if(found_user.isEmpty()){
//            throw new RuntimeException("USER/CHANGEPASSWORD: User not found");
            return 1;
        }
        System.out.println("Znaleziono user");
        User user = found_user.get();
        if (!encoder.matches(password, user.getHaslo())) {
            return 2; // Hasło nie pasuje
        }
        System.out.println("haslo zgodne");
        user.setHaslo(encoder.encode(newPassword));
        userRepository.save(user);
        userRepository.flush();
        return 0;

    }
}
