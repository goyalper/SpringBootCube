package com.cubemasterpro.model;

/**
 * Represents the six colors of a standard Rubik's cube.
 */
public enum CubeColor {
    WHITE("white", "#FFFFFF"),
    YELLOW("yellow", "#FFFF00"),
    RED("red", "#FF0000"),
    ORANGE("orange", "#FFA500"),
    BLUE("blue", "#0000FF"),
    GREEN("green", "#00FF00");

    private final String name;
    private final String hexCode;

    CubeColor(String name, String hexCode) {
        this.name = name;
        this.hexCode = hexCode;
    }

    public String getName() {
        return name;
    }

    public String getHexCode() {
        return hexCode;
    }

    public static CubeColor fromName(String name) {
        for (CubeColor c : values()) {
            if (c.name.equalsIgnoreCase(name)) return c;
        }
        return null;
    }
}
