package fr.jypast.parisjanitorapi.client.controller;

import fr.jypast.parisjanitorapi.client.dto.rankings.RankingRequest;
import fr.jypast.parisjanitorapi.client.mapper.RankingDtoMapper;
import fr.jypast.parisjanitorapi.client.service.AuthVerifierService;
import fr.jypast.parisjanitorapi.domain.functionnal.service.TokenControllerService;
import fr.jypast.parisjanitorapi.domain.port.in.rankings.RankingApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/providers-ranking")
public class RankingController {
	
	private final AuthVerifierService authVerifierService;
	private final TokenControllerService tokenControllerService;
	private final RankingApi rankingApi;
	
	
	@PostMapping
	@ResponseStatus(CREATED)
	public void addRanking(@RequestHeader HttpHeaders headers, @RequestBody RankingRequest request) {
		UUID token = authVerifierService.getToken(headers);
		var user = tokenControllerService.getUserByToken(token);
		rankingApi.addRanking(
				RankingDtoMapper.toDomain(user.getId(), request)
		);
	}
	
	@PutMapping
	@ResponseStatus(CREATED)
	public void updateRanking(@RequestHeader HttpHeaders headers, @RequestBody RankingRequest request) {
		UUID token = authVerifierService.getToken(headers);
		var user = tokenControllerService.getUserByToken(token);
		rankingApi.updateRanking(
				RankingDtoMapper.toDomain(user.getId(), request)
		);
	}
}
