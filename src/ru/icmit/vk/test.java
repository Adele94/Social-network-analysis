/*package ru.icmit.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.client.actors.UserActor;


public class test {

    public static void main(String[] args) {

        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);


        UserAuthResponse authResponse = vk.oauth()
                .userAuthorizationCodeFlow(
                        6901138,
                        "b1b48c22b1b48c22b1b48c2216b1ddc1b0bb1b4b1b48c22ed3a3e10e071e25d1adfba5a",
                        "https://my_semester_project.com",

                )
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        System.out.println(authResponse.getAccessToken());
        System.out.println(authResponse.getUserId());

    }
}
*/