package com.vk.api.sdk.queries.stats;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.base.responses.OkResponse;

import java.util.Arrays;
import java.util.List;

/**
 * Query for Stats.trackVisitor method
 */
public class StatsTrackVisitorQuery extends AbstractQueryBuilder<StatsTrackVisitorQuery, OkResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client VK API client
     * @param actor  actor with access token
     */
    public StatsTrackVisitorQuery(VkApiClient client, UserActor actor) {
        super(client, "stats.trackVisitor", OkResponse.class);
        accessToken(actor.getAccessToken());
    }

    @Override
    protected StatsTrackVisitorQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("access_token");
    }
}
