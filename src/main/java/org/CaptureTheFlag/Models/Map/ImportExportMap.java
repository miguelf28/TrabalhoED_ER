package org.CaptureTheFlag.Models.Map;

import org.CaptureTheFlag.Models.Location.Location;
import org.CaptureTheFlag.Models.Path.Path;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ImportExportMap {

    /**
     * Imports a map from a JSON file and generates the map accordingly.
     *
     * @param nameFile The name of the JSON file containing the map information.
     */
    public void importMapAndGenerateMap(String nameFile) {

//        JSONParser parser = new JSONParser();
//
//        try (FileReader reader = new FileReader(nameFile)) {
//            JSONObject mapObject = (JSONObject) parser.parse(reader);
//
//            boolean bidirectionalPaths = (boolean) mapObject.get("Type of path");
//
//            JSONArray locationsArray = (JSONArray) mapObject.get("locations");
//            for (Object locationObj : locationsArray) {
//                JSONObject locationJson = (JSONObject) locationObj;
//                int id = Math.toIntExact((Long) locationJson.get("id"));
//                String name = (String) locationJson.get("name");
//                Location location = new Location(id, name);
//                addVertex((T) location);
//            }
//
//            JSONArray pathsArray = (JSONArray) mapObject.get("paths");
//            for (Object pathObj : pathsArray) {
//                JSONObject pathJson = (JSONObject) pathObj;
//                int distance = Math.toIntExact((Long) pathJson.get("distance"));
//                String originName = (String) pathJson.get("origin");
//                String destinationName = (String) pathJson.get("destination");
//
//                Location origin = getLocationByName(originName);
//                Location destination = getLocationByName(destinationName);
//
//                if (origin != null && destination != null) {
//                    addEdge(getIndexOfLocation(origin), getIndexOfLocation(destination), distance);
//                    Path path = new Path(origin, destination, distance);
//                    paths.add(path);
//
//                    if (bidirectionalPaths) {
//                        addEdge(getIndexOfLocation(destination), getIndexOfLocation(origin), distance);
//                        Path inverse = new Path(destination, origin, distance);
//                        pathsInverse.add(inverse);
//                    }
//                }
//            }
//        } catch (IOException | ParseException | IndexOutOfBoundsException e) {
//            e.printStackTrace();
//        }


    }

    /**
     * Exports the map data to a JSON file.
     *
     * @param map           The map to export.
     * @param directionPath Whether to export bidirectional paths.
     * @param density       The density of the paths.
     * @param outputPath    The path to the output JSON file.
     */
    public static void exportMapToJson(Map<Location> map, boolean directionPath, int density, String outputPath) {
//        JSONArray locationsArray = new JSONArray();
//        JSONArray pathsArray = new JSONArray();
//
//        for (Location location : map.getLocations()) {
//            JSONObject locationObject = new JSONObject();
//            locationObject.put("id", location.getId());
//            locationObject.put("name", location.getName());
//            locationsArray.add(locationObject);
//        }
//
//        if (directionPath) {
//            for (Path path : map.getPaths()) {
//                JSONObject pathObject = new JSONObject();
//                pathObject.put("origin", path.getOrigin().getName());
//                pathObject.put("destination", path.getDestination().getName());
//                pathObject.put("distance", path.getDistance());
//                pathsArray.add(pathObject);
//            }
//
//            for (Path path : map.getPathsInverse()) {
//                JSONObject pathObject = new JSONObject();
//                pathObject.put("origin", path.getOrigin().getName());
//                pathObject.put("destination", path.getDestination().getName());
//                pathObject.put("distance", path.getDistance());
//                pathsArray.add(pathObject);
//            }
//        } else {
//            for (Path path : map.getPaths()) {
//                JSONObject pathObject = new JSONObject();
//                pathObject.put("origin", path.getOrigin().getName());
//                pathObject.put("destination", path.getDestination().getName());
//                pathObject.put("distance", path.getDistance());
//                pathsArray.add(pathObject);
//            }
//        }
//
//        JSONObject mapObject = new JSONObject();
//        mapObject.put("locations", locationsArray);
//        mapObject.put("paths", pathsArray);
//        mapObject.put("Type of path", directionPath);
//
//        try (FileWriter fileWriter = new FileWriter(outputPath)) {
//            fileWriter.write(mapObject.toJSONString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    }
}