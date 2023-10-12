package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Category;
import ku.cs.YakinikuWebsite.entity.Menu;
import ku.cs.YakinikuWebsite.model.MenuRequest;
import ku.cs.YakinikuWebsite.repository.CategoryRepository;
import ku.cs.YakinikuWebsite.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


import java.util.List;
import java.util.UUID;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;



    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }

    public Menu getOneById(UUID id) {
        return menuRepository.findById(id).get();
    }


    public void createMenu(MenuRequest request){
        Menu record = modelMapper.map(request,Menu.class);
        Category category =
                categoryRepository.findById(request.getCategoryId()).get();
        record.setCategory(category);
        menuRepository.save(record);

    }
}
