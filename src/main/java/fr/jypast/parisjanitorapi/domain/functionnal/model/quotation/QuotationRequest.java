package fr.jypast.parisjanitorapi.domain.functionnal.model.quotation;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class QuotationRequest {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private String serviceRequested;
    @With
    private double estimatedCost;
}
