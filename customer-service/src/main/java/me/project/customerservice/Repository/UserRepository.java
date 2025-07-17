package me.project.customerservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.project.customerservice.Entity.User;
import me.project.customerservice.Enum.UserRole;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    List<User> findUserByRole(UserRole role);

    boolean existsByEmail(String email);

    Optional<User> findByEmailVerificationCode(String code);

}
