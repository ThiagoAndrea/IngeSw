public class Couple {


    private Father first;
    private Father second;


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

    @Override
    public String toString() {
        return "Couple{" +
                "first=" + first.getName() +
                ", second=" + second.getName() +
                '}';
    }


}
