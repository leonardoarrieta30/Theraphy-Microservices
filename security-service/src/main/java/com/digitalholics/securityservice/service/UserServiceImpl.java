package com.digitalholics.securityservice.service;


import com.digitalholics.securityservice.domain.model.entity.User;
import com.digitalholics.securityservice.domain.persistence.UserRepository;
import com.digitalholics.securityservice.domain.service.UserService;
import com.digitalholics.securityservice.mapping.exception.ResourceNotFoundException;
import com.digitalholics.securityservice.mapping.exception.ResourceValidationException;
import com.digitalholics.securityservice.resource.CreateUserResource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final String ENTITY = "User";

    private final UserRepository userRepository;

    private final Validator validator;



    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(CreateUserResource userResource) {
        Set<ConstraintViolation<CreateUserResource>> violations = validator.validate(userResource);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
/*
        if(userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ResourceValidationException(ENTITY, "A user with the same username exists.");*/

        if(userRepository.findByEmail(userResource.getEmail()).isPresent())
            throw new ResourceValidationException(ENTITY, "A user with the same email exists.");

        User user = new User();
        user.setEmail(userResource.getEmail());
        user.setPassword(userResource.getPassword());
        user.setFirstname(userResource.getFirstname());
        user.setLastname(userResource.getLastname());

//        if(userResource.getType().equalsIgnoreCase("physiotherapist")  || userResource.getType().equalsIgnoreCase("patient")){
//            user.setType(userResource.getType());
//        }else{
//            throw new ResourceValidationException("Type don't exist");
//        }


        return userRepository.save(user);
    }

    @Override
    public User update(Integer userId, User request) {
        Set<ConstraintViolation<User>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId).map(student ->
                        userRepository.save(
                                student.withFirstname(request.getFirstname())
                                        .withLastname(request.getLastname())
                                        .withEmail(request.getEmail())
                                        .withPassword(request.getPassword())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Integer userId) {
        return userRepository.findById(userId).map( user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY,userId));
    }

}
