package exercici2;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Pizzeria {
    public static void main(String[] args) {
        // Establecer conexión con la base de datos MongoDB
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase database = mongoClient.getDatabase("pizzeria");


            // Obtener las colecciones
            MongoCollection<Document> clientesCollection = database.getCollection("clientes");
            MongoCollection<Document> pedidosCollection = database.getCollection("pedidos");
            MongoCollection<Document> pedidosDomicilioCollection = database.getCollection("pedidosDomicilio");
            MongoCollection<Document> categoriaCollection = database.getCollection("categoria");
            MongoCollection<Document> empleadoCollection = database.getCollection("empleado");
            MongoCollection<Document> localidadCollection = database.getCollection("localidad");
            MongoCollection<Document> provinciaCollection = database.getCollection("provincia");
            MongoCollection<Document> productosCollection = database.getCollection("productos");
            MongoCollection<Document> tiendaCollection = database.getCollection("tienda");

            // Crear cliente y guardarlo
            Document client1 = new Document()
                    .append("Nombre", "Joan")
                    .append("Apellido", "Gomez")
                    .append("Dirección", "Carrer Major 123")
                    .append("Codigo Postal", "08001")
                    .append("Localitat", "Badalona")
                    .append("Provincia", "Barcelona")
                    .append("Teléfono", "658956475");
            clientesCollection.insertOne(client1);

            Document empleado1 = new Document()
                    .append("Nombre", "Marina")
                    .append("Apellido", "Rueda Izquierdo")
                    .append("Dirección", "Carrer Principal 13")
                    .append("Codigo Postal", "08015")
                    .append("Localitat", "Badalona")
                    .append("Provincia", "Barcelona")
                    .append("Teléfono", "695784578");
            empleadoCollection.insertOne(empleado1);

            Document pedido1 = new Document()
                    .append("Fecha/hora", "23:59:00")
                    .append("Tipo Entrega", "Domicilio")
                    .append("Productos", new Document("pizza", 2).append("hamburguesa", 1).append("beguda", 3))
                    .append("Precio total", 25.50)
                    .append("Cliente", 1)  // Identificador de client
                    .append("Tienda", 1)  // Identificador de botiga
                    .append("Repartidor", 1); // Identificador de repartidor
            pedidosCollection.insertOne(pedido1);
        }
    }
}
