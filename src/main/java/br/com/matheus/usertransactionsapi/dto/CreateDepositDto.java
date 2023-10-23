package br.com.matheus.usertransactionsapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDepositDto {
    @NotNull(message = "The value field is required")
    @DecimalMin(value = "0.01", message = "The value field must be greater than 0")
    private float value;
}