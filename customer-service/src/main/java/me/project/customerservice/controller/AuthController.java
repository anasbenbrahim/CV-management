package me.project.customerservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.project.customerservice.Entity.User;
import me.project.customerservice.Repository.UserRepository;
import me.project.customerservice.service.Authetification.AuthService;
import me.project.customerservice.service.Authetification.AuthServiceImpl;
import me.project.customerservice.service.Authetification.PasswordResetService;
import me.project.customerservice.dto.*;
import me.project.customerservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    // Constants
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    // Dependencies
    private final AuthServiceImpl authService;
    private final AuthService userServ;
    private final UserRepository userRepository;
    private final PasswordResetService passwordResetService;

    // Authentication Endpoints
    @PostMapping("/register")
    public ResponseEntity<AuthetificationResponse> signUptech(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(userServ.register(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthentificationRequest authenticationRequest,
                                      HttpServletResponse response) throws IOException {
        return authService.login(authenticationRequest, response);
    }

    // User Management Endpoints
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userServ.users());
    }

    @GetMapping("/one/{id}")
    public User getUser(@PathVariable(name = "id") Long userId) {
        return userServ.getUser(userId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = userServ.deleteUser(id);
        return result.startsWith("Succès") 
            ? ResponseEntity.ok(result)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    // Profile Management Endpoints
    @PutMapping("/users/{id}/profile")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id,
                                            @RequestBody SignUpRequest signUpRequest) {
        try {
            authService.updateUserProfile(id, signUpRequest);
            return ResponseEntity.ok("Profil mis à jour avec succès pour l'utilisateur ID : " + id);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    // verification d'email
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("code") String code) {
        Optional<User> userOptional = userRepository.findByEmailVerificationCode(code);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code de vérification invalide");
        }

        User user = userOptional.get();
        user.setEmailVerified(true);
        user.setEmailVerificationCode(null); // Optionnel : le code n'est plus utile
        userRepository.save(user);

        return ResponseEntity.ok("Email vérifié avec succès. Vous pouvez maintenant vous connecter.");
    }




    @PostMapping("/forgot")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        passwordResetService.sendResetCode(request.getEmail());
        return ResponseEntity.ok("Code envoyé à votre email");
    }

    @PostMapping("/verify-forgot-code")
    public ResponseEntity<?> verifyCode(@RequestBody VerifyCodeRequest request) {
        boolean valid = passwordResetService.verifyCode(request.getEmail(), request.getCode());
        return valid ? ResponseEntity.ok("Code valide")
                     : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code invalide");
    }

    @PutMapping("/reset-mot-de-passe")
    public ResponseEntity<?> resetPassword(@RequestBody NewPasswordRequest request) {
        passwordResetService.resetPassword(
            request.getEmail(),
            request.getCode(),
            request.getNewPassword()
        );
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès.");
    }

}