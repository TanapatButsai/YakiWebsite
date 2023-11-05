package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Category;
import ku.cs.YakinikuWebsite.entity.Member;

import ku.cs.YakinikuWebsite.repository.CategoryRepository;
import ku.cs.YakinikuWebsite.repository.MemberRepository;
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
}
