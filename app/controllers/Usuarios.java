package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import play.mvc.BodyParser;

import views.html.*;

import models.*;
import java.util.List;

import play.libs.Json;
import play.libs.Json.*;
import static play.libs.Json.toJson;
import com.fasterxml.jackson.databind.JsonNode;

//@With(Autorizar.class)
public class Usuarios extends Controller {

//GET
    public static Result getUsuarios(){
        List<Usuario> usuarios = Usuario.find.all();
        return ok(toJson(usuarios));
    }
    
    public static Result getUsuario(Long id){
        Usuario usuario = Usuario.find.byId(id);
        return ok(toJson(usuario));
    }

//POST
    @BodyParser.Of(BodyParser.Json.class)
    public static Result addUsuario()
    {
        //Usuario newUsuario = Json.fromJson(request().body().asJson(), Usuario.class);
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        }else{
            Usuario newUsuario = new Usuario();
            
            newUsuario.nombre = json.findPath("nombre").textValue();
            newUsuario.login = json.findPath("login").textValue();
            newUsuario.password = json.findPath("password").textValue();
            newUsuario.email = json.findPath("email").textValue();
            String cadena = json.findPath("tipousuario").textValue();
            newUsuario.tipousuario.id = Long.parseLong(cadena);
            
            if(newUsuario.nombre == null) {
                return badRequest("Missing parameter [nombre]");
            } else {
                newUsuario.save();
                return redirect("/promociones");
            }
        }
     }
    
    public static  Result editUsuario(){
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Usuario usuario = Usuario.find.byId(id);
        
        usuario.nombre = json.findPath("nombre").textValue();
        usuario.login = json.findPath("login").textValue();
        usuario.password = json.findPath("password").textValue();
        usuario.email = json.findPath("email").textValue();
        String cadena2 = json.findPath("tipousuario").textValue();
        usuario.tipousuario.id = Long.parseLong(cadena2);
        
        usuario.update(id);
        return ok("se edito");
    }
    
    public static Result deleteUsuario() {
        JsonNode json = request().body().asJson();
        String cadena = json.findPath("id").textValue();
        Long id = Long.parseLong(cadena);
        
        Usuario.find.ref(id).delete();
        return redirect("/promociones");
    }
}
