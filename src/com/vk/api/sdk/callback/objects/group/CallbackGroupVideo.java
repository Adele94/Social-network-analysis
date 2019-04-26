package com.vk.api.sdk.callback.objects.group;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anton Tsivarev on 11.08.17.
 */
public enum CallbackGroupVideo {

    @SerializedName("0")
    DISABLED(0),

    @SerializedName("1")
    OPEN(1),

    @SerializedName("2")
    LIMITED(2);

    private final Integer value;

    CallbackGroupVideo(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
