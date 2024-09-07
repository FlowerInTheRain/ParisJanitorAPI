package fr.jypast.parisjanitorapi.domain.functionnal.model.invoice;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class Invoice {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private LocalDate issueDate;
    @With
    private double amount;
    @With
    private InvoiceStatus status;
}