package fr.jypast.parisjanitorapi.domain.port.out;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;

public interface RankingSpi {
	void addRanking(Rankings ranking);
	void updateRanking(Rankings ranking);
	
}
