package ru.icmit.vk;


import java.util.List;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.pages.Wikipage;
import com.vk.api.sdk.objects.pages.WikipageFull;
import com.vk.api.sdk.objects.users.responses.GetFollowersResponse;


public class Pages_vk {

	public static void main(String[] args) throws InterruptedException {
		TransportClient transportClient = new HttpTransportClient();
		VkApiClient vk = new VkApiClient(transportClient);

		UserActor ua = new UserActor(494345417,
								"8d1a830a90baf9e5be43886169f623a3f83cbc70b8034283341f133a7b8e9e58b019e0ac9dcd7d9bc3306");
				
		try {
			
			 WikipageFull g=vk.pages().get(ua).execute();
			 System.out.println(g.getTitle());
						

		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}

	}

}

