package behavioral.chain_of_responsibility.middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete Handler 1: Rate Limiting Middleware.
 * Rejects requests if a client IP exceeds max allowed requests per window.
 */
public class RateLimitMiddleware extends MiddlewareHandler {
    private final int maxRequestsPerIp;
    private final Map<String, Integer> requestCounts = new HashMap<>();

    public RateLimitMiddleware(int maxRequestsPerIp) {
        this.maxRequestsPerIp = maxRequestsPerIp;
    }

    @Override
    public boolean handle(HttpRequest request) {
        String ip = request.getClientIp();
        int count = requestCounts.getOrDefault(ip, 0) + 1;
        requestCounts.put(ip, count);

        if (count > maxRequestsPerIp) {
            System.out.printf("  [429 Too Many Requests] IP '%s' exceeded rate limit of %d req/min.%n",
                    ip, maxRequestsPerIp);
            return false; // Short-circuit: do not pass to next handler
        }

        System.out.printf("  [RateLimit Check Passed] IP '%s' (Request %d/%d)%n",
                ip, count, maxRequestsPerIp);
        return checkNext(request);
    }
}
