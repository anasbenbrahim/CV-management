package me.project.customerservice.service.Authetification;

import java.util.Random;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.project.customerservice.entity.User;
import me.project.customerservice.repository.UserRepository;
import me.project.customerservice.service.EmailService;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendResetCode(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        String code = String.format("%06d", new Random().nextInt(999999));
        user.setEmailVerificationCode(code);
        userRepository.save(user);

        emailService.sendCode(email, code);
    }

    @Override
    public boolean verifyCode(String email, String code) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        return code.equals(user.getEmailVerificationCode());
    }

    @Override
    public void resetPassword(String email, String code, String newPassword) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        if (!code.equals(user.getEmailVerificationCode())) {
            throw new RuntimeException("Code incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setEmailVerificationCode(null); // Invalide le code
        user.setEmailVerified(true); // Facultatif
        userRepository.save(user);
    }
}

