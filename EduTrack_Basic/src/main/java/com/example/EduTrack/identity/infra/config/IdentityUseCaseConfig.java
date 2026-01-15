package com.example.EduTrack.identity.infra.config;

import com.example.EduTrack.identity.application.gateway.PasswordEncoder;
import com.example.EduTrack.identity.application.usecase.RegisterUserUseCase;
import com.example.EduTrack.identity.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdentityUseCaseConfig {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public IdentityUseCaseConfig(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase() {
        return new RegisterUserUseCase(this.passwordEncoder,this.userRepository);
    }


}
