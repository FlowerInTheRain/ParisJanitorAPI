package fr.jypast.parisjanitorapi.domain.functionnal.model.document;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Document {

    @Builder.Default
    @With
    UUID id = UUID.randomUUID();
    @With
    private UUID propertyId;
    @With
    private DocumentType documentType;
    @With
    private String url;
}
