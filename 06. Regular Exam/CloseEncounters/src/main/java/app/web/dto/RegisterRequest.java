package app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Username must be at least 4 characters")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters!")
    private String username;

    @NotBlank(message = "Password must be at least 4 characters")
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters!")
    private String password;


}
