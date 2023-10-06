package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Member;
import org.modelmapper.ModelMapper;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import ku.cs.YakinikuWebsite.model.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {


    @Autowired
    private MemberRepository repository;


    @Autowired
    private PasswordEncoder passwordEncoder;



    @Autowired
    private ModelMapper modelMapper;

    public boolean isUsernameAvailable(String username) {
        return repository.findByUsername(username) == null;
    }
    public boolean isEmailAvailable(String email) {
        return repository.findByEmail(email) == null;
    }

    public void createUser(SignupRequest user) {
        Member record = modelMapper.map(user, Member.class);
//        record.setName(user.getName());
//        record.setUsername(user.getUsername());
        record.setRole("CUSTOMER");

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        record.setPassword(hashedPassword);


        repository.save(record);
    }


    public Member getUser(String username) {
        return repository.findByUsername(username);
    }
}

