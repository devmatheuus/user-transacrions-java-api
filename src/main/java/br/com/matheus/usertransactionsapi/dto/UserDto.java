package br.com.matheus.usertransactionsapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "The name field is required")
    @Size(min = 3, max = 92, message = "The name field must be between 3 and 92 characters")
    private String name;

    @NotEmpty(message = "The cpf field is required")
    @Size(min = 11, max = 11, message = "The cpf field must have 11 characters")
    private String cpf;

    @NotEmpty(message = "The email field is required")
    @Size(max = 62, message = "The email field must be less than 62 characters")
    @Email(message = "The email field must be a valid email")
    private String email;

    @NotEmpty(message = "The password field is required")
    private String password;

    @NotEmpty(message = "The type field is required")
    @Pattern(regexp = "(COMMON|SELLER)", message = "The type field must be COMMON or SELLER (case sensitive)")
    private String type;

}