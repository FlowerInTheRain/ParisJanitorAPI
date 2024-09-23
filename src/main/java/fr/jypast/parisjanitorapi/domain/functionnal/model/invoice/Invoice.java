package fr.jypast.parisjanitorapi.domain.functionnal.model.invoice;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.sql.Date;
import java.util.UUID;

@Value
@Builder
public class Invoice {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    UUID propertyId;
    @With
    Date issueDate;
    @With
    double amount;
    @With
    InvoiceStatus status;
}