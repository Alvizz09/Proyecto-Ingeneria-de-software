package Modelo;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

public class ConexionBaseDatos {
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructor que inicializa la conexión
    public ConexionBaseDatos() {
        conectar();
    }

    // Método para conectar a MongoDB
    public void conectar() {
        String connectionString = "mongodb+srv://alvarezrjsebastian:PuyOEFgv0w8Aou3o@proyectofundamentos.fcgji.mongodb.net/?retryWrites=true&w=majority&appName=ProyectoFundamentos";
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(connectionString);
                database = mongoClient.getDatabase("Impulse");
                System.out.println("Conexión exitosa a la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos.");
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            System.out.println("Conexión cerrada.");
        }
    }

    // Método para obtener los usuarios
    public ArrayList<Usuario> getUserDb() {
        ArrayList<Usuario> users = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            for (Document doc : collection.find()) {
                Usuario user = new Usuario();
                user.setIdUsuario(doc.getString("idUsuario"));
                user.setNombre(doc.getString("nombre"));
                user.setApellido(doc.getString("apellido"));
                user.setCorreo(doc.getString("correo"));
                user.setContrasena(doc.getString("contrasena"));
                user.setEdad(doc.getInteger("edad"));
                user.setCarrera(doc.getString("carrera"));
                user.setUniversidad(doc.getString("universidad"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener los usuarios.");
        }
        return users;
    }

    // Método para obtener las oportunidades
    public ArrayList<Oportunidad> getOportunidadesDb() {
        ArrayList<Oportunidad> oportunidades = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Oportunidades");
            for (Document doc : collection.find()) {
                Oportunidad oportunidad = new Oportunidad(
                        doc.getString("idOportunidad"),
                        doc.getString("nombre"),
                        doc.getString("descripcion"),
                        doc.getBoolean("esPrivada"),
                        doc.getString("tags"),
                        doc.getString("tipo")
                );
                oportunidades.add(oportunidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener las oportunidades.");
        }
        return oportunidades;
    }
}
