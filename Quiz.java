
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
        int pts=0;
        ResultSet geo;
        String tent;
        String query;
        int diff=0;
        Scanner sc=new Scanner(System.in);
        final String BLACK = "\033[0;30m";
        final String white = "\033[47m";
        final String green = "\033[42m";
        final String blue = "\033[44m";
        final String red = "\u001B[41m";
        final String bred = "\033[1;31m";
        final String r = "\u001B[0m";

        System.out.println(bred+" ________________________________________________________________________________________"+r);
        System.out.println(bred+"|       Bienvenue dans ce quiz de géographie. Vous partirez avec 0 points                |"+r);
        System.out.println(bred+"|   Vous aurez ensuite une série de questions qui détermineront votre nombre de points   |"+r);
        System.out.println(bred+"|________________________________________________________________________________________|"+r);
        System.out.println("");
        System.out.println("Choisis une difficulté en indiquant le numéro correspondant : ");
        System.out.println("1/"+white+"Facile"+r+"       2/"+green+"Intermédiaire"+r+"       3/"+blue+"Moyen"+r+"       4/"+red+"Difficile"+r+"        5/Impossible     6/"+white+"Mix "+r+""+green+"de "+r+""+blue+"tous "+r+""+red+"les "+r+"niveaux");
        diff= Integer.parseInt(sc.nextLine());

        query=String.format("Select * from quizz Where Difficulte=%d",diff);
        if (diff==6){
            query="Select * from quizz";
        }
        try{
            connection = DriverManager.getConnection(url,"root","");
            stmt = connection.createStatement();

            geo = stmt.executeQuery(query);
            System.out.println("Vous avez choisi un questionnaire de géographie de niveau "+diff+", Bonne chance !");
            if (diff==6) {
                System.out.println("Vous avez choisi un questionnaire de géographie contenant un mix de toutes les difficultés");
            }

            System.out.println(red+"ATTENTION A L'ORTHOGRAPHE!!!!!"+r);
            System.out.println(geo);
            while (geo.next()){
                System.out.println("Question a "+geo.getInt("Difficulte")+" point(s) : "+geo.getString("Intitule")+"?");

                System.out.println("Votre réponse : ");
                tent=sc.nextLine();

                while (!tent.equals(geo.getString("reponse"))){
                    System.out.println("C'est incorrect, nous vous avons demandé "+geo.getString("Intitule"));
                    tent=sc.nextLine();
                    pts--;
                }
                pts=pts+diff;
            }
            System.out.println("Félicitations vous avez eu "+pts+" points sur 20 possibles!");


    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
