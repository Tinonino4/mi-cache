package com.micache.application.usecases.impl;

import com.micache.application.usecases.AuthUserUseCase;
import com.micache.domain.exception.InvalidEmailException;
import com.micache.domain.exception.UserAlreadyExistsException;
import com.micache.infrastructure.adapters.UserRepository;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationRequest;
import com.micache.infrastructure.adapters.input.rest.model.AuthenticationResponse;
import com.micache.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserUseCaseImpl implements AuthUserUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Override
    public AuthenticationResponse execute(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidEmailException(""));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
