package com.vk.api.sdk.objects.photos.responses;

import com.google.gson.annotations.SerializedName;
import com.vk.api.sdk.objects.photos.Photo;

import java.util.List;
import java.util.Objects;

/**
 * GetUserPhotosResponse object
 */
public class GetUserPhotosResponse {
    /**
     * Total number
     */
    @SerializedName("count")
    private Integer count;

    @SerializedName("items")
    private List<Photo> items;

    public Integer getCount() {
        return count;
    }

    public List<Photo> getItems() {
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
        GetUserPhotosResponse getUserPhotosResponse = (GetUserPhotosResponse) o;
        return Objects.equals(count, getUserPhotosResponse.count) &&
                Objects.equals(items, getUserPhotosResponse.items);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetUserPhotosResponse{");
        sb.append("count=").append(count);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
