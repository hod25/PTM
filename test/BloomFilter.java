package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;
import java.util.ArrayList;

public class BloomFilter {
    private BitSet bitSet;
    private List<String> algorithms;
    private List<MessageDigest> hashFunctions;

    public BloomFilter(int size, String... hashFunctionNames) {
        this.bitSet = new BitSet(size);
        this.algorithms = new ArrayList<>(); // Initialize the list here
        this.hashFunctions = new ArrayList<>(); // Initialize the list here

        try {
            for (String hashFunctionName : hashFunctionNames) {
                MessageDigest md = MessageDigest.getInstance(hashFunctionName);
                this.hashFunctions.add(md);
                this.algorithms.add(hashFunctionName); // Add the algorithm name to the list
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    void add(String word)
    {
        for(String s : algorithms)
            {
                MessageDigest md;
                try {
                    md = MessageDigest.getInstance(s);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                md.reset();
                md.update(word.getBytes());
                byte[] digest = md.digest();
                BigInteger bi = new BigInteger(1, (digest));
                int k = bi.intValue();
                int index = Math.abs(k) % bitSet.size();
                bitSet.set(index);
            }
                    // System.out.println(bitSet.toString());
        }

    public boolean contains(String word) {
        for (String algorithm : algorithms) {
            try {
                MessageDigest md = MessageDigest.getInstance(algorithm);
                byte[] hashBytes = md.digest(word.getBytes());
                BigInteger hashNumber = new BigInteger(1, hashBytes);
                int index = Math.abs(hashNumber.intValue()) % bitSet.size();
                if (!bitSet.get(index)) {
                    return false;
                }
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSet.length(); i++) {
            sb.append(bitSet.get(i) ? "1" : "0");
            // System.out.println("Bit at index " + i + " is " + (bitSet.get(i) ? "1" : "0"));
        }
        // System.out.println("BitSet: " + sb.toString());
        return sb.toString();
    }
}
