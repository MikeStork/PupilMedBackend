package com.pupilmed.pupilmedapi.service;

import com.pupilmed.pupilmedapi.model.User;
import com.pupilmed.pupilmedapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }
    public User findById(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new RuntimeException("User not found");
        }else{
            return user.get();
        }
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User update(User user){
        Optional<User> found_user = userRepository.findById(user.getId());
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/UPDATE: User not found");
        }
        User userToUpdate = found_user.get();
        userToUpdate.setAktywny(user.isAktywny());
        userToUpdate.setHaslo(user.getHaslo());
        userToUpdate.setNumerTelefonu(user.getNumerTelefonu());
        return userRepository.save(userToUpdate);
    }
    @Transactional
    public void delete(int id){
        Optional<User> found_user = userRepository.findById(id);
        if(found_user.isEmpty()){
            throw new RuntimeException("USER/UPDATE: User not found");
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
            throw new RuntimeException("USER/UPDATE: User not found");
        }
        System.out.println("Attempting to deactivate user with id: " + id);  // Logowanie przed usunięciem
        User updatedUser = found_user.get();
        updatedUser.setAktywny(false);
        userRepository.save(updatedUser);
         // Deaktywacja
        System.out.println("User with id: " + id + " has been deleted");  // Logowanie po usunięciu
        userRepository.flush();

    }
}
