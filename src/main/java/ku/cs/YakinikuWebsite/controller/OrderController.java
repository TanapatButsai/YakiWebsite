package ku.cs.YakinikuWebsite.controller;


import ku.cs.YakinikuWebsite.entity.Discount;
import ku.cs.YakinikuWebsite.model.AddCartRequest;
import ku.cs.YakinikuWebsite.model.DiscountRequest;
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
    public String viewCart(Model model,Authentication authentication) {
        model.addAttribute("cart", orderService.getCurrentOrder(authentication.getName()));
        return "cart";
    }

    @PostMapping("/checkDiscount")
    public String checkDiscount(@ModelAttribute DiscountRequest discount,Authentication authentication, Model model) {
            if (orderService.isDiscountAvailable(discount.getDiscountName())) {
                orderService.setDisableDiscount(discount.getDiscountName());
                orderService.setDiscount(discount.getDiscountName());
                model.addAttribute("available", true);
                model.addAttribute("discount", orderService.getDiscountByDiscountName(discount.getDiscountName()));
            } else if (orderService.findDiscount(discount.getDiscountName())) {
                model.addAttribute("notFound", "This discount is not available.");
            } else {
                model.addAttribute("disable", "The discount has already been used.");
            }
        model.addAttribute("cart", orderService.getCurrentOrder(authentication.getName()));
        return "cart";
    }



    @GetMapping("/discount/add")
    public String getAddDiscountPage(Model model){
        return "discount-add";
    }

    @PostMapping("/discount/add")
    public String addDiscount(@ModelAttribute DiscountRequest request, Model model){
        if(orderService.findDiscount(request.getDiscountName())){
            orderService.addDiscount(request);
            model.addAttribute("addDiscount",true);
        }
        else{
            model.addAttribute("duplicateDiscountName","The name is duplicated in the system.");
        }
        return "discount-add";
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
                        @ModelAttribute AddCartRequest request, Model model,Authentication authentication){
        orderService.order(menuId, request,authentication.getName());
        return "redirect:/menus";
    }
}

