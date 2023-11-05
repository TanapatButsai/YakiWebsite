package ku.cs.YakinikuWebsite.controller;


import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.model.EditProfileRequest;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import ku.cs.YakinikuWebsite.service.OrderService;
import ku.cs.YakinikuWebsite.service.ProfileService;
import ku.cs.YakinikuWebsite.service.ReceiptService;
import ku.cs.YakinikuWebsite.status.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class HomeController {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileService profileService;

    @Autowired
    private OrderService orderService;

    @Autowired
    ReceiptService receiptService;
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
    public String getEditProfilePage(Model model,Authentication authentication){
        Member member = memberRepository.findByUsername(authentication.getName());
        model.addAttribute("member",member);
        return "edit-profile";
    }

    @PostMapping("/edit")
    public String editPage(
            @ModelAttribute EditProfileRequest editProfileRequest
            , Authentication authentication
            , Model model){

        profileService.editProfile(editProfileRequest,authentication.getName());
        return "redirect:/profile";
    }

    @GetMapping("/my-orders")
    public String getAllMemberOrder(Model model,Authentication authentication){
        model.addAttribute("orders", orderService.getAllOrdersByStatusNotOrderMember(authentication.getName()));
        Status status;
        model.addAttribute("DELIVERED",Status.DELIVERED);
        return "order-all";
    }

    @GetMapping("/{id}/printReceipt")
    public String printReceipt(@PathVariable UUID id, Model model){
        model.addAttribute("receipt",receiptService.getReceiptById(id));
        if(orderService.getById(id).getDiscount()!=null) {
            model.addAttribute("discount", orderService.getDiscountByDiscountName(orderService.getById(id).getDiscount().getDiscountName()));
        }
        return "receipt";
    }
}
