package test;
import java.util.LinkedHashMap;
import java.util.Map;
public class LRU implements CacheReplacementPolicy {
    private LinkedHashMap<String, Integer> usageMap;

    public LRU() {
        this.usageMap = new LinkedHashMap<>();
    }

    @Override
    public void add(String word) {
        usageMap.put(word, usageMap.size());
    }

    @Override
    public String remove() {
        String victim = null;
        int minUsage = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : usageMap.entrySet()) {
            if (entry.getValue() < minUsage) {
                minUsage = entry.getValue();
                victim = entry.getKey();
            }
        }
        usageMap.remove(victim);
        return victim;
    }
}
