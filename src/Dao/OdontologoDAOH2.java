package Dao;
import org.apache.log4j.Logger;

import Model.Odontologo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static  final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static  final String SQL_INSERT="INSERT INTO ODONTOLOGIA(NUMEROMATRICULA, NOMBRE, APELLIDO) VALUES (?,?,?)";

    //private static  final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGIA WHERE ID = ?";
    private static  final String SQL_SELECT_ALL= "SELECT * FROM ODONTOLOGIA";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Inicio - Guardar un Odontólogo");

        //Logica para persistir en la bd
        Connection connection = null;
        try {
            connection= BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1,odontologo.getNumMatricula());
            psInsert.setString(2,odontologo.getNombre());
            psInsert.setString(3,odontologo.getApellido());
            psInsert.execute();
            logger.info("Datos cargados con exito");

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()){
                odontologo.setNumMatricula(rs.getInt(1));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("Odontólogo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Inicio - Listar Odontólogos");
        List<Odontologo> odontologos = new ArrayList<>();
        Connection connection = null;
        try {
            connection= BD.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psSelectAll.executeQuery();
            while (rs.next()){
                odontologos.add(new Odontologo(rs.getInt(1),
                        rs.getString(2), rs.getString(3)));
            }
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.info("Listado de todos los odontólogos: " + odontologos);
        return odontologos;
    }
}