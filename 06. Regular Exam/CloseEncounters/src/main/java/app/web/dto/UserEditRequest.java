package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
public class UserEditRequest {

    @Size(min = 2, max = 20, message = "First name length must be between 2 and 20 characters")
    private String firstName;

    @Size(min = 2, max = 20, message = "Last name length must be between 2 and 20 characters")
    private String lastName;

    @Email(message = "Invalid email format. Email must contain '@'.")
    private String email;

    @URL(message = "Profile picture must contain a valid URL.")
    private String profilePicture;
}
