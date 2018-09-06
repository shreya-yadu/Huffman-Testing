/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreya
 */
import java.util.HashMap;

public class encoder 
{
    public static void main(String[] args) {
        String name_file = args[0];

        HashMap <Integer, Integer> input = Huffman.FreqTable_building (name_file);
        HashMap <Integer, String> codes = Huffman.build_4_heap(input);
        Huffman.encode ( codes, name_file );
    }
}
