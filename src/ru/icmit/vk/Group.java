package ru.icmit.vk;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Adele on 03/05/2019.
 */
public class Group {
    final private int id;
    private Set<Integer> participants;

    public Group(int id) {
        this.id = id;
        this.participants = new HashSet<>();
    }

    public Group(int id, Set<Integer> participants) {
        this.id = id;
        this.participants = participants;
    }

    public int getId() {
        return id;
    }

    public Set<Integer> getParticipants() {
        return participants;
    }

    public Group addParticipant(int participant) {
        participants.add(participant);
        return this;
    }

    public int size() {
        return participants.size();
    }

    public int compareBySize(Group other) {
        return Integer.compare(this.size(), other.size());
    }

    public int compareBySizeDesc(Group other) {
        return -compareBySize(other);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        return id == group.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Size: %d", id, size());
    }

    public String printIdIdWithSize() {
        return String.format("\"id\":\"%s\",\"diameter\": %d ,", id, size());
    }
    public String printId() {
        return String.format("\"%s\"", id);
    }
}
