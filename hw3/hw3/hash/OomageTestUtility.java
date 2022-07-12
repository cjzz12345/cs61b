package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
      int[] buckets = new int[M];
      for (Oomage o : oomages) {
          int bucketnum = (o.hashCode() & 0x7FFFFFFF) % M;
          buckets[bucketnum]++;
      }
      for (int b : buckets) {
          if (b < oomages.size() / 50 || b > oomages.size() / 2.5) {
              return false;
          }
      }
        return true;
    }
}
