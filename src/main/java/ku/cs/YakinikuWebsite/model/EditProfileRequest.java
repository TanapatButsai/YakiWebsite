package ku.cs.YakinikuWebsite.model;

import lombok.Data;
@Data
public class EditProfileRequest {


        private String username;
        private String name;
        private String email;
        private String address;
        private String note;

}
