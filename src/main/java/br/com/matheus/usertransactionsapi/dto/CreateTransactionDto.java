package br.com.matheus.usertransactionsapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTransactionDto {
    @NotNull(message = "The payer_id field is required")
    private Long payer_id;

    @NotNull(message = "The payee_id field is required")
    private Long payee_id;

    @NotNull(message = "The value field is required")
    @DecimalMin(value = "0.01", message = "The value field must be greater than 0")
    private Float value;
}