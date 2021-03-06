package models;

import java.util.*;
import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Promocion extends Model {
    
    @Id
    public Long id;
    
    @Constraints.Required(message="Debe ingresar el nombre")
    public String nombre;
    
    @Constraints.Required(message="Debe ingresar la descripcion")
    public String descripcion;
    
    @Constraints.Required(message="Debe ingresar el precioAnt")
    public Float precioAnt;

    @Constraints.Required(message="Debe ingresar el precioAct")
    public Float precioAct;
    
    @Constraints.Required(message="Debe ingresar el stock")
    public Integer stock;

    // Generic query helper for entity with id long
    public static Model.Finder<Long,Promocion> find = new Model.Finder<Long,Promocion>(Long.class, Promocion.class);
}
