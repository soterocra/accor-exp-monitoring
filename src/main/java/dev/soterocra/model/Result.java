package dev.soterocra.model;

import java.util.List;

public class Result {

    private List<Item> added;
    private List<Item> removed;
    private List<Item> sustained;

    public Result(List<Item> added, List<Item> removed, List<Item> sustained) {
        this.added = added;
        this.removed = removed;
        this.sustained = sustained;
    }

    public List<Item> getAdded() {
        return added;
    }

    public void setAdded(List<Item> added) {
        this.added = added;
    }

    public List<Item> getRemoved() {
        return removed;
    }

    public void setRemoved(List<Item> removed) {
        this.removed = removed;
    }

    public List<Item> getSustained() {
        return sustained;
    }

    public void setSustained(List<Item> sustained) {
        this.sustained = sustained;
    }

    @Override
    public String toString() {
        return "{\"Result\":{"
                + "\"added\":" + added
                + ", \"removed\":" + removed
                + ", \"sustained\":" + sustained
                + "}}";
    }
}
