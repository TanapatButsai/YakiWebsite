package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Category;
import ku.cs.YakinikuWebsite.entity.Discount;
import ku.cs.YakinikuWebsite.entity.Member;

import ku.cs.YakinikuWebsite.entity.Menu;
import ku.cs.YakinikuWebsite.repository.CategoryRepository;
import ku.cs.YakinikuWebsite.repository.DiscountRepository;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
import ku.cs.YakinikuWebsite.status.DiscountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppInitializeService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DiscountRepository discountRepository;


    //for increment countLogin in member when login
    public void isManagerIsNull() {
        long record = memberRepository.count();
        if (record == 0){
            Member manager = new Member();
            manager.setUsername("manager");
            manager.setName("Manager Manager");
            manager.setEmail("yakinuku.ding@gmail.com");
            manager.setAddress("-");
            manager.setNote("Not note for address: This account is initialize when start the application for the first time.");
            String hashedPassword = passwordEncoder.encode("manager");
            manager.setPassword(hashedPassword);
            manager.setRole("ROLE_ADMIN");
            memberRepository.save(manager);
        }
    }

    public void isCategoryIsNull(){
        long count = categoryRepository.count();
        if (count == 0){
            Category recordPork = new Category();
            recordPork.setName("Pork");
            Category recordBeef = new Category();
            recordBeef.setName("Beef");
            Category recordChicken = new Category();
            recordChicken.setName("Chicken");
            Category recordDrink = new Category();
            recordDrink.setName("Drink");
            Category recordSet = new Category();
            recordSet.setName("Set");
            Category recordAppetizer = new Category();
            recordAppetizer.setName("Appetizer");
            categoryRepository.save(recordPork);
            categoryRepository.save(recordBeef);
            categoryRepository.save(recordChicken);
            categoryRepository.save(recordDrink);
            categoryRepository.save(recordSet);
            categoryRepository.save(recordAppetizer);
        }
    }

    public void isDiscountIsNull(){
        long count = discountRepository.count();
        if (count == 0){
            Discount recordDiscount5 = new Discount();
            recordDiscount5.setDiscountName("5per");
            recordDiscount5.setPercentDiscount(5);
            recordDiscount5.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount10 = new Discount();
            recordDiscount10.setDiscountName("10per");
            recordDiscount10.setPercentDiscount(10);
            recordDiscount10.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount15 = new Discount();
            recordDiscount15.setDiscountName("15per");
            recordDiscount15.setPercentDiscount(15);
            recordDiscount15.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount20 = new Discount();
            recordDiscount20.setDiscountName("20per");
            recordDiscount20.setPercentDiscount(20);
            recordDiscount20.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount25 = new Discount();
            recordDiscount25.setDiscountName("25per");
            recordDiscount25.setPercentDiscount(25);
            recordDiscount25.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount30 = new Discount();
            recordDiscount30.setDiscountName("30per");
            recordDiscount30.setPercentDiscount(30);
            recordDiscount30.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount35 = new Discount();
            recordDiscount35.setDiscountName("35per");
            recordDiscount35.setPercentDiscount(35);
            recordDiscount35.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount40 = new Discount();
            recordDiscount40.setDiscountName("40per");
            recordDiscount40.setPercentDiscount(40);
            recordDiscount40.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount45 = new Discount();
            recordDiscount45.setDiscountName("45per");
            recordDiscount45.setPercentDiscount(45);
            recordDiscount45.setDiscountStatus(DiscountStatus.ENABLE);
            Discount recordDiscount50 = new Discount();
            recordDiscount50.setDiscountName("50per");
            recordDiscount50.setPercentDiscount(50);
            recordDiscount50.setDiscountStatus(DiscountStatus.ENABLE);
            discountRepository.save(recordDiscount5);
            discountRepository.save(recordDiscount10);
            discountRepository.save(recordDiscount15);
            discountRepository.save(recordDiscount20);
            discountRepository.save(recordDiscount25);
            discountRepository.save(recordDiscount30);
            discountRepository.save(recordDiscount35);
            discountRepository.save(recordDiscount40);
            discountRepository.save(recordDiscount45);
            discountRepository.save(recordDiscount50);

        }
    }
}
