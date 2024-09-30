package fr.jypast.parisjanitorapi.server.adapter;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Rankings;
import fr.jypast.parisjanitorapi.domain.port.out.RankingSpi;
import fr.jypast.parisjanitorapi.server.mapper.RankingsMapper;
import fr.jypast.parisjanitorapi.server.repository.RankingsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingsAdapter implements RankingSpi {
	
	private final RankingsRepository repository;
	@Override
	public void addRanking(Rankings ranking) {
		var toAdd = RankingsMapper.toEntity(ranking);
		repository.save(toAdd);
	}
	
	@Override
	public void updateRanking(Rankings ranking) {
		var toUpdate = repository.findByTargetAndOrigin(ranking.getOriginId(),ranking.getTargetId());
		toUpdate.setRanking(ranking.getRanking());
		toUpdate.setComment(ranking.getComment());
		repository.save(toUpdate);
	}
}
