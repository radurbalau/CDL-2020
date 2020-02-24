package com.company;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class TextFieldExample implements ActionListener{
    JTextField tf1,tf3;
    JLabel label1,label2;
    JButton b1;
    TextFieldExample(){
        JFrame f= new JFrame("Ce cuvinte doresti sa fie cautate?");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tf1=new JTextField();
        tf1.setBounds(50,100,350,40);

        tf3=new JTextField();
        tf3.setBounds(50,200,350,40);
        tf3.setEditable(false);

        label1 = new JLabel("Se gasesc cuvintele in documentele cu numarul:");
        label1.setBounds(50,175,450,20);

        label2 = new JLabel("Scrie filtrul pe care vrei sa il aplici:");
        label2.setBounds(50,75,450,20);


        b1=new JButton("Cauta");
        b1.setBounds(50,250,100,50);
        b1.addActionListener(this);


        f.add(tf1);
        f.add(tf3);
        f.add(b1);
        f.add(label1);
        f.add(label2);

        f.setSize(500,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1) //cand se apasa
        {
            try {
                tf3.setText(computeSearch(tf1.getText()));
            } catch (IOException | ScriptException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new TextFieldExample();
    }

    public String computeSearch(String input) throws IOException, ScriptException {

        StringBuilder output = new StringBuilder();
        String beautifulOutput = new String();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

    String[] wordList = input.split("[^a-zA-Z-' ]");
    List<String> myList = Arrays.stream(wordList)
            .filter(p->p.trim().length() > 0)
            .map(String::trim)
            .collect(Collectors.toList());


        File f1 = new File("documente_problema/"); //Creation of
    File[] allTextFiles = f1.listFiles(new FilenameFilter() {
        @Override
        public boolean accept(File file, String s) {
            return s.toLowerCase().endsWith(".txt");
        }
    });

    assert allTextFiles != null;
    Arrays.sort(allTextFiles);
        String salvare = input;

    for (int i = 0; i < allTextFiles.length; i++) {
        File file;
        file = allTextFiles[i];

        salvare = input;
        salvare = salvare.replaceAll("\\|\\|", "|");
        salvare = salvare.replaceAll("\\&\\&", "&");

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr); //Creation of BufferedReader object
            String s;

            String doc = "";
            while ((s = br.readLine()) != null)   //Reading Content from the file
            {
                s = s.replaceAll(",", " ");
                s = s.replaceAll("!", " ");
                s = s.replaceAll("\\?", " ");
                s = s.replaceAll("\\.", " ");
                doc += " " + s;

            }

            for (String keywords : myList) {
                String regex = "\\b" + keywords + "\\b";
                Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(doc);
                if (matcher.find()) {
                    salvare = salvare.replaceAll(keywords,"true");
                }
                else {
                        salvare = salvare.replaceAll(keywords, "false");
            }


            }


        if(engine.eval(salvare).equals(true)){
             output.append('1');
         }
         else {
             output.append('0');
         }

        fr.close();

        }

        for (int i = 0; i < output.length(); i++){
            char c = output.charAt(i);
            if(c == '1'){
                beautifulOutput = beautifulOutput + (i +1) + " ";
            }

        }

        return beautifulOutput;
    }
}
