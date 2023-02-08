import java.sql.*;
import java.util.Scanner;

public class Quiz {
    public static void main(String[]args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Erreur lors du chargement"+e.getMessage());
        }
        String url = "jdbc:mysql://localhost:3306/quiz";
        Connection connection;
        Statement stmt;
        ResultSet geo;
        int pts=10;
        String tent;

        System.out.println(" ________________________________________________________________________________");
        System.out.println("|       Bienvenue dans ce quiz de géographie, vous partirez avec 10 points       |");
        System.out.println("|   Vous aurez une série de 10 questions qui détermineront votre nombre de points|");
        System.out.println("|________________________________________________________________________________|");
        try{
            connection = DriverManager.getConnection(url,"root","");
            stmt = connection.createStatement();
            geo = stmt.executeQuery("Select * from geographie");

            System.out.println(geo);
            while (geo.next()){
                System.out.println("Question : "+geo.getString("Intitule")+"?");
                Scanner sc=new Scanner(System.in);
                System.out.println("Votre réponse : ");
                tent=sc.nextLine();
                if (!tent.equals(geo.getString("reponse"))){
                    pts--;
                }else{
                while (!tent.equals(geo.getString("reponse"))){
                    System.out.println("C'est incorrect, votre réponse : ");
                    tent=sc.nextLine();
                    pts--;
                }
                pts++;
            }}
            System.out.println("Félicitations vous avez eu "+pts+" points!");


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
