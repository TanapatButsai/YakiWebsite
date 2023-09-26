package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Employee;
import ku.cs.YakinikuWebsite.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignupService {


    @Autowired
    private EmployeeRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }


    public void createUser(Employee user) {
        Employee record = new Employee();
        record.setName(user.getName());
        record.setUsername(user.getUsername());
        record.setRole("ROLE_USER");


        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);


        repository.save(record);
    }


    public Employee getUser(String username) {
        return repository.findByUsername(username);
    }
}

