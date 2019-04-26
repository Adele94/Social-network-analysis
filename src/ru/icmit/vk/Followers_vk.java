
package ru.icmit.vk;


import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.groups.GroupsArray;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.groups.responses.SearchResponse;
import com.vk.api.sdk.objects.users.User;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.objects.users.responses.GetFollowersResponse;
import com.vk.api.sdk.objects.users.responses.GetSubscriptionsResponse;
import com.vk.api.sdk.objects.utils.DomainResolved;
import com.vk.api.sdk.queries.users.UserField;
import com.vk.api.sdk.queries.users.UsersSearchRelation;
import com.vk.api.sdk.queries.utils.UtilsResolveScreenNameQuery;
import java.io.*;
import java.io.FileReader;


public class Followers_vk {

    private static final VkApiClient vk = new VkApiClient(new HttpTransportClient());
    private static final UserActor ua = new UserActor(
            31712689,
            "61b9758c61b9758c61b9758c8661d014b7661b961b9758c3d28736c0290f32f58c9c547"
    );

	public static void main(String[] args) throws InterruptedException {
        makeUserIdsFileFromUrls("kfu_students.txt", "kfu_students_vk_ids.txt");

        List<String> userIds = null;
        try {
            userIds = Files.readAllLines(Paths.get("kfu_students_vk_ids.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            List<UserXtrCounters> users = vk.users().get(ua).userIds(userIds).execute();

            Map<Integer, List<Integer>> usersGroups = new HashMap<>();
            for (UserXtrCounters user : users) {
                if (user.isClosed()) {
                    continue;
                }
                GetSubscriptionsResponse g = vk.users().getSubscriptions(ua).userId(user.getId()).execute();
                GroupsArray ga = g.getGroups();
                List<Integer> lf = ga.getItems();
               // System.out.println(user.toString());
                usersGroups.put(user.getId(), lf);
            }
            System.out.println(usersGroups);
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }

	}

    private static void makeUserIdsFileFromUrls(String userUrlsFileName, String userIdsFileName) {
        makeUserIdsFileFromUrls(userUrlsFileName, userIdsFileName, "create");
    }

	private static void makeUserIdsFileFromUrls(String userUrlsFileName, String userIdsFileName, String mode) {
        Path outputFile = Paths.get(userIdsFileName);

        if (mode.equals("create") && Files.exists(outputFile)) {
            return;
        }

        FileInputStream fstream = null;
        try {
            fstream = new FileInputStream(userUrlsFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        List<String> userIds = new LinkedList<>();

        try {
            while ((strLine = br.readLine()) != null) {
                URL url = new URL(strLine);
                String screenName = url.getPath().split("/", 3)[1];

                try {
                    userIds.add(vk.utils().resolveScreenName(ua, screenName).execute().getObjectId().toString());
                } catch (ClientException e) {
                    System.out.println("Vk user with following screen_name does not exist: " + screenName);
                }

                Thread.sleep(1000);
            }
        } catch (IOException | ApiException | InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Files.write(outputFile, userIds, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
