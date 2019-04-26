package com.vk.api.sdk.queries.app.widgets;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.app.widgets.ImageType;
import com.vk.api.sdk.objects.app.widgets.responses.GetGroupImageUploadServerResponse;

import java.util.Arrays;
import java.util.List;

public class AppWidgetsGetGroupImageUploadServerQuery extends AbstractQueryBuilder<AppWidgetsGetGroupImageUploadServerQuery, GetGroupImageUploadServerResponse> {

    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client     VK API client
     * @param actor      group actor with access token
     * @param imageType  type of image to upload
     */
    public AppWidgetsGetGroupImageUploadServerQuery(VkApiClient client, GroupActor actor, ImageType imageType) {
        super(client, "appWidgets.getGroupImageUploadServer", GetGroupImageUploadServerResponse.class);
        accessToken(actor.getAccessToken());
        imageType(imageType);
    }

    /**
     * Image type.
     *
     * @param value value of "image_type" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AppWidgetsGetGroupImageUploadServerQuery imageType(ImageType value) {
        return unsafeParam("image_type", value.getValue());
    }

    @Override
    protected AppWidgetsGetGroupImageUploadServerQuery getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("image_type", "access_token");
    }
}
