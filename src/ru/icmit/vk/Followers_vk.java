
package ru.icmit.vk;


import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.lang.*;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.groups.GroupsArray;
import com.vk.api.sdk.objects.groups.responses.GetResponse;
import com.vk.api.sdk.objects.groups.responses.SearchResponse;
import com.vk.api.sdk.objects.users.LastSeen;
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

        String studentsFileName = "kfu_students_short.txt";
        String studentsIdsFileName = "kfu_students_vk_ids.txt";
        makeUserIdsFileFromUrls(studentsFileName, studentsIdsFileName);

        List<String> userIds = null;
        try {
            userIds = Files.readAllLines(Paths.get(studentsIdsFileName));
        } catch (IOException e) {
            System.err.format("Can't read file: %s %n", studentsIdsFileName);
        }

        List<UserXtrCounters> users = new ArrayList<>();
        try {
            users = vk.users().get(ua).userIds(userIds).execute();
        } catch (ApiException | ClientException e) {
            System.err.println("Can't extract users");
            return;
        }
        System.out.format("Users size: %d %n", users.size());

        Map<Integer, Group> groups = new HashMap<>();
        users.forEach(user -> {
            if (user.isClosed()) {
                return;
            }
            try {
                GetSubscriptionsResponse g = vk.users().getSubscriptions(ua).userId(user.getId()).execute();
                GroupsArray groupsArray = g.getGroups();
                List<Integer> groupsIds = groupsArray.getItems();
                groupsIds.forEach(groupId -> {
                    groups.put(
                            groupId,
                            groups.getOrDefault(groupId, new Group(groupId)).addParticipant(user.getId())
                    );
                });
            } catch (ApiException | ClientException e) {
                System.err.format("Can't extract subscriptions for user: %d %n", user.getId());
            }
        });
        System.out.format("Groups size: %d %n", groups.size());

        /* Sort groups by size descending */
        List<Group> sortedGroups = new ArrayList(groups.values());
        Collections.sort(
                sortedGroups,
                (Comparator<Group>) (group1, group2) -> group1.compareBySizeDesc(group2)
        );

        /* Filter group if it has only one participant */
        List<Group> trimmedGroups = new ArrayList();
        sortedGroups.forEach(group -> {
            if (group.size() > 1) {
                trimmedGroups.add(group);
            }
        });
        System.out.format(
                "Trimmed groups size: %d, filtred: %d %n",
                trimmedGroups.size(),
                sortedGroups.size() - trimmedGroups.size()
        );


        try(FileWriter writer = new FileWriter("GroupsWithSize.txt", false)){
            sortedGroups.forEach(group -> {
                    try {
                        writer.write(group.toString());
                        writer.append('\n');
                        writer.flush();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

/*        sortedGroups.forEach(group -> {
            System.out.println(group.toString());
        });*/


        /* Create graph */
        GraphFactory graphFactory = new GraphFactory(trimmedGroups);
        List<Edge> graph = new ArrayList<>();
        for (int groupIdx1 = 0; groupIdx1 < trimmedGroups.size(); groupIdx1++) {
            for (int groupIdx2 = groupIdx1 + 1; groupIdx2 < trimmedGroups.size(); groupIdx2++) {
                graph.add(graphFactory.createEdge(
                        trimmedGroups.get(groupIdx1),
                        trimmedGroups.get(groupIdx2)
                ));
            }
        }
        Collections.sort(
                graph,
                (Comparator<Edge>) (edge1, edge2) -> edge1.compareByAffinityDecs(edge2)
        );

        System.out.println();
        try(FileWriter writer = new FileWriter("EdgesOfGraphWithAffinity.txt", false)){
            graph.forEach(edge -> {
                if (edge.affinity > 1e-5) {
                    try {
                        writer.write(edge.toString());
                        writer.append('\n');
                        writer.flush();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //System.out.println("Graph: ");
       /* graph.forEach(edge -> {
            if (edge.affinity > 1e-5) {
                System.out.println(edge.toString());
            }
        });*/
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
            System.err.format("File %s not founded", userUrlsFileName);
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
                    System.err.format("Vk user with following group does not exist: $s %n", screenName);
                } catch (ApiException e) {
                    System.err.println("Api Exception");
                }

                Thread.sleep(1000);
            }
        } catch (IOException e) {
            System.err.format("Can't read file: %s %n", userUrlsFileName);
        } catch (InterruptedException e) {
            System.err.println("Sleep exception");
        }

        try {
            Files.write(outputFile, userIds, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.err.format("Can't write file: %s %n", userIdsFileName);
        }
    }
}
