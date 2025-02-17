package app.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "Username must be at least 4 characters")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters!")
    private String username;

    @NotNull(message = "Password must be at least 4 characters")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters!")
    private String password;
}
