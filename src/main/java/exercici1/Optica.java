package exercici1;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Optica {
    public static void main(String[] args) {
        // Establecer conexión con la base de datos MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("optica");

            // Obtener las colecciones
            MongoCollection<Document> proveedorCollection = database.getCollection("proveedor");
            MongoCollection<Document> gafasCollection = database.getCollection("gafas");
            MongoCollection<Document> clienteCollection = database.getCollection("cliente");

            // Carga de proveedores
            Document proveedor1 = new Document("nombre", "RayBan")
                    .append("direccion", "Carrer Cerdenya, 45")
                    .append("telefono", "658956748")
                    .append("fax", "935865478")
                    .append("nif", "47277732J");
            // Insertar el documento en la colección de proveedor
            proveedorCollection.insertOne(proveedor1);

            // Carga de gafas
            Document gafas1 = new Document("marca", "RayBan")
                    .append("graduacion", "1")
                    .append("tipoMontura", "flotante")
                    .append("colorMontura", "negro")
                    .append("colorCristal", "azul")
                    .append("precio", 100.0)
                    .append("proveedorID", 1);
            // Insertar el documento en la colección de gafas
            gafasCollection.insertOne(gafas1);

            // Carga de cliente
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaRegistro = null;
            try {
                fechaRegistro = dateFormat.parse("08/05/2022");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Document cliente1 = new Document()
                    .append("nombre", "Marina")
                    .append("direccion", "Camí des castell, 69, Maó")
                    .append("telefono", "697915797")
                    .append("emails", "marina.rueda90@gmail.com")
                    .append("fechaRegistro", fechaRegistro)
                    .append("clienteRecomendado", 4)
                    .append("empleado", 2);
            // Insertar el documento en la colección de clientes
            clienteCollection.insertOne(cliente1);
        }

    }

}
