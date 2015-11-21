package com.google.engedu.ghost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
              words.add(line.trim());
        }
    }

    public SimpleDictionary() {

    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {
        if(prefix==""){
            Random r = new Random();
           int ran= r.nextInt(words.size());
            return words.get(ran);
        }else{

            // pos = Collections.binarySearch(words, prefix);


            for(String str: words){
                if(str.startsWith(prefix)){

                    return str;
                }
            }
            return null;
        }
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        return null;
    }
}
