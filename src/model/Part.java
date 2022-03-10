package model;

public class Part {
    private TopologyType type;
    private int indexStart;
    private int count;

    public Part(TopologyType type, int indexStart, int count) {
        this.type = type;
        this.indexStart = indexStart;
        this.count = count;
    }

    public TopologyType getType() {
        return type;
    }

    public int getIndexStart() {
        return indexStart;
    }

    public int getCount() {
        return count;
    }
}
