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
        String query;
        int theme=0;

        Scanner sc=new Scanner(System.in);

        System.out.println(" ________________________________________________________________________________________");
        System.out.println("|       Bienvenue dans ce quiz de géographie, vous partirez avec 10 points               |");
        System.out.println("|   Vous aurez ensuite une série de 10 questions qui détermineront votre nombre de points|");
        System.out.println("|________________________________________________________________________________________|");
        System.out.println("");
        System.out.println("Choisis un theme en indiquant son numero : ");
        System.out.println("1/ Géographie     2/ Histoire");
        theme= Integer.parseInt(sc.nextLine());

        //Si on veut faire différents themes    On peut aussi le faire en faisant plusieurs BDD

        query=String.format("Select * from quizz Where theme=%d",theme);
        try{
            connection = DriverManager.getConnection(url,"root","");
            stmt = connection.createStatement();

            geo = stmt.executeQuery(query);

            System.out.println(geo);
            while (geo.next()){
                System.out.println("Question : "+geo.getString("Intitule")+"?");

                System.out.println("Votre réponse : ");
                tent=sc.nextLine();

                while (!tent.equals(geo.getString("reponse"))){
                    System.out.println("C'est incorrect, nous vous avons demandé "+geo.getString("Intitule"));
                    tent=sc.nextLine();
                    pts--;
                }
                pts++;
            }
            System.out.println("Félicitations vous avez eu "+pts+" points sur 20 possibles!");


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
