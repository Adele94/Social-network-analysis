package com.vk.api.sdk.objects.friends.responses;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.users.UserFull;

import java.util.List;
import java.util.Objects;

/**
 * GetSuggestionsResponse object
 */
public class GetSuggestionsResponse {
    /**
     * Total results number
     */
    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<UserFull> items;

    public Integer getCount() {
        return count;
    }

    public List<UserFull> getItems() {
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
        GetSuggestionsResponse getSuggestionsResponse = (GetSuggestionsResponse) o;
        return Objects.equals(count, getSuggestionsResponse.count) &&
                Objects.equals(items, getSuggestionsResponse.items);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetSuggestionsResponse{");
        sb.append("count=").append(count);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
