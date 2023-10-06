package ku.cs.YakinikuWebsite.model;



import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String username;
    private String Password;
    private String newPassword;
    private String confirmPassword;
}

