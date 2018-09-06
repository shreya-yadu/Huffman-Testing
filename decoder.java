/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreya
 */
public class decoder 
{

    public static void main(String[] args) {
        try {
            String encoded_file_name = args[0];
            String code_table_file_name = args[1];
            Huffman.decode(encoded_file_name,code_table_file_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}    
    

