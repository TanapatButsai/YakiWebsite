package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.*;
import ku.cs.YakinikuWebsite.model.AddCartRequest;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import ku.cs.YakinikuWebsite.repository.MenuRepository;
import ku.cs.YakinikuWebsite.repository.OrderItemRepository;
import ku.cs.YakinikuWebsite.repository.PurchaseOrderRepository;
import ku.cs.YakinikuWebsite.status.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private MemberRepository memberRepository;


    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    //private Member member;


    private UUID currentOrderId;


    public void createNewOrder(Member member) {
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(Status.ORDER);
        //newOrder.setMember(memberRepository.findById(member.getId()).get());
        PurchaseOrder record = orderRepository.save(newOrder);
        currentOrderId = record.getId();
    }

    public PurchaseOrder getCurrentOrder(Member member) {
        if (currentOrderId == null)
            createNewOrder(member);
        return orderRepository.findById(currentOrderId).get();
    }


    public void submitOrder() {
        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        currentOrder.setStatus(Status.CONFIRM);
        orderRepository.save(currentOrder);
        currentOrderId = null;
    }

    public void order(UUID menuId, AddCartRequest request,Member member) {
        if (currentOrderId == null)
            createNewOrder(member);


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
    public PurchaseOrder getById(UUID orderId) {
        return orderRepository.findById(orderId).get();
    }


    public void finishOrder(UUID orderId) {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        record.setStatus(Status.DELIVERED);
        orderRepository.save(record);
    }
}

