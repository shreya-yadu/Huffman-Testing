
import java.util.Scanner;
import java.io.File;
import java.io.BufferedWriter;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Huffman {

    public static final int MAX_RANGE = 1000000;
    static final String CODE_TABLE = "code_table.txt";
    static final String ENCODED_BIN = "encoded.bin";
    static final String DECODED_TXT = "decoded.txt";
    static final String INPUT = "sample1/sample_input_small.txt";

    public static void main(String[] args) 
    {
        HashMap< Integer, Integer> input = FreqTable_building(INPUT);
        int run_loop = 1;
        long start_time = System.currentTimeMillis();
        for (int i = 0; i < run_loop; i++) {
            build_btree(input);
        }
        System.out.println("Binary or 2 way Heap : " + (System.currentTimeMillis()
                - start_time) + " ms");

        start_time = System.currentTimeMillis();
        for (int i = 0; i < run_loop; i++) {
            build_4tree(input);
        }
        System.out.println("Four way Heap : " + (System.currentTimeMillis()
                - start_time) + " ms");

        start_time = System.currentTimeMillis();
        for (int i = 0; i < run_loop; i++) {
            pairing_heaptree(input);
        }
        System.out.println("Pairing Heap : " + (System.currentTimeMillis()
                - start_time) + " ms");
    }

    public static HashMap<Integer, Integer> FreqTable_building(String fileName) {
        long L = System.currentTimeMillis();
        HashMap<Integer, Integer> map = new HashMap<>();
        try (Scanner sc = new Scanner(new File(fileName))) {
            int[] new_Array = new int[MAX_RANGE];
            while (sc.hasNext()) {
                String nxt_Line = sc.nextLine();
                int next = Integer.parseInt(nxt_Line);
                new_Array[next]++;
            }
            for (int i = 0; i < new_Array.length; i++) {
                if (new_Array[i] > 0) {
                    map.put(i, new_Array[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Total Time taken by this method to build the frequency "
                + "table : " + (System.currentTimeMillis() - L));
        return map;
    }

    public static void pairing_heaptree(HashMap<Integer, Integer> input) {
        PairingHeap PH = new PairingHeap();
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            PH.insert(entry.getValue(), entry.getKey());
        }
        PH.buildTree();
        HashMap<Integer, String> codes = new HashMap<>();
        PH.calculate_codes(PH.extract_Root(), "", codes);
        encode(codes, INPUT);
        decode(ENCODED_BIN, CODE_TABLE);
    }

    public static void build_btree(HashMap<Integer, Integer> input) {
        HashMap<Integer, String> codes = build_b_heap(input);
        encode(codes, INPUT);
        decode(ENCODED_BIN, CODE_TABLE);
    }

    public static HashMap<Integer, String> build_b_heap(HashMap<Integer, Integer> input) {
        long L = System.currentTimeMillis();
        binaryheap bheap = new binaryheap(2 * input.size());
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            bheap.insert(entry.getValue(), entry.getKey());
        }
        System.out.println("heap inserted");
        bheap.Treebuild();
        System.out.println("tree build");
        HashMap<Integer, String> codes = new HashMap<>();
        bheap.populateCodes(bheap.ExtractRoot(), "", codes);
        System.out.println(" Total Time taken for building heap: " + (System.currentTimeMillis() - L));
        return codes;
    }

    public static void build_4tree(HashMap<Integer, Integer> input) {
        HashMap<Integer, String> codes = build_4_heap(input);
        encode(codes, INPUT);
        decode(ENCODED_BIN, CODE_TABLE);
    }

    public static HashMap<Integer, String> build_4_heap(HashMap<Integer, Integer> input) {
        long L = System.currentTimeMillis();
        fourwayheap fheap = new fourwayheap(2 * input.size());
        for (Map.Entry<Integer, Integer> entry : input.entrySet()) {
            fheap.insert(entry.getValue(), entry.getKey());
        }
        fheap.Treebuild();
        HashMap<Integer, String> codes = new HashMap<>();
        fheap.populateCodes(fheap.ExtractRoot(), "", codes);
        System.out.println("Total Time taken for building heap: " + (System.currentTimeMillis() - L));
        return codes;
    }

    public static void encode(HashMap<Integer, String> codes, String file_name) 
    {

        write(codes);
        encode_data(codes, file_name);
    }

    public static void decode(String encoded_file_name, String code_table_file_name) 
    {
        long t1 = System.currentTimeMillis();
        HashMap<Integer, String> codes = read(code_table_file_name);
        Trie t = new Trie();
        int size = 0;
        for (Map.Entry<Integer, String> entry : codes.entrySet()) {
            t.insert(entry.getKey(), entry.getValue());
        }
        System.out.println("Built Trie in " + (System.currentTimeMillis() - t1) + "ms");
        StringBuilder content = read_encoded_data(encoded_file_name);
        t.populateData(content);
    }

    public static void write(HashMap<Integer, String> codes) 
    {
        long L = System.currentTimeMillis();
        try (BufferedWriter br = new BufferedWriter(new FileWriter(CODE_TABLE))) 
        {
            for (Map.Entry<Integer, String> entry : codes.entrySet()) 
            {
                br.write(entry.getKey() + " " + entry.getValue());
                br.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Unable to write code table");
        }
        System.out.println("Time to write a table to file : " + (System.currentTimeMillis() - L));
    }

    public static void encode_data(HashMap<Integer, String> codes, String input_file_name) {
        long L = System.currentTimeMillis();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ENCODED_BIN));
                Scanner sc = new Scanner(new File(input_file_name))) {

            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                String next = sc.nextLine();
                if (!next.isEmpty()) {
                    String code = codes.get(Integer.parseInt(next));
                    bw.write(code);
                }
            }
        } catch (IOException ioe) {
            System.err.println("Unable to encode");
        }
        System.out.println("Time for encoding to binary : " + (System.currentTimeMillis() - L));
    }

    public static HashMap<Integer, String> read(String fileName) {
        long t1 = System.currentTimeMillis();
        HashMap<Integer, String> codes = new HashMap<>();
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String[] next = sc.nextLine().split(" ");
                codes.put(Integer.parseInt(next[0]), next[1]);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Can not read codetable");
        }
        System.out.println("Codetable read in  " + (System.currentTimeMillis() - t1) + "ms");
        return codes;
    }

    public static StringBuilder read_encoded_data(String encoded_file_name) {
        StringBuilder sb = new StringBuilder("");
        long L = System.currentTimeMillis();
        try {
            sb.append(new String(Files.readAllBytes(Paths.get(encoded_file_name))));
            System.out.println("Encoded file read in " + (System.currentTimeMillis() - L) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    static class Trie {

        private TrieNode root = new TrieNode();

        public void insert(int data, String path) {
            if (path == null || path.isEmpty()) {
                return;
            }
            TrieNode ptr = root;
            for (int i = 0; i < path.length(); i++) {
                char c = path.charAt(i);
                if (ptr.child[c - '0'] == null) {
                    ptr.child[c - '0'] = new TrieNode();
                }
                ptr = ptr.child[c - '0'];
            }
            ptr.end = data;
        }

        public void populateData(StringBuilder content) {
            long L = System.currentTimeMillis();
            if (content == null || content.length() == 0) {
                return;
            }
            TrieNode ptr = root;
            try (BufferedWriter br = new BufferedWriter(new FileWriter(DECODED_TXT))) {

                for (int i = 0; i <= content.length(); i++) {
                    if (i < content.length()) {
                        char c = content.charAt(i);
                        if (ptr.child[c - '0'] == null) {
                            br.write("" + ptr.end);
                            br.newLine();
                            ptr = root;
                            i--;
                            continue;
                        }
                        ptr = ptr.child[c - '0'];
                    } else {
                        br.write("" + ptr.end);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(" Total Time taken for decoding " + (System.currentTimeMillis() - L) + "ms");

        }

        private class TrieNode {
            int end = -1;
            TrieNode[] child = new TrieNode[2];
        }
    }
}
