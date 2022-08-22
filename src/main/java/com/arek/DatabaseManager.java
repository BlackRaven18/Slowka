package com.arek;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:wordsdatabase.db";

    private static final String GET_WORDS_NUMBER_QUERRY = "SELECT COUNT(*) FROM SLOWO;";
    private static final String GET_WORDS_QUERRY = "SELECT slowo FROM SLOWO;";
    private static final String GET_WORDS_WITH_TRANSLATIONS = "SELECT sl.slowo, tl.tlumaczenie\n" +
            "FROM slowo sl, tlumaczenie tl\n" +
            "WHERE sl.id_slowa = tl.id_slowa;\n";

    public static int getWordsNumber(){
        int wordsNumber = 0;

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_WORDS_NUMBER_QUERRY)){
            wordsNumber = resultSet.getInt(1);
        }catch (SQLException e){
            System.err.println("GET_WORDS_NUMBER_QUERRY ERROR!");
        }

        return wordsNumber;
    }

    public static HashMap<String, ArrayList<String>> getWordsAndTranslations(){
        HashMap<String, ArrayList<String>> wordsAndTranslations = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_WORDS_WITH_TRANSLATIONS)){
            while (resultSet.next()){

                ArrayList<String> translationList;

                if(wordsAndTranslations.containsKey(resultSet.getString(1))){
                    translationList = wordsAndTranslations.get(resultSet.getString(1));
                }else {
                    translationList = new ArrayList<>();
                }

                translationList.add(resultSet.getString(2));
                wordsAndTranslations.put(resultSet.getString(1), translationList);
            }
            //TODO: delete  this
            System.out.println(wordsAndTranslations);

        }catch (SQLException e){
            System.err.println("GET_WORDS_WITH_TRANSLATIONS ERROR!");
        }


        return wordsAndTranslations;
    }
}