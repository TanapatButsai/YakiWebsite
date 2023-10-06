package ku.cs.YakinikuWebsite.service;

import ku.cs.YakinikuWebsite.entity.Menu;
import ku.cs.YakinikuWebsite.model.MenuRequest;
import ku.cs.YakinikuWebsite.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }

    public void createMenu(MenuRequest request){
        Menu record = modelMapper.map(request,Menu.class);
        menuRepository.save(record);
    }
}
