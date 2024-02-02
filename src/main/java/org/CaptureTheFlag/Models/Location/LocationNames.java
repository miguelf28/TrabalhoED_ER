package org.CaptureTheFlag.Models.Location;

/**
 * The {@code LocationNames} enum represents predefined names for locations on the game map in a Capture The Flag game.
 * These names can be used to assign human-readable labels to locations.
 * <p>
 * Example usage:
 * <pre>
 * Location location = new Location(1, LocationNames.VALE.toString());
 * </pre>
 * </p>
 *
 * @author 8190357 - Marcelo Barbosa
 * @author 8180325 - Miguel Fonseca
 */
public enum LocationNames {
    VALE, PICO, CIDADE, LAGO, ILHA, FORTE, RIO, TORRE, BOSQUE, GRUTA,
    CAVERNA, PLANICIE, MONTANHA, ALDEIA, PRAIA, PORTAO, RUINAS,
    CUME, PONTE, TRILHA, ABISMO, PANTANO, OASIS, DESERTO, FENDA, ALTAR,
    SERRA, PATIO, SELVA, COLINA
}
