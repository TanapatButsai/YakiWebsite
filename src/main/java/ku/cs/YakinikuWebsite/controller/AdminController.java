package ku.cs.YakinikuWebsite.controller;

import ku.cs.YakinikuWebsite.service.OrderService;
import ku.cs.YakinikuWebsite.service.ReceiptService;
import ku.cs.YakinikuWebsite.status.Status;
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

    @Autowired
    private ReceiptService receiptService;


    @GetMapping
    public String getAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrdersByStatusNotOrder());
        return "order-all";
    }

    @GetMapping("/{orderId}")
    public String getAllOrders(@PathVariable UUID orderId, Model model) {
        model.addAttribute("order", orderService.getById(orderId));
        if(orderService.getById(orderId).getDiscount()!=null) {
            model.addAttribute("discount", orderService.getDiscountByDiscountName(orderService.getById(orderId).getDiscount().getDiscountName()));
        }
        return "order-view";
    }

    @GetMapping("/{id}/printReceipt")
    public String printReceipt(@PathVariable UUID id,Model model){
        model.addAttribute("receipt",receiptService.getReceiptById(id));
        if(orderService.getById(id).getDiscount()!=null) {
            model.addAttribute("discount", orderService.getDiscountByDiscountName(orderService.getById(id).getDiscount().getDiscountName()));
        }
        return "receipt";
    }


    @PostMapping("/{orderId}/finish")
    public String finishOrder(@PathVariable UUID orderId, Model model) throws MessagingException {
        orderService.finishOrder(orderId);
        if(orderService.getById(orderId).getStatus().equals(Status.DELIVERED)){
            receiptService.receipt(orderId);
        }
        return "redirect:/admin/orders";
    }

}
