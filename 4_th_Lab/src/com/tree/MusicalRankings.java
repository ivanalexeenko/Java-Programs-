package com.tree;

public class MusicalRankings implements Comparable<MusicalRankings> {
    String string;
    int ranking;

    public MusicalRankings(String string, int ranking) {
        this.string = string;
        this.ranking = ranking;
    }

    @Override
    public int compareTo(MusicalRankings o) {
      /*  if (this.ranking == o.ranking) {
            return 0;
        } else if (this.ranking < o.ranking) {
            return -1;
        } else {
            return 1;
        }*/
      return this.ranking - o.ranking;
    }
    public String toString() {
       return this.string.toString() + "->(rank:" + ranking + ")";
    }
}
