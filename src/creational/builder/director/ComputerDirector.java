package creational.builder.director;

import creational.builder.model.Computer;

/**
 * Director Class (Optional in GoF Builder Pattern).
 * Defines order and recipes for constructing common configurations.
 */
public class ComputerDirector {

    /**
     * Builds a budget office PC configuration.
     */
    public Computer buildOfficePC() {
        return new Computer.ComputerBuilder("Intel Core i3-13100", 8, 256)
                .setOperatingSystem("Windows 11 Home")
                .setBluetooth(true)
                .build();
    }

    /**
     * Builds a high-end gaming PC configuration.
     */
    public Computer buildGamingPC() {
        return new Computer.ComputerBuilder("AMD Ryzen 7 7800X3D", 32, 2048)
                .setGpu("NVIDIA GeForce RTX 4080 16GB")
                .setLiquidCooling(true)
                .setRGB(true)
                .setBluetooth(true)
                .setOperatingSystem("Windows 11 Pro")
                .build();
    }

    /**
     * Builds a high-performance server configuration.
     */
    public Computer buildServer() {
        return new Computer.ComputerBuilder("AMD EPYC 9654 96-Core", 256, 8192)
                .setOperatingSystem("Ubuntu Server 24.04 LTS")
                .setLiquidCooling(true)
                .setRGB(false)
                .setBluetooth(false)
                .build();
    }
}
