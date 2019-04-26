package ru.icmit.vk;

import java.util.ArrayList;
import java.util.List;


import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.queries.groups.GroupField;


public class VkGroups {

	public static void main(String[] args) {
		TransportClient transportClient = new HttpTransportClient();
		VkApiClient vk = new VkApiClient(transportClient);

		UserActor ua = new UserActor(31712689, "d19d5e9b37ad36e13a2a4fd2e67911bef309c14f006a57371d4827ad86dad575f58b0425e0a76dac6a78f");
		
		try {
			GetResponse getResponse = vk.groups().get(ua).userId(31712689).count(1000).execute();
			
			List<Integer> groupIDList = getResponse.getItems();
			
			ArrayList<GroupField> gfs = new ArrayList<>();
			gfs.add(GroupField.SCREEN_NAME);
			gfs.add(GroupField.DESCRIPTION);
			gfs.add(GroupField.MEMBERS_COUNT);
			
			for (int gId : groupIDList) {
				List<GroupFull> gr = vk.groups().getById(ua).
						groupId(String.valueOf(gId)).fields(gfs).execute();
				//System.out.println("gId=" + gId +", gr count = "+gr.size());
				Thread.sleep(500);
				if(gr!=null&&gr.size()>0)
					System.out.println(gr.get(0).getName());
					//System.out.println("name: "+gr.get(0).getName()+", description: "
							//+gr.get(0).getDescription()+", member count: "+gr.get(0).getMembersCount());
			}
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
