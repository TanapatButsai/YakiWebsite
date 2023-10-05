package ku.cs.YakinikuWebsite.controller;

import ku.cs.YakinikuWebsite.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String getAllMenus(Model model){
        model.addAttribute("menu",menuService.getAllMenu());
        return "menu-all";
    }

}
