package org.CaptureTheFlag.Managements;

import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Map.Map;
import org.CaptureTheFlag.Models.Path.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ImportExportMap {
    public static class MapImportResult {
        private Map<Location> map;
        private boolean hasBidirectionalPaths;

        public MapImportResult(Map<Location> map, boolean hasBidirectionalPaths) {
            this.map = map;
            this.hasBidirectionalPaths = hasBidirectionalPaths;
        }

        public Map<Location> getMap() {
            return map;
        }

        public boolean hasBidirectionalPaths() {
            return hasBidirectionalPaths;
        }
    }

    /**
     * Imports a map from a JSON file and generates the map accordingly.
     *
     * @param filePath The path of the JSON file containing the map data.
     * @return The imported map.
     */
    public MapImportResult importMapAndGenerateMap(String filePath) {
        Map<Location> map = new Map<>();
        boolean bidirectionalPaths = false;

        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));

            JSONArray locations = (JSONArray) jsonData.get("vertices");
            JSONArray paths = (JSONArray) jsonData.get("paths");
            bidirectionalPaths = (boolean) jsonData.get("bidirectionalPaths");

            // Adicionar locais ao mapa
            if (locations != null) {
                for (Object loc : locations) {
                    JSONObject locationData = (JSONObject) loc;
                    int id = ((Long) locationData.get("id")).intValue();
                    String name = (String) locationData.get("name");

                    // Adiciona informações de log para verificar os dados
                    System.out.println("ID: " + id + ", Name: " + name);

                    map.addVertex(new Location(id, name));
                }
            } else {
                System.out.println("O JSON não contém dados de localizações (locations).");
            }

            // Adicionar caminhos ao mapa
            if (paths != null) {
                for (Object path : paths) {
                    JSONObject pathData = (JSONObject) path;
                    int start = ((Long) pathData.get("start")).intValue();
                    int end = ((Long) pathData.get("end")).intValue();
                    long distanceLong = (Long) pathData.get("distance");
                    int distance = (int) distanceLong;
                    map.addEdge(start, end, distance);
                }

            } else {
                System.out.println("O JSON não contém dados de caminhos (paths).");
            }

            System.out.println("Mapa importado com sucesso.");
            return new MapImportResult(map, bidirectionalPaths);

        } catch (IOException | ParseException e) {
            System.out.println("Erro ao importar o mapa: " + e.getMessage());
            return null;
        }
    }

    /**
     * Exports the map data to a JSON file.
     *
     * @param map      The map to be exported.
     * @param filePath The path of the JSON file to export the map data.
     */
    public static void exportMapToJson(Map<Location> map, boolean bidirectionalPaths, String filePath) {
        JSONObject jsonMap = new JSONObject();
        JSONArray verticesArray = new JSONArray();
        JSONArray pathsArray = new JSONArray();

        // Adicionar vértices ao array JSON
        for (Location vertex : map.getVertices()) {
            JSONObject vertexObject = new JSONObject();
            vertexObject.put("id", vertex.getId());
            vertexObject.put("name", vertex.getName());
            verticesArray.add(vertexObject);
        }

        // Adicionar caminhos ao array JSON
        for (Path path : map.getEdges()) {
            JSONObject pathObject = new JSONObject();
            pathObject.put("start", path.getOrigin().getId());
            pathObject.put("end", path.getDestination().getId());
            pathObject.put("distance", path.getDistance());
            pathsArray.add(pathObject);
        }

        // Adicionar arrays de vértices e caminhos ao objeto JSON do mapa
        jsonMap.put("vertices", verticesArray);
        jsonMap.put("paths", pathsArray);
        jsonMap.put("bidirectionalPaths", bidirectionalPaths);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(jsonMap.toJSONString());
            file.flush();
            System.out.println("Mapa exportado com sucesso para '" + filePath + "'.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}