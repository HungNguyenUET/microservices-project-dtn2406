package vti.dtn.auth_service.oauth2.user;

import java.util.Map;

public class GithubOAuth2UserInfo extends OAuth2UserInfo {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String AVATAR_URL = "avatar_url";

    protected GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get(ID);
    }

    @Override
    public String getName() {
        return (String) attributes.get(NAME);
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(EMAIL);
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get(AVATAR_URL);
    }
}
