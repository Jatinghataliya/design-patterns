package behavioral.chain_of_responsibility;

import behavioral.chain_of_responsibility.middleware.*;
import behavioral.chain_of_responsibility.server.WebServer;

import java.util.HashSet;
import java.util.Set;

/**
 * Demo runner for Chain of Responsibility Pattern.
 */
public class ChainOfResponsibilityDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   CHAIN OF RESPONSIBILITY DESIGN PATTERN DEMO    ");
        System.out.println("==================================================\n");

        // 1. Prepare valid system credentials
        Set<String> validTokens = new HashSet<>();
        validTokens.add("token_user_123");
        validTokens.add("token_admin_999");

        // 2. Build the middleware chain:
        // RateLimit (max 2 per IP) -> Authentication -> RoleAuthorization (/admin -> ADMIN) -> PayloadValidation
        MiddlewareHandler chain = MiddlewareHandler.link(
                new RateLimitMiddleware(2),
                new AuthenticationMiddleware(validTokens),
                new RoleAuthorizationMiddleware("/admin", "ADMIN"),
                new PayloadValidationMiddleware()
        );

        WebServer server = new WebServer();
        server.setMiddlewarePipeline(chain);

        // --- Scenario 1: Valid User Request ---
        System.out.println("--- Scenario 1: Valid Normal User Request ---");
        HttpRequest req1 = new HttpRequest(
                "/api/profile", "GET", "token_user_123", "192.168.1.10", "USER", null
        );
        server.processRequest(req1);

        // --- Scenario 2: Unauthenticated Request (Fails at Auth stage) ---
        System.out.println("--- Scenario 2: Request without Auth Token ---");
        HttpRequest req2 = new HttpRequest(
                "/api/profile", "GET", null, "192.168.1.20", "GUEST", null
        );
        server.processRequest(req2);

        // --- Scenario 3: Unauthorized Admin Access (Fails at Role Auth stage) ---
        System.out.println("--- Scenario 3: Normal User trying to access /admin/users ---");
        HttpRequest req3 = new HttpRequest(
                "/admin/users", "GET", "token_user_123", "192.168.1.30", "USER", null
        );
        server.processRequest(req3);

        // --- Scenario 4: Valid Admin POST with Malicious SQL Injection Body (Fails at Payload Validation) ---
        System.out.println("--- Scenario 4: Admin Request with Malicious Payload ---");
        HttpRequest req4 = new HttpRequest(
                "/admin/users", "POST", "token_admin_999", "192.168.1.40", "ADMIN", "{\"query\": \"DROP TABLE users;\"}"
        );
        server.processRequest(req4);

        // --- Scenario 5: Valid Admin POST Request ---
        System.out.println("--- Scenario 5: Valid Admin POST Request ---");
        HttpRequest req5 = new HttpRequest(
                "/admin/users", "POST", "token_admin_999", "192.168.1.40", "ADMIN", "{\"username\": \"john_doe\"}"
        );
        server.processRequest(req5);

        // --- Scenario 6: Rate Limiting Triggered (Same IP exceeds 2 requests) ---
        System.out.println("--- Scenario 6: Rate Limiter Blocks Third Request from Same IP (192.168.1.40) ---");
        HttpRequest req6 = new HttpRequest(
                "/admin/dashboard", "GET", "token_admin_999", "192.168.1.40", "ADMIN", null
        );
        server.processRequest(req6);
    }
}
