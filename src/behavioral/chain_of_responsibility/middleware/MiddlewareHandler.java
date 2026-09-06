package behavioral.chain_of_responsibility.middleware;

/**
 * Base Handler in the Chain of Responsibility.
 * Provides the boilerplate code for linking handlers together and passing the request along.
 */
public abstract class MiddlewareHandler {
    private MiddlewareHandler next;

    /**
     * Helper method to build fluent chains: handler1.linkWith(handler2).linkWith(handler3);
     */
    public static MiddlewareHandler link(MiddlewareHandler first, MiddlewareHandler... chain) {
        MiddlewareHandler head = first;
        for (MiddlewareHandler nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    public MiddlewareHandler linkWith(MiddlewareHandler nextHandler) {
        this.next = nextHandler;
        return nextHandler;
    }

    /**
     * Handles the request. Subclasses implement specific check logic.
     * Returns true if processing succeeded through the rest of the chain; false if rejected.
     */
    public abstract boolean handle(HttpRequest request);

    /**
     * Passes the request to the next handler in the chain.
     */
    protected boolean checkNext(HttpRequest request) {
        if (next == null) {
            return true; // Reached end of the chain successfully
        }
        return next.handle(request);
    }
}
