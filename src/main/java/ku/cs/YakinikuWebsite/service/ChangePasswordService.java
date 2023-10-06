package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.model.ChangePasswordRequest;
import ku.cs.YakinikuWebsite.model.ChangePasswordResult;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class ChangePasswordService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        Member member = memberRepository.findByUsername(username);

        if (member != null && passwordEncoder.matches(oldPassword, member.getPassword())) {
            // Passwords match; proceed with changing the password
            String hashedNewPassword = passwordEncoder.encode(newPassword);
            member.setPassword(hashedNewPassword);
            memberRepository.save(member);
            return true;
        } else {
            // Invalid old password
            return false;
        }
    }

}
