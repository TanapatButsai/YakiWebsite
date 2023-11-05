package ku.cs.YakinikuWebsite.controller;

import ku.cs.YakinikuWebsite.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.mail.MessagingException;
import java.util.UUID;


@Controller
@RequestMapping("/admin/orders")
public class AdminController {


    @Autowired
    private OrderService orderService;


    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByStatusNotOrder());
        return "order-all";
    }

    @GetMapping("/{orderId}")
    public String getAllOrders(@PathVariable UUID orderId, Model model) {
        model.addAttribute("order", orderService.getById(orderId));
        return "order-view";
    }


    @PostMapping("/{orderId}/finish")
    public String finishOrder(@PathVariable UUID orderId, Model model) throws MessagingException {
        orderService.finishOrder(orderId);
        return "redirect:/admin/orders";
    }

}
