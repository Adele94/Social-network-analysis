package com.vk.api.sdk.objects.places.responses;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.places.PlaceFull;

import java.util.List;
import java.util.Objects;

/**
 * SearchResponse object
 */
public class SearchResponse {
    /**
     * Total number
     */
    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<PlaceFull> items;

    public Integer getCount() {
        return count;
    }

    public List<PlaceFull> getItems() {
        return items;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, items);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResponse searchResponse = (SearchResponse) o;
        return Objects.equals(count, searchResponse.count) &&
                Objects.equals(items, searchResponse.items);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchResponse{");
        sb.append("count=").append(count);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
