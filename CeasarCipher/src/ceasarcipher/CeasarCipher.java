/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ceasarcipher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CeasarCipher {

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789,./?!";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = input.nextInt();
            input.nextLine();
            if (choice == 1) {
                System.out.println("choose a file to decrypt");
                System.out.println("1-file-e-1.txt");
                System.out.println("2-file-e-2.txt");
                System.out.println("3-file-e-3.txt");
                System.out.println("4-file-e-4.txt");
                String filename = "";
                System.out.print("Choice: ");

                int choiceFile = input.nextInt();

                switch (choiceFile) {
                    case 1:
                        filename = "file-e-1.txt";

                        break;
                    case 2:
                        filename = "file-e-2.txt";

                        break;
                    case 3:
                        filename = "file-e-3.txt";

                        break;
                    case 4:
                        filename = "file-e-4.txt";

                        break;
                }
                ArrayList<String> para = decryptFile(filename);
                String outputFile = "";
                for (int i = 0; i < filename.length(); i++) {
                    if (filename.charAt(i) == 'e' && i > 3) {
                        outputFile += 'd';
                        continue;
                    }
                    outputFile += filename.charAt(i);
                }

                System.out.println();

                String[] commonWords = {"the", "and", "that", "have", "for", "with", "you", "this", "but", "not"};
                String decrypted = "";
                int key = 0;
                boolean iskey = false;
                PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

                for (String line : para) {
                    for (key = 0; key < ALPHABET.length(); key++) {
                        decrypted = decryptParagraph(line, key);
                        for (String word : commonWords) {
                            if (decrypted.toLowerCase().contains(" " + word + " ")) {
                                iskey = true;
                            }
                        }
                        if (iskey) {
                            break;
                        }
                    }
                    iskey = false;
                    if (!decrypted.isEmpty()) {

                        writer.println("Key = " + key + "\n" + decrypted);

                        // System.out.println(filename + " is decrpyted");
                    }

                }
                writer.close();
                System.out.println("Decryption completed! Output saved in: " + outputFile);

            } else if (choice == 2) {
                displayFile();
            }

        }

    }

    public static ArrayList<String> decryptFile(String fileName) throws FileNotFoundException {
        File infile = new File(fileName);
        Scanner read = new Scanner(new File(fileName));
        ArrayList<String> paragraphs = new ArrayList<>();

        while (read.hasNext()) {
            paragraphs.add(read.nextLine());
        }
        return paragraphs;
    }

    public static String decryptParagraph(String text, int key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int index = ALPHABET.indexOf(ch);
            if (index != -1) {
                int newIndex = (index - key + ALPHABET.length()) % ALPHABET.length();
                result += ALPHABET.charAt(newIndex);
            } else {
                result += ch;
            }

        }
        return result;
    }

    public static void displayFile() throws FileNotFoundException {

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("choose a file to display");
            System.out.println("1-file-d-1.txt");
            System.out.println("2-file-d-2.txt");
            System.out.println("3-file-d-3.txt");
            System.out.println("4-file-d-4.txt");
            String filename = "";
            int choiceFile = input.nextInt();
            switch (choiceFile) {
                case 1:
                    filename = "file-d-1.txt";

                    break;
                case 2:
                    filename = "file-d-2.txt";

                    break;
                case 3:
                    filename = "file-d-3.txt";

                    break;
                case 4:
                    filename = "file-d-4.txt";

                    break;
            }
            File infile = new File(filename);

            if (!infile.canRead()) {
                System.out.println("File is not decrypted yet \n try again!");
                break;

            } else {
                Scanner read = new Scanner(infile);
                System.out.println("Output File: ");
                while (read.hasNext()) {
                    System.out.println(read.nextLine());

                }
                System.out.println("==========================================================");
                break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("=============================================");
        System.out.println("Caesar Cipher");
        System.out.println("1-Decrypt a file");
        System.out.println("2-Display a file");
        System.out.println("=============================================");
        System.out.print("Choice: ");
    }

}
