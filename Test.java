import java.sql.*;

public class Test {
    public static void main(String[]args) throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("Erreur lors du chargement"+e.getMessage());
        }
        String url = "jdbc:mysql://localhost:3306/immodb";
        Connection connection= null ;
        Statement stmt= null;
        ResultSet resultat,resultat2,resultat3,resultat4= null;


        try {
            connection = DriverManager.getConnection(url,"root","");
            stmt = (Statement) connection.createStatement();
            resultat = stmt.executeQuery("Select a_code,a_loyer,a_superf From appartement;");

            System.out.println(resultat);
            while (resultat.next()){
                System.out.println("Numéro : "+ resultat.getInt("a_code")+ " "
                                    +"Loyer : "+ resultat.getInt("a_loyer")+" "
                                    +"Superficie : "+resultat.getInt("a_superf")+" "
                );

            }

            resultat2 = stmt.executeQuery("Select imm_num,count(*) as nombre From appartement group by imm_num");
            System.out.println(resultat2);
            while (resultat2.next()){
                System.out.println("Nombre d'apparts dans l'immeuble "+resultat2.getInt("imm_num")+": "+resultat2.getInt("nombre"));
            }
            resultat3 = stmt.executeQuery("Select a_code,a_loyer from appartement Where a_loyer=(Select max(a_loyer) from appartement)");
            System.out.println(resultat3);
            while (resultat3.next()){
                System.out.println("L'appartement ayant le loyer le plus élevé est le numéro "+resultat3.getInt("a_code") +" avec un loyer de "+resultat3.getInt("a_loyer"));
            }
            resultat4 = stmt.executeQuery("Select a_code from appartement where a_etat LIKE 'TBE' OR a_etat LIKE 'BON'");
            System.out.println(resultat4);
            while (resultat4.next()){
                System.out.println("L'appartement "+resultat4.getInt("a_code")+"est en bon ou très bon état ");
            }
        }catch (SQLException e){
            System.out.println("Erreur lors du chargement "+e.getMessage());
        }
    }
}
