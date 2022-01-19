public class Couple {


    private Father first;
    private Father second;
    private int weight = 1;


    public Father getFirst() {
        return first;
    }

    public void setFirst(Father first) {
        this.first = first;
    }

    public Father getSecond() {
        return second;
    }

    public void setSecond(Father second) {
        this.second = second;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Couple{" +
                "first=" + first.getName() +
                ", second=" + second.getName() +
                '}';
    }


}
