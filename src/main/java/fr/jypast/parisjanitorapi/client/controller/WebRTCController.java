package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.WebRTCMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webrtc")
public class WebRTCController {

    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/offer")
    public ResponseEntity<String> handleOffer(@RequestBody WebRTCMessage offer) {
        messagingTemplate.convertAndSend("/topic/adventure/webrtc/" + offer.getAdventureId(), offer);
        return ResponseEntity.ok("Offer received and broadcasted");
    }

    @PostMapping("/answer")
    public ResponseEntity<String> handleAnswer(@RequestBody WebRTCMessage answer) {
        messagingTemplate.convertAndSend("/topic/adventure/webrtc/" + answer.getAdventureId(), answer);
        return ResponseEntity.ok("Answer received and broadcasted");
    }

    @PostMapping("/candidate")
    public ResponseEntity<String> handleCandidate(@RequestBody WebRTCMessage candidate) {
        messagingTemplate.convertAndSend("/topic/adventure/webrtc/" + candidate.getAdventureId(), candidate);
        return ResponseEntity.ok("Candidate received and broadcasted");
    }
}
