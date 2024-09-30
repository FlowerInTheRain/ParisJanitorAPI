package fr.jypast.parisjanitorapi.domain.functionnal.service.ranking;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;
import fr.jypast.parisjanitorapi.domain.port.in.rankings.RankingApi;
import fr.jypast.parisjanitorapi.domain.port.out.RankingSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingService implements RankingApi {
	private final RankingSpi rankingSpi;
	@Override
	public void addRanking(Rankings ranking) {
		if(ranking.getRanking() >= 0 && ranking.getRanking() <= 5) {
			rankingSpi.addRanking(ranking);
		} else {
			throw new RuntimeException("Rankings must be between 0 and 5");
		}
	}
	
	@Override
	public void updateRanking(Rankings ranking) {
		if(ranking.getRanking() >= 0 && ranking.getRanking() <= 5) {
			rankingSpi.addRanking(ranking);
		} else {
			throw new RuntimeException("Rankings must be between 0 and 5");
		}
	}
}
