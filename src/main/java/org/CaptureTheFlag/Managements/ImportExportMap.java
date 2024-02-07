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

/**
 * The ImportExportMap class provides functionality to import and export maps from/to JSON files.
 */
public class ImportExportMap {

    /**
     * MapImportResult is a nested class representing the result of importing a map.
     */
    public static class MapImportResult {
        private Map<Location> map;
        private boolean hasBidirectionalPaths;

        /**
         * Constructs a MapImportResult object.
         *
         * @param map                 The imported map.
         * @param hasBidirectionalPaths Indicates if the map has bidirectional paths.
         */
        public MapImportResult(Map<Location> map, boolean hasBidirectionalPaths) {
            this.map = map;
            this.hasBidirectionalPaths = hasBidirectionalPaths;
        }

        /**
         * Retrieves the imported map.
         *
         * @return The imported map.
         */
        public Map<Location> getMap() {
            return map;
        }

        /**
         * Checks if the imported map has bidirectional paths.
         *
         * @return true if the map has bidirectional paths, false otherwise.
         */
        public boolean hasBidirectionalPaths() {
            return hasBidirectionalPaths;
        }
    }

    /**
     * Imports a map from a JSON file and generates the map.
     *
     * @param filePath The path to the JSON file containing the map data.
     * @return The result of the map import, including the map and bidirectional paths status.
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
                    int id = ((Long) locationData.get("id")).intValue() - 1 ;
                    String name = (String) locationData.get("name");
                    map.addVertex(new Location(id, name));
                }
            } else {
                System.out.println("O JSON não contém dados de localizações (locations).");
            }

            // Adicionar arestas ao mapa
            if (paths != null) {
                for (Object path : paths) {
                    JSONObject pathData = (JSONObject) path;
                    int start = ((Long) pathData.get("start")).intValue() - 1;
                    int end = ((Long) pathData.get("end")).intValue() - 1;
                    int distance = ((Long) pathData.get("distance")).intValue();
                    map.addEdge(start, end, distance);

                    // Se bidirectionalPaths for verdadeiro, adicionar a aresta oposta também
                    if (bidirectionalPaths) {
                        map.addEdge(end, start, distance);
                    }
                }
            } else {
                System.out.println("O JSON não contém dados de caminhos (paths).");
            }

            System.out.println("Mapa importado com sucesso.");

        } catch (IOException | ParseException e) {
            System.out.println("Erro ao importar o mapa: " + e.getMessage());
        }

        return new MapImportResult(map, bidirectionalPaths);
    }

    /**
     * Exports the map to a JSON file.
     *
     * @param map                The map to export.
     * @param bidirectionalPaths Indicates if the map has bidirectional paths.
     * @param filePath           The path to export the map JSON file.
     */
    public void exportMapToJson(Map<Location> map, boolean bidirectionalPaths, String filePath) {
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