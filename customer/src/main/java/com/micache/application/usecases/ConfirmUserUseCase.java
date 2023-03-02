package com.micache.application.usecases;

public interface ConfirmUserUseCase {
    String execute(String token, String userId);
}
