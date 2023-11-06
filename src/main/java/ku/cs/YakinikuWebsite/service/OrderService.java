package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.*;
import ku.cs.YakinikuWebsite.model.AddCartRequest;
import ku.cs.YakinikuWebsite.model.DiscountRequest;
import ku.cs.YakinikuWebsite.repository.*;
import ku.cs.YakinikuWebsite.status.DiscountStatus;
import ku.cs.YakinikuWebsite.status.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {


    @Autowired
    private PurchaseOrderRepository orderRepository;


    @Autowired
    private OrderItemRepository itemRepository;


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiscountRepository discountRepository;
    private UUID currentOrderId;


    public void createNewOrder(String username) {
        Member member = memberRepository.findByUsername(username);

        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setMember(member);
        newOrder.setStatus(Status.ORDER);
        PurchaseOrder record = orderRepository.save(newOrder);
        currentOrderId = record.getId();
    }

    public PurchaseOrder getCurrentOrder(String username) {
        if (currentOrderId == null)
            createNewOrder(username);
        System.out.println(currentOrderId.toString());
        return orderRepository.findById(currentOrderId).get();
    }

    public void removeCurrentOrder(){
    this.currentOrderId = null;

    }

    public void submitOrder() {
        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        currentOrder.setStatus(Status.CONFIRM);
        orderRepository.save(currentOrder);
        currentOrderId = null;
    }

    public void order(UUID menuId, AddCartRequest request,String username) {
        if (currentOrderId == null)
            createNewOrder(username);


        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        Menu menu = menuRepository.findById(menuId).get();


        OrderItem item = new OrderItem();
        item.setId(new OrderItemKey(currentOrderId, menuId));
        item.setPurchaseOrder(currentOrder);
        item.setMenu(menu);
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }
    public List<PurchaseOrder> getAllOrdersByStatusNotOrder(){
        return orderRepository.getAllByStatusNot(Status.ORDER);
    }
    public List<PurchaseOrder> getAllOrdersByStatusNotOrderMember(String username){
        return orderRepository.getAllByMemberUsernameAndStatusNot(username, Status.ORDER);
    }
    public PurchaseOrder getById(UUID orderId) {
        return orderRepository.findById(orderId).get();
    }

    public void finishOrder(UUID orderId) throws MessagingException {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        if (record.getStatus() == Status.CONFIRM){
            triggerMailOrderReceived(record.getMember().getUsername());
            record.setStatus(Status.ORDER_RECEIVED);
        } else if (record.getStatus() == Status.ORDER_RECEIVED){
            triggerMailDelivered(record.getMember().getUsername());
            record.setStatus(Status.DELIVERED);
        }
        orderRepository.save(record);
    }

    public void addDiscount(DiscountRequest request){
        Discount record = modelMapper.map(request,Discount.class);
        record.setDiscountStatus(DiscountStatus.ENABLE);
        discountRepository.save(record);
    }
    public boolean findDiscount(String discountID){
        if(discountRepository.findByDiscountName(discountID) == null){
            return true;
        }
        return false;
    }
    public boolean isDiscountAvailable(String discountID){
        if(discountRepository.findByDiscountName(discountID) == null){
            return false;
        }
        if(discountRepository.findByDiscountName(discountID).getDiscountStatus().equals(DiscountStatus.ENABLE)
                && discountRepository.findByDiscountName(discountID) != null){
            return true;
        }
        return false;
    }

    public void setDisableDiscount(String discountID){
        Discount discount = discountRepository.findByDiscountName(discountID);
        discount.setDiscountStatus(DiscountStatus.DISABLE);
        discountRepository.save(discount);
    }

    public Discount getDiscountByDiscountName(String discountID){
        return discountRepository.findByDiscountName(discountID);
    }

//    ------------------------ email sender
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }
//    public void triggerMailConfirm(String username) throws MessagingException {
//        sendEmail(memberRepository.findByUsername(username).getEmail(),
//                ":) Yakiniku Delivery Submitted order",
//                "You have ordered food from Yakiniku Delivery! " +"\n"+
//                        "We will notify you if the order is Confirm By Admin.");
//
//    }
    public void triggerMailOrderReceived(String username) throws MessagingException {
        sendEmail(memberRepository.findByUsername(username).getEmail(),
                ":) Yakiniku Delivery Order Received",
                    "OrderReceived! " +"\n"+
                         "We will notify you if the order is delivered.");

    }
    public void triggerMailDelivered(String username) throws MessagingException {
        sendEmail(memberRepository.findByUsername(username).getEmail(),
                "Yakiniku Delivery Food Delivered",
                "We have delivered your food from Yakiniku Delivery! " +"\n"+
                        "Thank you for choosing our food" +"\n"+
                        "We look forward to seeing you again");


    }
}