package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Recommender {
    static Scanner scan = new Scanner(System.in);
    static List<String> movieIdsNumbers=new ArrayList<>();
    static List<String> userIds=new ArrayList<>();

    public static void main(String[] args){

        List<String> moviesFileData=movieFileVerification();
        for(int i=0;i<moviesFileData.size();i+=2){ //creating movies from text file
            String movieName=moviesFileData.get(i).split(", ")[0];
            String movieId=moviesFileData.get(i).split(", ")[1];
            List<String> movieGenre=Arrays.asList(moviesFileData.get(i+1).split(", "));
            new Movie(movieName,movieId,movieGenre);
        }

        List<String> userFileData=userFileVerification();
        for(int i=0;i<userFileData.size();i+=2){
            String userName=userFileData.get(i).split(", ")[0];
            String userId=userFileData.get(i).split(", ")[1];
            List<String> userMovies=Arrays.asList(userFileData.get(i+1).split(", "));
            new User(userName,userId,userMovies);
        }
        writeRecommendationsToFile();
    }

    public static List<String> fileReader(String msg){
        String fileName, line;
        List<String> lines = new ArrayList<>();
        System.out.println(msg);
        fileName = scan.nextLine();
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufread = new BufferedReader(fileReader);

            // reading the file, line by line
            while((line = bufread.readLine()) != null) {
                lines.add(line);
            }
            bufread.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception: " +e);
            System.exit(1);
        }
        return lines;
    }

    public static List<String> movieFileVerification(){
        List<String> lines=fileReader("Enter movies' file name/path: ");
        for(int i=0;i<lines.size();i++){

            String[] temp=lines.get(i).split(", ");
            if(i%2==0) {
                if (!movieTitleCheck(temp[0])) {
                    String errorMsg = "ERROR: Movie Title {" + temp[0] + "} is wrong";
                    writeErrorMsg(errorMsg);
                    System.err.println(errorMsg);
                    throw new RuntimeException("Exiting due to error condition");
                    //continue
                }

                if (!movieIdLettersCheck(temp)) {
                    String errorMsg = "ERROR: Movie Id letters {" + temp[1] + "} are wrong";
                    writeErrorMsg(errorMsg);
                    System.err.println(errorMsg);
                    throw new RuntimeException("Exiting due to error condition");
                    //continue
                }

                if(!movieIdNumbersCheck(temp[1])){
                    String errorMsg = "ERROR: Movie Id numbers {" + temp[1] + "} aren’t unique";
                    writeErrorMsg(errorMsg);
                    System.err.println(errorMsg);
                    throw new RuntimeException("Exiting due to error condition");
                }
            }
        }
        return lines;
    }

    public static boolean movieTitleCheck(String title){
        for(String word:title.split(" ")){
            if(!Character.isUpperCase(word.charAt(0)))return false;
        }
        return true;
    }

    public static boolean movieIdLettersCheck(String[] line){
        String movieName=line[0];
        String expectedId="";
        String temp=line[1].replaceAll("[0-9]","");
        for(String word:movieName.split(" ")){
            expectedId+=word.charAt(0);
        }
        return expectedId.equals(temp);
    }

    public static boolean movieIdNumbersCheck(String line){
        String idNumbers=line.replaceAll("[A-Z]","");
        if (idNumbers.length()!=3) return false;
        if (movieIdsNumbers.contains(idNumbers)) return false;
        else {
            movieIdsNumbers.add(idNumbers);
            return true;
        }
    }

    public static List<String> userFileVerification(){
        List<String> userData = fileReader("Enter users' file name/path: ");

        for(int i=0;i<userData.size();i+=2){

            String[] temp=userData.get(i).split(", ");
            String userName=temp[0];
            String userId=temp[1];

            if(!userNameCheck(userName)){
                String errorMsg = "ERROR: User Name {" + temp[0] + "} is wrong";
                writeErrorMsg(errorMsg);
                System.err.println(errorMsg);
                throw new RuntimeException("Exiting due to error condition");
            }

            if(!userIdCheck(userId)){
                String errorMsg = "ERROR: User Id {" + temp[1] + "} is wrong";
                writeErrorMsg(errorMsg);
                System.err.println(errorMsg);
                throw new RuntimeException("Exiting due to error condition");
            }
        }
        return userData;
    }

    public static boolean userNameCheck(String name){
        if(name.charAt(0)==' ') return false;
        for(int i=0;i<name.length();i++){
            if(!Character.isLetter(name.charAt(i)) && name.charAt(i)!=' ') return false;
        }
        return true;
    }

    public static boolean userIdCheck(String Id){
        if(Id.length()!=9)return false;
        for(int i=0;i<8;i++){
            if(!Character.isDigit(Id.charAt(i))) return false;
        }
        if(!Character.isLetterOrDigit(Id.charAt(8))) return false;

        if (userIds.contains(Id)) return false;
        else {
            userIds.add(Id);
        }
        return true;
    }

    public static void writeRecommendationsToFile() {
        try {
            FileWriter myWriter = new FileWriter("recommendations.txt");
            List<User> users = User.getUsersList();

            for (int u = 0; u < users.size(); u++) {
                User user = users.get(u);
                myWriter.write(user.getUserName() + ", " + user.getUserId() + '\n');

                List<Movie> recommendations = user.getRecommendations();
                for (int i = 0; i < recommendations.size(); i++) {
                    myWriter.write(recommendations.get(i).getTitle());
                    if (i < recommendations.size() - 1) {
                        myWriter.write(", ");
                    }
                }
                if (u < users.size() - 1) {
                    myWriter.write('\n');
                }
            }

            myWriter.close();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new java.io.File("recommendations.txt"));
            } else {
                System.out.println("Desktop is not supported on this system.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeErrorMsg(String message){
        try {
            FileWriter myWriter = new FileWriter("recommendations.txt");
            myWriter.write(message);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}