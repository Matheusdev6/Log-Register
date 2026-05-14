package Server;

import Model.RequestModel;
import Model.ResponseModel;
import Model.Status;
import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class JsonObject {
    private UUID id;
    private LocalDateTime timestamp;
    private Status status;
    private String place;

    public JsonObject(RequestModel request, ResponseModel response) {
        this.id = request.getId();
        this.timestamp = request.getTimestamp();
        this.status = response.getStatus();
        this.place = response.getPlace();
    }
    public UUID getId() {
        return id;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public Status getStatus() {
        return status;
    }
    public String getPlace() {
        return place;
    }

    public void jsonWriting(JsonObject jsonObject, String fileName) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                        return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                })
                .create();

        try (FileWriter writer= new FileWriter(fileName)){;
            gson.toJson(jsonObject, writer);
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonObject jsonReading(String filename) throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    }
                })
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
                        return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    }
                })
                .create();

        try(FileReader file = new FileReader(filename)){
            return gson.fromJson(file, JsonObject.class);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
