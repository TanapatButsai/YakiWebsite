package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ku.cs.YakinikuWebsite.status.Role;

@Service
public class SignupService {


    @Autowired
    private EmployeeRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }


    public void createUser(Member user) {
        Member record = new Member();
        record.setName(user.getName());
        record.setUsername(user.getUsername());
        record.setRole("CUSTOMER");


        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);


        repository.save(record);
    }


    public Member getUser(String username) {
        return repository.findByUsername(username);
    }
}

