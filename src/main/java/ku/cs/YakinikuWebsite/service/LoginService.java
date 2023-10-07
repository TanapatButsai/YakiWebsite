//package ku.cs.YakinikuWebsite.service;
//
//import ku.cs.YakinikuWebsite.entity.Category;
//import ku.cs.YakinikuWebsite.entity.Member;
//
//import ku.cs.YakinikuWebsite.model.SignupRequest;
//import ku.cs.YakinikuWebsite.repository.MemberRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//
//
//
//
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    //for increment countLogin in member when login
//    public void incrementLoginCount(String username) {
//
//        Member record = memberRepository.findByUsername(username);
//        memberRepository.save(record);
//    }
//}
