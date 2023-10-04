package ku.cs.YakinikuWebsite.controller;
import ku.cs.YakinikuWebsite.model.ChangePasswordRequest;
import ku.cs.YakinikuWebsite.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class ChangePasswordController {

    @RequestMapping("/change_password")
    public String getCChangePasswordControllerPage(){
        return "change_password";
    }
//    @Autowired
//    private ChangePasswordService changePasswordService;
//
//    @GetMapping("/form")
//    public String getChangePasswordForm(Model model) {
//        return "change_password";
//    }
//
//    @PostMapping("/submit")
//    public String submitChangePasswordForm(@ModelAttribute("changePasswordRequest") ChangePasswordRequest request, Model model) {
//        return "change_password";
//    }
}
