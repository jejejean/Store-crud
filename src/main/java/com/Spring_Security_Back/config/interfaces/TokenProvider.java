package com.Spring_Security_Back.config.interfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String getToken(HttpServletRequest request);
}
