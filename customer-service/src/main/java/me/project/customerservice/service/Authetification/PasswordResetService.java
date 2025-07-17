package me.project.customerservice.service.Authetification;

public interface PasswordResetService {
    void sendResetCode(String email);
    boolean verifyCode(String email, String code);
    void resetPassword(String email, String code, String newPassword);
}
