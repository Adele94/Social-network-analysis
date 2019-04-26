package ru.icmit.vk;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.queries.wall.WallGetQuery;
import org.apache.commons.logging.Log;

public class wall {

    public static void main(String[] args) {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        UserActor ua = new UserActor(494345417,
                "21ce8c43359d36e49fad4db1dd5d11a3f866a86182ad4f5d773441b211a4f482f642d0ad9aaaf61bcd8a6");
        

            WallGetQuery wallGetQuery = vk.wall().get(ua).ownerId(16172225).count(10);
            System.out.println(wallGetQuery.count(10));


    }
}

