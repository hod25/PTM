package test;
import java.util.HashSet;
public class CacheManager {
    private HashSet<String> cache;
    private int maxSize;
    private CacheReplacementPolicy crp;

    public CacheManager(int maxSize, CacheReplacementPolicy crp) {
        this.maxSize = maxSize;
        this.crp = crp;
        this.cache = new HashSet<>();
    }

    public boolean query(String word) {
        return cache.contains(word);
    }

    public void add(String word) {
        crp.add(word);
        cache.add(word);
        if (cache.size() > maxSize) {
            String victim = crp.remove();
            cache.remove(victim);
        }
    }
}

