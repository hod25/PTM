package test;

import java.util.HashMap;
import java.util.Map;
public class LFU implements CacheReplacementPolicy {
    private HashMap<String, Integer> frequencyMap;

    public LFU() {
        this.frequencyMap = new HashMap<>();
    }

    @Override
    public void add(String word) {
        frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
    }

    @Override
    public String remove() {
        String victim = null;
        int minFreq = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() < minFreq || (entry.getValue() == minFreq && entry.getKey().compareTo(victim) < 0)) {
                minFreq = entry.getValue();
                victim = entry.getKey();
            }
        }
        frequencyMap.remove(victim);
        return victim;
    }
}
