import java.util.List;
import java.util.ArrayList;

class Solution {
    
    private int n;
    private int k;

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        this.n = n;
        this.k = k;
        combine(1, new ArrayList<>(), result);
        return result;
    }

    private void combine(int current, List<Integer> path, List<List<Integer>> result) {
        if (k == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = current; i <= n; i++) {
            path.add(i);
            combine(i + 1, path, result); 
            path.remove(path.size() - 1);
        }
    }
}