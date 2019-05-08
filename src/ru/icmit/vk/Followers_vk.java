
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


    class Global{
    private static int NumberOfStudents = 1105;

    public static int getNumberOfStudents(){
        return Global.NumberOfStudents;
    }

    public static void setNumberOfStudents(int var){
        Global.NumberOfStudents = var;
    }
}

    public class Followers_vk {

    private static final VkApiClient vk = new VkApiClient(new HttpTransportClient());
    private static final UserActor ua = new UserActor(
            31712689,
            "61b9758c61b9758c61b9758c8661d014b7661b961b9758c3d28736c0290f32f58c9c547"
    );

    public static void main(String[] args) throws InterruptedException {


        String studentsFileName = "kfu_students.txt";
        String studentsFileNameShort = "kfu_students_short.txt";
        String studentsIdsFileName = "kfu_students_vk_ids.txt";
        String studentsIdsFileNameShort = "kfu_students_vk_ids_short.txt";
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
            if (group.size() > 5) {
                trimmedGroups.add(group);
            }
        });
        System.out.format(
                "Trimmed groups size: %d, filtred: %d %n",
                trimmedGroups.size(),
                sortedGroups.size() - trimmedGroups.size()
        );


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

        createJSON(trimmedGroups,graph);
	}

	/* Write graph to JSON file */
	private static void createJSON(List<Group> sortedGroups,List<Edge> graph){
        try(FileWriter writer = new FileWriter("blocks.json", false)){
            writer.write("{\"nodes\":[");
            int i = 0;
            int min = 1;
            int max = 5; // здесь примерное количество будущих кластеров.
                         // у каждого кластера будет свой номер

            int  LowerAffinityBoundary = 5;

            for ( i = 0; i < sortedGroups.size()-1; i++) {

                Global.getNumberOfStudents();

                //set Lower affinity boundary for each node
                if (sortedGroups.get(i).size()> Global.getNumberOfStudents()/3)
                    LowerAffinityBoundary= 5;
                else {
                    if (sortedGroups.get(i).size() > Global.getNumberOfStudents() / 5)
                         LowerAffinityBoundary = 10;
                    else {
                        if(sortedGroups.get(i).size() > Global.getNumberOfStudents() / 8)
                             LowerAffinityBoundary = 15;
                        else {
                            if(sortedGroups.get(i).size() > Global.getNumberOfStudents() / 11)
                                 LowerAffinityBoundary = 20;
                            else {
                                if(sortedGroups.get(i).size() > Global.getNumberOfStudents() / 22)
                                     LowerAffinityBoundary = 25;
                                else  LowerAffinityBoundary = 30;
                            }
                        }
                    }
                }

                //write nodes
                try {
                    writer.write("{");
                    writer.write(sortedGroups.get(i).printIdIdWithSize());
                    writer.write("\"cluster\":");
                    int diff = max - min;
                    int rnd = min + (int) (Math.random() * max);
                    writer.write(String.valueOf(rnd));
                    writer.write("},");
                    writer.flush();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            };

            // Для последней ноды записываем в файл без запятой
            writer.write("{");
            writer.write(sortedGroups.get(i).printIdIdWithSize());
            writer.write("\"cluster\":");
            int diff = max - min;
            int rnd = min + (int) (Math.random() * max);
            writer.write(String.valueOf(rnd));
            writer.write("}");
            writer.flush();
            writer.write("],");

            //write edges
            writer.write("\"links\":[");
            i = 0;
            for ( ; i < graph.size()-1; i++) {
                if (graph.get(i).affinity >  LowerAffinityBoundary) {
                    try {
                        writer.write("{");
                        writer.write(graph.get(i).printEdges());
                        writer.write(",");
                        writer.flush();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            };

            // Для последней связи выводим в файл без запятой и с любым  LowerAffinityBoundary
            try {
                writer.write("{");
                writer.write(graph.get(i).printEdges());
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            writer.write("]}");
            writer.append('\n');
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
            System.err.format("File %s not founded", userUrlsFileName);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;

        List<String> userIds = new LinkedList<>();
        int count = 0;
        try {
            while ((strLine = br.readLine()) != null) {
                URL url = new URL(strLine);
                String screenName = url.getPath().split("/", 3)[1];

                try {
                    userIds.add(vk.utils().resolveScreenName(ua, screenName).execute().getObjectId().toString());
                    count++;
                } catch (ClientException e) {
                    System.err.format("Vk user with following group does not exist: $s %n", screenName);
                } catch (ApiException e) {
                    System.err.println("Api Exception");
                }

                Thread.sleep(1000);
            }
            Global.setNumberOfStudents(count);
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
