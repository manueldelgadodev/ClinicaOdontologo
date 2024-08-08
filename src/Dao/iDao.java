package Dao;

import java.util.List;

public interface iDao<T> {
    //Metodos comunes a los crud de los objetos de la logica de negocio.
    /*guardar , eliminnar, buscar, actualizar*/

    T guardar (T t);
    List<T> listarTodos();
}