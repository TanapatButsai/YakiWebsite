package ku.cs.YakinikuWebsite.controller;
import ku.cs.YakinikuWebsite.model.ChangePasswordRequest;
import ku.cs.YakinikuWebsite.model.SignupRequest;
import ku.cs.YakinikuWebsite.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class ChangePasswordController {
    @Autowired
    private ChangePasswordService changePasswordService;

    @GetMapping("/change_password")
    public String getChangePasswordPage() {
        return "change_password"; // Render the change_password.html page
    }

    @PostMapping("/change_password")
    public String changePassword(
            @RequestParam("oldPassword") String oldPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Authentication authentication,
            Model model) {

        // Check if the new passwords match
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("passwordMismatch", true);
            return "change_password";
        }

        // Get the authenticated user's username
        String username = authentication.getName();

        // Attempt to change the password
        boolean passwordChanged = changePasswordService.changePassword(username, oldPassword, newPassword);

        if (passwordChanged) {
            model.addAttribute("passwordChanged", true);
        } else {
            model.addAttribute("passwordChangeError", true);
        }

        return "change_password";
    }

}
