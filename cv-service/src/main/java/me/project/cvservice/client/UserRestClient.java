package me.project.cvservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import me.project.cvservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface UserRestClient {

    @GetMapping("/api/auth/one/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomer")
    User findCustomerById(@PathVariable Long id);

    @CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultCustomers")
    @GetMapping("/api/auth/users")
    List<User> findAllCustomers();

    default User getDefaultCustomer(Long id, Exception exception){
        User customer = new User();
        customer.setId(id);
        customer.setFirstName("Not Valid");
        customer.setLastName("Not Valid");
        customer.setEmail("Not Valid");
        customer.setPhoneNumber("Not Valid");
        customer.setAddress("Not Valid");
        return customer;
    }

    default List<User> getDefaultCustomers(Exception exception){
        return List.of();
    }

}
