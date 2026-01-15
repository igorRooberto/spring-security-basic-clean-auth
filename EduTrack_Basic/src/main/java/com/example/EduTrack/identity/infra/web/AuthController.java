package com.example.EduTrack.identity.infra.web;

import com.example.EduTrack.identity.application.dto.RegisterUserInput;
import com.example.EduTrack.identity.application.usecase.RegisterUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterUserInput request) {

       registerUserUseCase.execute(new RegisterUserInput(request.login(),
                                                         request.email(),
                                                         request.password()
               ));

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication){
        return ResponseEntity.ok("Usuário Logado Com Sucesso:" +authentication.getName());
    }

}
