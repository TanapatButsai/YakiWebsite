package ku.cs.YakinikuWebsite.controller;

import ku.cs.YakinikuWebsite.entity.Menu;
import ku.cs.YakinikuWebsite.model.MenuRequest;
import ku.cs.YakinikuWebsite.service.CategoryService;
import ku.cs.YakinikuWebsite.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String getAllMenus(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());

        return "menu-all";
    }
    //public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/templates/image";

    @GetMapping("/add")
    public String getMenuForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "menu-add";
    }
    @GetMapping("/{id}")
    public String getOneMenu(@PathVariable UUID id, Model model) {
        Menu menu = menuService.getOneById(id);
        model.addAttribute("menu", menu);
        return "menu-view";
    }


    @PostMapping("/add")
    public String createMenu(@ModelAttribute MenuRequest menu, Model model) {
//        StringBuilder fileNames = new StringBuilder();
//        String filename = menu.getName()+ file.getOriginalFilename().substring(file.getOriginalFilename().length()-4);
//        Path fileNameAndPath = Paths.get(uploadDirectory,filename);
//        Files.write(fileNameAndPath,file.getBytes());
//
//        menu.setMenuImage(filename);
        menuService.createMenu(menu);
        return "redirect:/menus";
    }

}
