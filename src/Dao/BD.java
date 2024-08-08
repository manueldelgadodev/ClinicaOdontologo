package Dao;

import Model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BD {
    private static final Logger logger =Logger.getLogger(BD.class);
    private static final String SQL_DROP_CREATE = "DROP TABLE IF EXISTS ODONTOLOGIA; "+
            //ID INT AUTO_INCREMENT PRIMARY KEY
            "CREATE TABLE ODONTOLOGIA " +
            "(NUMEROMATRICULA INT PRIMARY KEY, " +
            "NOMBRE VARCHAR(100) NOT NULL, " +
            "APELLIDO VARCHAR(100) NOT NULL)";

    public static void crearTabla(){
        Connection connection = null;

        try {
            connection= getConnection();
            Statement statement = connection.createStatement();
            statement.execute(SQL_DROP_CREATE);
            logger.info("tabla creada con exito");
        }catch (Exception e){
            logger.error("Error en BD: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:~/DaoOdontologia","sa","sa");
    }
}