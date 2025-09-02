package com.example.eventmanagementbackend.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * AuthUtil provides simple header/body-based authorization checks.
 * This is a mock authorization mechanism as per task requirements.
 */
public final class AuthUtil {

    private AuthUtil() {}

    /**
     * PUBLIC_INTERFACE
     * Checks whether the caller has privileges to perform admin/organizer actions.
     * Accepted roles: ADMIN, ORGANIZER (case-insensitive).
     * If not authorized, throws 403.
     * @param role role string from header or request body
     */
    public static void requireOrganizerOrAdmin(String role) {
        if (role == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing role");
        }
        String r = role.trim().toUpperCase();
        if (!("ADMIN".equals(r) || "ORGANIZER".equals(r))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient role: " + role);
        }
    }

    /**
     * PUBLIC_INTERFACE
     * Normalizes header/body role strings.
     */
    public static String normalize(String role) {
        return role == null ? null : role.trim().toUpperCase();
    }
}
