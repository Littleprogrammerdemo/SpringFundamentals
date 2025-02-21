package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EditProfileRequest {

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;

    @Email(message = "Enter a valid email address")
    private String email;

    //@Pattern(regexp = "^(https)", message = "Enter a valid URL")
    private String pictureUrl;
}
