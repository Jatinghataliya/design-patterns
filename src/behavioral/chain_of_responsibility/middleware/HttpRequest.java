package behavioral.chain_of_responsibility.middleware;

/**
 * Request Model passed along the Chain of Responsibility.
 */
public class HttpRequest {
    private final String endpoint;
    private final String httpMethod;
    private final String authToken;
    private final String clientIp;
    private final String userRole;
    private final String requestBody;

    public HttpRequest(String endpoint, String httpMethod, String authToken, String clientIp, String userRole, String requestBody) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.authToken = authToken;
        this.clientIp = clientIp;
        this.userRole = userRole;
        this.requestBody = requestBody;
    }

    public String getEndpoint() { return endpoint; }
    public String getHttpMethod() { return httpMethod; }
    public String getAuthToken() { return authToken; }
    public String getClientIp() { return clientIp; }
    public String getUserRole() { return userRole; }
    public String getRequestBody() { return requestBody; }
}
