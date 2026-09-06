package behavioral.chain_of_responsibility.middleware;

import java.util.Set;

/**
 * Concrete Handler 2: Authentication Middleware.
 * Verifies if the request carries a valid, non-empty auth token.
 */
public class AuthenticationMiddleware extends MiddlewareHandler {
    private final Set<String> validTokens;

    public AuthenticationMiddleware(Set<String> validTokens) {
        this.validTokens = validTokens;
    }

    @Override
    public boolean handle(HttpRequest request) {
        String token = request.getAuthToken();

        if (token == null || token.trim().isEmpty()) {
            System.out.println("  [401 Unauthorized] Missing authentication token.");
            return false;
        }

        if (!validTokens.contains(token)) {
            System.out.printf("  [401 Unauthorized] Invalid or expired token: '%s'%n", token);
            return false;
        }

        System.out.println("  [Auth Check Passed] Valid bearer token verified.");
        return checkNext(request);
    }
}
