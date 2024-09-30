package fr.jypast.parisjanitorapi.domain.port.in.rankings;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;

public interface RankingApi {
	void addRanking(Rankings ranking);
	void updateRanking(Rankings ranking);
	
}
