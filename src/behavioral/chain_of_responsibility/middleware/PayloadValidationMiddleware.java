package behavioral.chain_of_responsibility.middleware;

/**
 * Concrete Handler 4: Payload Data Validation Middleware.
 * Validates request payload for POST/PUT operations (e.g. non-empty and sanitation).
 */
public class PayloadValidationMiddleware extends MiddlewareHandler {

    @Override
    public boolean handle(HttpRequest request) {
        if ("POST".equalsIgnoreCase(request.getHttpMethod()) || "PUT".equalsIgnoreCase(request.getHttpMethod())) {
            String body = request.getRequestBody();
            if (body == null || body.trim().isEmpty()) {
                System.out.println("  [400 Bad Request] Empty request body not permitted for " + request.getHttpMethod());
                return false;
            }

            if (body.contains("<script>") || body.contains("DROP TABLE")) {
                System.out.println("  [400 Bad Request] Malicious content detected in request body.");
                return false;
            }
        }

        System.out.println("  [Payload Validation Passed] Request body is clean.");
        return checkNext(request);
    }
}
