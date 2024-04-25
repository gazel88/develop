package net.srook.common.dto;

import static net.srook.common.utils.ValidateUtils.validateId;

public class PostMethodResponse {
    private static final String URL_SLASH = "/";
    private Long resourceId;
    private String resourceUrl;

    protected PostMethodResponse() {
    }

    private PostMethodResponse(final String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    private PostMethodResponse(final Long resourceId, final String resourceUrl) {
        this.resourceId = resourceId;
        this.resourceUrl = resourceUrl;
    }

    public static PostMethodResponse createPostMethodResponse(final Long resourceId, final String resourceUrl) {
        validateId(resourceId);
        return new PostMethodResponse(resourceId, resourceUrl + URL_SLASH + resourceId);
    }

    public static PostMethodResponse createPostMethodResponse(final String resourceUrl) {
        return new PostMethodResponse(resourceUrl);
    }

    public Long getResourceId() {
        return resourceId;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }
}
