package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.*;
import ku.cs.YakinikuWebsite.model.AddCartRequest;
import ku.cs.YakinikuWebsite.model.DiscountRequest;
import ku.cs.YakinikuWebsite.repository.*;
import ku.cs.YakinikuWebsite.status.DiscountStatus;
import ku.cs.YakinikuWebsite.status.Status;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    public PurchaseOrder getById(UUID orderId) {
        return orderRepository.findById(orderId).get();
    }

    public void finishOrder(UUID orderId) {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        if (record.getStatus() == Status.CONFIRM){
            record.setStatus(Status.ORDER_RECEIVED);
        } else if (record.getStatus() == Status.ORDER_RECEIVED){
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
}