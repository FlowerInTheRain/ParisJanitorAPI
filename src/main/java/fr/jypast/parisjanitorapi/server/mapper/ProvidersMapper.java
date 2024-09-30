package fr.jypast.parisjanitorapi.server.mapper;

import fr.jypast.parisjanitorapi.domain.functionnal.model.rankings.Providers;
import fr.jypast.parisjanitorapi.server.entity.ProvidersEntity;

public interface ProvidersMapper {
	static ProvidersEntity toEntity(Providers provider){
		return ProvidersEntity.builder()
										 .id(provider.getId())
										 .email(provider.getEmail())
										 .phoneNumber(provider.getPhoneNumber())
										 .fullName(provider.getFullName())
										 .joinDate(provider.getJoinDate())
										 .joinRequestMessage(provider.getJoinRequestMessage())
										 .joinRequestStatus(provider.getJoinRequestStatus())
										 .build();
	}
	
	static Providers toDomain(ProvidersEntity provider){
		return Providers.builder()
					   .id(provider.getId())
					   .email(provider.getEmail())
					   .phoneNumber(provider.getPhoneNumber())
					   .fullName(provider.getFullName())
					   .joinDate(provider.getJoinDate())
					   .joinRequestMessage(provider.getJoinRequestMessage())
					   .joinRequestStatus(provider.getJoinRequestStatus())
					   .build();
	}
}
