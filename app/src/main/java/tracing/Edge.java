package tracing;

public final class Edge {

    public final Node src;
    public final Node dest;
    public final int weight;


    public Edge(final Node src, final Node dest, final int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}
