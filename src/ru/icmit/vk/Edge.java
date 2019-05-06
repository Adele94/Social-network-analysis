package ru.icmit.vk;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adele on 03/05/2019.
 */
public class Edge {
    public int groupIdA;
    public int groupIdB;
    public float affinity;

    public Edge(int groupIdA, int groupIdB, float affinity) {
        this.groupIdA = groupIdA;
        this.groupIdB = groupIdB;
        this.affinity = affinity;
    }

    public int compareByAffinity(Edge other) {
        return Float.compare(this.affinity, other.affinity);
    }

    public int compareByAffinityDecs(Edge other) {
        return -compareByAffinity(other);
    }

    @Override
    public String toString() {
        return String.format("%d - %d: %f", groupIdA, groupIdB, affinity);
    }
}
