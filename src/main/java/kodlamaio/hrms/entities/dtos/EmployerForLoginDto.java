package kodlamaio.hrms.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployerForLoginDto {

    @NotNull(message = "Email boş geçilemez.")
    @NotBlank(message = "Email boş geçilemez.")
    private String email;

    @NotNull(message = "Şifre boş geçilemez.")
    @NotBlank(message = "Şifre boş geçilemez.")
    private String password;
}
