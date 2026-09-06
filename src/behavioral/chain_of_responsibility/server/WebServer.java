package behavioral.chain_of_responsibility.server;

import behavioral.chain_of_responsibility.middleware.HttpRequest;
import behavioral.chain_of_responsibility.middleware.MiddlewareHandler;

/**
 * WebServer handling client requests through its configured middleware pipeline.
 */
public class WebServer {
    private MiddlewareHandler middlewarePipeline;

    public void setMiddlewarePipeline(MiddlewareHandler middlewarePipeline) {
        this.middlewarePipeline = middlewarePipeline;
    }

    public void processRequest(HttpRequest request) {
        System.out.printf("%n>>> Processing %s %s from IP %s (Role: %s) <<<%n",
                request.getHttpMethod(), request.getEndpoint(), request.getClientIp(), request.getUserRole());

        if (middlewarePipeline != null) {
            boolean passed = middlewarePipeline.handle(request);
            if (!passed) {
                System.out.println(">>> Result: Request REJECTED by middleware pipeline. <<<\n");
                return;
            }
        }

        // Target business controller execution
        System.out.println("  [200 OK - Controller]: Request successfully processed by application backend.");
        System.out.println(">>> Result: Request COMPLETED successfully. <<<\n");
    }
}
