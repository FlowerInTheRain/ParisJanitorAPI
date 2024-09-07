package fr.jypast.parisjanitorapi.domain.functionnal.model.finance;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class FinancialSummary {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private double totalIncome;
    @With
    private double totalExpenses;
}