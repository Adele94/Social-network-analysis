package ru.icmit.vk;

import java.util.List;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
//import com.vk.api.sdk.objects.account.UserSettings;

public class TestVKAPI {

	public static void main(String[] args) throws InterruptedException {
		TransportClient transportClient = new HttpTransportClient();
		VkApiClient vk = new VkApiClient(transportClient);

		UserActor ua = new UserActor(494345417,
				"18db707c5b495b3683073cf71edfe460f258ea4ac1f3e1852dce96b65dcd38abc9f70e2c2a52e973201b0");

		try {
			com.vk.api.sdk.objects.friends.responses.GetResponse grf = vk.friends().get(ua).userId(10385605).execute();

			
			System.out.println("?????????? ??????: " + grf.getCount());

			List<Integer> lf = grf.getItems();
			for (int i = 0; i < lf.size(); i++) {
				Integer b = lf.get(i);
				Thread.sleep(500);
				System.out.println(b);
				VkUsers h1 = new VkUsers(494345417,
						"18db707c5b495b3683073cf71edfe460f258ea4ac1f3e1852dce96b65dcd38abc9f70e2c2a52e973201b0");
				
				String c = String.valueOf(b);
				
				System.out.println(h1.getVkUser(c).getFirstName());
			}

			//UserSettings info = vk.account().getProfileInfo(ua).execute();

			//System.out.println(info.getFirstName());

		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}

	}

}
