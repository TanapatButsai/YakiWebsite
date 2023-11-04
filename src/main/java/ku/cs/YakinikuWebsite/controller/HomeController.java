package ku.cs.YakinikuWebsite.controller;


import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @Autowired
    MemberRepository memberRepository;


    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("greeting", "welcome to YakiWeb");
        // ต้องคืนค่าเป็นชื่อไฟล์ html template โดยในเมธอดนี้ คืนค่าเป็น home.html
        return "home";
    }

    @GetMapping("/profile")
    public String getProfilePage(Authentication authentication,Model model) {
        Member member = memberRepository.findByUsername(authentication.getName());
        model.addAttribute("email",member.getEmail());
        model.addAttribute("name",member.getName());
        model.addAttribute("username",member.getUsername());
        model.addAttribute("address",member.getAddress());
        model.addAttribute("note",member.getNote());
        return "profile";
    }

    @GetMapping("/edit")
    public String getEditPage(Authentication authentication,Model model){
        Member member = memberRepository.findByUsername(authentication.getName());
        model.addAttribute("member",member);
        return "edit-profile";

    }

}
