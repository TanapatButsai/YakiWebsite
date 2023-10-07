package ku.cs.YakinikuWebsite.controller;


import ku.cs.YakinikuWebsite.entity.Member;
import ku.cs.YakinikuWebsite.model.AddCartRequest;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import ku.cs.YakinikuWebsite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ku.cs.YakinikuWebsite.service.EmailSenderService;

import javax.mail.MessagingException;
import java.util.UUID;


@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailSenderService senderService;
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", orderService.getCurrentOrder());
        return "cart";
    }


    @PostMapping
    public String submitOrder(Model model,Authentication authentication) throws MessagingException {

        String username = authentication.getName();
        senderService.triggerMail(username);
        orderService.submitOrder();
        model.addAttribute("confirmOrder", true);
        return "home";
    }


    @PostMapping("/{menuId}")
    public String order(@PathVariable UUID menuId,
                        @ModelAttribute AddCartRequest request, Model model){
        orderService.order(menuId, request);
        return "redirect:/menus";
    }
}

