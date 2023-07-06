package com.sales.service;

import com.sales.dtos.AuthRequest;
import com.sales.dtos.AuthResponse;
import com.sales.entity.User;

public interface SecurityService {
    public AuthResponse login(AuthRequest request);
    public User register(AuthRequest request);
}
