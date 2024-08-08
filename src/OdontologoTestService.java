import Dao.BD;
import Dao.OdontologoDAOH2;
import Model.Odontologo;
import Service.OdontologoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class OdontologoTestService {
    @Test
    public void testRegistrarOdontologos(){
        BD.crearTabla();
        OdontologoService odontologoService= new OdontologoService();
        Odontologo odontologo1 = new Odontologo(1111,"Juan", "Perez");
        Odontologo odontologo2 = new Odontologo(2222,"Pedro", "Ramirez");
        OdontologoDAOH2 odontologoDAOList = new OdontologoDAOH2();
        odontologoDAOList.guardar(odontologo1);
        odontologoDAOList.guardar(odontologo2);
    }

    @Test
    public void testListaOdontologos(){
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos = new OdontologoDAOH2().listarTodos();
        Assertions.assertFalse(odontologos.isEmpty());
    }
}