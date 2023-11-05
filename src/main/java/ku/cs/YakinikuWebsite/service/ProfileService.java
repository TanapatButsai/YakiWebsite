package ku.cs.YakinikuWebsite.service;


import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.model.EditProfileRequest;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
@Service
public class ProfileService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    MemberRepository memberRepository;
    public void editProfile(EditProfileRequest editProfileRequest, String username){
        Member temp = modelMapper.map(editProfileRequest,Member.class);
        Member record = memberRepository.findByUsername(username);
        record.setName(temp.getName());
        record.setAddress(temp.getAddress());
        record.setNote(temp.getNote());
        memberRepository.save(record);
    }
}
