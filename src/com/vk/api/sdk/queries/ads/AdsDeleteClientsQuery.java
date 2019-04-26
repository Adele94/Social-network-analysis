package com.vk.api.sdk.queries.ads;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

import java.util.Arrays;
import java.util.List;

/**
 * Query for Ads.deleteClients method
 */
public class AdsDeleteClientsQuery extends AbstractQueryBuilder<AdsDeleteClientsQuery, Integer> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client    VK API client
     * @param actor     actor with access token
     * @param accountId value of "account id" parameter.
     * @param ids       value of "ids" parameter.
     */
    public AdsDeleteClientsQuery(VkApiClient client, UserActor actor, int accountId, String ids) {
        super(client, "ads.deleteClients", Integer.class);
        accessToken(actor.getAccessToken());
        accountId(accountId);
        ids(ids);
    }

    /**
     * Advertising account ID.
     *
     * @param value value of "account id" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AdsDeleteClientsQuery accountId(int value) {
        return unsafeParam("account_id", value);
    }

    /**
     * Serialized JSON array with IDs of deleted clients.
     *
     * @param value value of "ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AdsDeleteClientsQuery ids(String value) {
        return unsafeParam("ids", value);
    }

    @Override
    protected AdsDeleteClientsQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("ids", "account_id", "access_token");
    }
}
