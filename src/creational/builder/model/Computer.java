package creational.builder.model;

/**
 * Product Class: Computer
 * Represents a complex object with many mandatory and optional attributes.
 */
public class Computer {
    // Required attributes
    private final String cpu;
    private final int ramGB;
    private final int storageGB;

    // Optional attributes
    private final String gpu;
    private final boolean hasLiquidCooling;
    private final boolean hasRGB;
    private final boolean hasBluetooth;
    private final String operatingSystem;

    /**
     * Package-private constructor: forces creation via ComputerBuilder.
     */
    Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ramGB = builder.ramGB;
        this.storageGB = builder.storageGB;
        this.gpu = builder.gpu;
        this.hasLiquidCooling = builder.hasLiquidCooling;
        this.hasRGB = builder.hasRGB;
        this.hasBluetooth = builder.hasBluetooth;
        this.operatingSystem = builder.operatingSystem;
    }

    public String getCpu() { return cpu; }
    public int getRamGB() { return ramGB; }
    public int getStorageGB() { return storageGB; }
    public String getGpu() { return gpu; }
    public boolean hasLiquidCooling() { return hasLiquidCooling; }
    public boolean hasRGB() { return hasRGB; }
    public boolean hasBluetooth() { return hasBluetooth; }
    public String getOperatingSystem() { return operatingSystem; }

    @Override
    public String toString() {
        return "Computer Specifications:\n" +
                "  - CPU: " + cpu + "\n" +
                "  - RAM: " + ramGB + " GB\n" +
                "  - Storage: " + storageGB + " GB SSD\n" +
                "  - GPU: " + (gpu != null ? gpu : "Integrated Graphics") + "\n" +
                "  - Liquid Cooling: " + (hasLiquidCooling ? "Yes" : "No") + "\n" +
                "  - RGB Lighting: " + (hasRGB ? "Yes" : "No") + "\n" +
                "  - Bluetooth: " + (hasBluetooth ? "Yes" : "No") + "\n" +
                "  - Operating System: " + (operatingSystem != null ? operatingSystem : "None (No OS)") + "\n";
    }

    /**
     * Static inner Builder class for fluent creation.
     */
    public static class ComputerBuilder {
        // Required parameters
        private final String cpu;
        private final int ramGB;
        private final int storageGB;

        // Optional parameters (default values)
        private String gpu = null;
        private boolean hasLiquidCooling = false;
        private boolean hasRGB = false;
        private boolean hasBluetooth = false;
        private String operatingSystem = "FreeDOS";

        /**
         * Constructor enforces all required parameters.
         */
        public ComputerBuilder(String cpu, int ramGB, int storageGB) {
            if (cpu == null || cpu.trim().isEmpty()) {
                throw new IllegalArgumentException("CPU is required");
            }
            if (ramGB <= 0) {
                throw new IllegalArgumentException("RAM must be greater than 0 GB");
            }
            if (storageGB <= 0) {
                throw new IllegalArgumentException("Storage must be greater than 0 GB");
            }
            this.cpu = cpu;
            this.ramGB = ramGB;
            this.storageGB = storageGB;
        }

        // Fluent setter methods returning 'this' for method chaining

        public ComputerBuilder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public ComputerBuilder setLiquidCooling(boolean hasLiquidCooling) {
            this.hasLiquidCooling = hasLiquidCooling;
            return this;
        }

        public ComputerBuilder setRGB(boolean hasRGB) {
            this.hasRGB = hasRGB;
            return this;
        }

        public ComputerBuilder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public ComputerBuilder setOperatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        /**
         * Terminal build method that constructs the immutable Computer instance.
         */
        public Computer build() {
            // Optional: validate combinations (e.g. liquid cooling only if high-end GPU)
            return new Computer(this);
        }
    }
}
