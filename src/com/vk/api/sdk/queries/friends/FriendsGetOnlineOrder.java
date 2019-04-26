package com.vk.api.sdk.queries.friends;

import com.vk.api.sdk.queries.EnumParam;

/**
 * Created by Anton Tsivarev on 22.09.16.
 */
public enum FriendsGetOnlineOrder implements EnumParam {

    RANDOM("random"),
    HINTS("hints");

    private final String value;

    FriendsGetOnlineOrder(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
