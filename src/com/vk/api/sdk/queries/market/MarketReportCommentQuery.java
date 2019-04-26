package com.vk.api.sdk.queries.market;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.base.responses.OkResponse;
import com.vk.api.sdk.queries.ReportReason;

import java.util.Arrays;
import java.util.List;

/**
 * Query for Market.reportComment method
 */
public class MarketReportCommentQuery extends AbstractQueryBuilder<MarketReportCommentQuery, OkResponse> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client    VK API client
     * @param actor     actor with access token
     * @param ownerId   value of "owner id" parameter.
     * @param commentId value of "comment id" parameter. Minimum is 0.
     * @param reason    value of "reason" parameter. Minimum is 0.
     */
    public MarketReportCommentQuery(VkApiClient client, UserActor actor, int ownerId, int commentId, ReportReason reason) {
        super(client, "market.reportComment", OkResponse.class);
        accessToken(actor.getAccessToken());
        ownerId(ownerId);
        commentId(commentId);
        reason(reason);
    }

    /**
     * Identifier of an item owner community
     * Note that community id in the 'owner_id' parameter should be negative number. For example 'owner_id'=-1 matches the [vk.com/apiclub|VK API] community
     *
     * @param value value of "owner id" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected MarketReportCommentQuery ownerId(int value) {
        return unsafeParam("owner_id", value);
    }

    /**
     * Comment id
     *
     * @param value value of "comment id" parameter. Minimum is 0.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected MarketReportCommentQuery commentId(int value) {
        return unsafeParam("comment_id", value);
    }

    /**
     * Complaint reason
     *
     * @param value value of "reason" parameter. Minimum is 0.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected MarketReportCommentQuery reason(ReportReason value) {
        return unsafeParam("reason", value);
    }

    @Override
    protected MarketReportCommentQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("reason", "owner_id", "comment_id", "access_token");
    }
}
