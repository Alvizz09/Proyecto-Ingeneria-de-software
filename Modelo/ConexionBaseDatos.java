package Modelo;

import com.mongodb.client.*;
import javafx.fxml.FXML;
import org.bson.Document;

import java.sql.Connection;
import java.util.ArrayList;

public class ConexionBaseDatos {
    private MongoClient mongoClient;
    private MongoDatabase database;

    // Constructor que inicializa la conexión
    public ConexionBaseDatos() {
        conectar();
    }

    // Método para conectar a MongoDB
    @FXML
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
    @FXML
    public void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
            System.out.println("Conexión cerrada.");
        }
    }

    // Método para obtener los usuarios
    @FXML
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
    @FXML
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
                        doc.getString("tipo"),
                        (ArrayList<String>) doc.getList("miembros", String.class),
                        doc.getString("owner")
                );
                oportunidades.add(oportunidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener las oportunidades.");
        }
        System.out.println("Oportunidades dadas.");
        return oportunidades;
    }

    @FXML
    public boolean guardarUsuario(Usuario usuario) {
        try {

            if (existeUsuarioPorCorreo(usuario.getCorreo())) {
                System.err.println("Error: El correo ya está registrado.");
                return false;
            }

            // Obtén la colección "Usuarios"
            MongoCollection<Document> collection = database.getCollection("Usuarios");

            // Crea un documento con los datos del usuario, sin el campo idUsuario
            Document documento = new Document()
                    .append("idUsuario", usuario.getIdUsuario())
                    .append("nombre", usuario.getNombre())
                    .append("apellido", usuario.getApellido())
                    .append("correo", usuario.getCorreo())
                    .append("contrasena", usuario.getContrasena())
                    .append("edad", usuario.getEdad())
                    .append("carrera", usuario.getCarrera())
                    .append("universidad", usuario.getUniversidad());

            // Inserta el documento en la colección
            collection.insertOne(documento);

            // Obtener el ID generado automáticamente por la base de datos
            System.out.println("Usuario guardado exitosamente con ID: " + documento.getObjectId("_id"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al guardar el usuario.");
            return false;
        }
    }

    @FXML
    public boolean existeUsuarioPorCorreo(String correo) {
        try {
            MongoCollection<Document> collection = database.getCollection("Usuarios");
            Document query = new Document("correo", correo);
            Document doc = collection.find(query).first();
            return doc != null;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al buscar el usuario por correo.");
            return false;
        }
    }

    @FXML
    public boolean guardarOportunidad(Oportunidad oportunidad) {
        try {
            MongoCollection<Document> collection = database.getCollection("Oportunidades");

            Document documento = new Document()
                    .append("idOportunidad", oportunidad.getIdOportunidad())
                    .append("nombre", oportunidad.getNombre())
                    .append("descripcion", oportunidad.getDescripcion())
                    .append("esPrivada", oportunidad.isEsPrivada())
                    .append("tags", oportunidad.getTags())
                    .append("tipo", oportunidad.getTipo())
                    .append("miembros", oportunidad.getMiembros())
                    .append("owner", oportunidad.getOwner());

            collection.insertOne(documento);
            System.out.println("Oportunidad guardada exitosamente con ID: " + documento.getObjectId("_id"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al guardar la oportunidad.");
            return false;
        }
    }

    @FXML
    public ArrayList<Carreras> getCarrerasDb() {
        ArrayList<Carreras> carreras = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Carreras");
            for (Document doc : collection.find()) {
                Carreras carrera = new Carreras(
                        doc.getString("nombreCarrera")
                );
                carreras.add(carrera);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener las carreras.");
        }
        return carreras;
    }

    @FXML
    public ArrayList<Universidad> getUniversidadesDb() {
        ArrayList<Universidad> universidades = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection("Universidades");
            for (Document doc : collection.find()) {
                Universidad universidad = new Universidad(
                        doc.getString("nombre"),
                        doc.getInteger("id")
                );
                universidades.add(universidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al obtener las universidades.");
        }
        return universidades;
    }
    /*public void actualizarOportunidad(Oportunidad oportunidad, Usuario nuevoMiembro) {
        String query = "UPDATE oportunidades SET miembros = CONCAT(miembros, ?) WHERE id = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, ", " + nuevoMiembro.getNombre());
            pstmt.setString(2, oportunidad.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }*/

    public void actualizarOportunidad(Oportunidad oportunidad, Usuario nuevoMiembro) {
        try {
            MongoCollection<Document> collection = database.getCollection("Oportunidades");

            // Crear el filtro para encontrar la oportunidad por id
            Document filtro = new Document("idOportunidad", oportunidad.getIdOportunidad());

            // Crear el documento de actualización para agregar el nuevo miembro
            Document actualizacion = new Document("$addToSet", new Document("miembros", nuevoMiembro.getNombre()));

            // Actualizar la oportunidad en la base de datos
            collection.updateOne(filtro, actualizacion);
            System.out.println("Oportunidad actualizada exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al actualizar la oportunidad.");
        }
    }
}
