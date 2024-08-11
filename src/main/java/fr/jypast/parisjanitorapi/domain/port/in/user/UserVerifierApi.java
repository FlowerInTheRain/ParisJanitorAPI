package fr.jypast.parisjanitorapi.domain.port.in.user;

public interface UserVerifierApi {

    boolean verify(String email, String code);

}
