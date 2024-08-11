package fr.jypast.parisjanitorapi.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebRTCMessage {
    private String type;
    private String content;
    private String adventureId;
    private String userId;
}

