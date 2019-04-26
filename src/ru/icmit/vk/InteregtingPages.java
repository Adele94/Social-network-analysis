package ru.icmit.vk;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupsArray;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsResponse;

public class InteregtingPages {

    public static void main(String[] args) throws InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        UserActor ua = new UserActor(494345417,
                "48ddbf5aa49026f29b09432fd0b359241eafc354596239abd5abdd1c45b2365228bd3e6f368456b21c3ae");
        try {

            //UsersGetSubscriptionsQuery ugsq = vk.users().getSubscriptions(ua);
            GetSubscriptionsResponse ugsq = vk.users().getSubscriptions(ua).userId(118932635).count(200).execute();
            GroupsArray ugsqList = ugsq.getGroups();
            System.out.println(ugsqList);

            /*for (int i = 0; i < ugsqList.getCount(); i++) {
                UsersGetSubscriptionsQuery b = ugsList.getId(i);
                String FriendsInfo = b + "," + String.valueOf(b) + System.getProperty("line.separator");//System.getProperty("line.separator") ????? ??????
                try (FileWriter writer = new FileWriter("InterestingPages_vk.txt", true)) {
                    // ?????? ???? ??????
                    writer.write(FriendsInfo);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }
}
