package com.vk.api.sdk.queries.ads;

import com.vk.api.sdk.client.AbstractQueryBuilder;
import com.vk.api.sdk.client.Utils;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.ads.TargSuggestionsRegions;

import java.util.Arrays;
import java.util.List;

/**
 * Query for Ads.getSuggestions method
 */
public class AdsGetSuggestionsQueryWithRegions extends AbstractQueryBuilder<AdsGetSuggestionsQueryWithRegions, List<TargSuggestionsRegions>> {
    /**
     * Creates a AbstractQueryBuilder instance that can be used to build api request with various parameters
     *
     * @param client  VK API client
     * @param actor   actor with access token
     * @param section value of "section" parameter.
     */
    public AdsGetSuggestionsQueryWithRegions(VkApiClient client, UserActor actor, AdsGetSuggestionsSection section) {
        super(client, "ads.getSuggestions", Utils.buildParametrizedType(List.class, TargSuggestionsRegions.class));
        accessToken(actor.getAccessToken());
        section(section);
    }

    /**
     * Section, suggestions are retrieved in.
     *
     * @param value value of "section" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    protected AdsGetSuggestionsQueryWithRegions section(AdsGetSuggestionsSection value) {
        return unsafeParam("section", value);
    }

    /**
     * Set ids
     *
     * @param value value of "ids" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public AdsGetSuggestionsQueryWithRegions ids(String value) {
        return unsafeParam("ids", value);
    }

    /**
     * Filter-line of the request (for 'countries, regions, cities, streets, schools, interests, positions').
     *
     * @param value value of "q" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public AdsGetSuggestionsQueryWithRegions q(String value) {
        return unsafeParam("q", value);
    }

    /**
     * ID of the country objects are searched in.
     *
     * @param value value of "country" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public AdsGetSuggestionsQueryWithRegions country(Integer value) {
        return unsafeParam("country", value);
    }

    /**
     * IDs of cities where objects are searched in, separated with a comma.
     *
     * @param value value of "cities" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public AdsGetSuggestionsQueryWithRegions cities(String value) {
        return unsafeParam("cities", value);
    }

    /**
     * Language of the returned string values.
     *
     * @param value value of "lang" parameter.
     * @return a reference to this {@code AbstractQueryBuilder} object to fulfill the "Builder" pattern.
     */
    public AdsGetSuggestionsQueryWithRegions lang(AdsGetSuggestionsLang value) {
        return unsafeParam("lang", value);
    }

    @Override
    protected AdsGetSuggestionsQueryWithRegions getThis() {
        return this;
    }

    @Override
    protected List<String> essentialKeys() {
        return Arrays.asList("section", "access_token");
    }
}
