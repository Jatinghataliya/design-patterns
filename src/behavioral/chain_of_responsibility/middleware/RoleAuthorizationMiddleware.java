package behavioral.chain_of_responsibility.middleware;

/**
 * Concrete Handler 3: Role-Based Authorization Middleware.
 * Checks whether user role is permitted to access restricted endpoints (e.g. /admin/*).
 */
public class RoleAuthorizationMiddleware extends MiddlewareHandler {
    private final String requiredRole;
    private final String restrictedPathPrefix;

    public RoleAuthorizationMiddleware(String restrictedPathPrefix, String requiredRole) {
        this.restrictedPathPrefix = restrictedPathPrefix;
        this.requiredRole = requiredRole;
    }

    @Override
    public boolean handle(HttpRequest request) {
        if (request.getEndpoint().startsWith(restrictedPathPrefix)) {
            if (!requiredRole.equalsIgnoreCase(request.getUserRole())) {
                System.out.printf("  [403 Forbidden] Access to '%s' requires '%s' role, but user has '%s'.%n",
                        request.getEndpoint(), requiredRole, request.getUserRole());
                return false;
            }
        }

        System.out.printf("  [Authorization Passed] Role '%s' allowed on '%s'.%n",
                request.getUserRole(), request.getEndpoint());
        return checkNext(request);
    }
}
