package creational.builder;

import creational.builder.director.ComputerDirector;
import creational.builder.model.Computer;
import creational.builder.query.SqlQuery;

/**
 * Demo runner for Builder Pattern.
 */
public class BuilderDemo {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("          BUILDER DESIGN PATTERN DEMO             ");
        System.out.println("==================================================\n");

        // Approach 1: Fluent custom build by client
        System.out.println("--- 1. Custom Build (Fluent API by Client) ---");
        Computer customDevPC = new Computer.ComputerBuilder("Apple M3 Max", 64, 1024)
                .setOperatingSystem("macOS Sonoma")
                .setBluetooth(true)
                .setRGB(false)
                .build();
        System.out.println(customDevPC);

        // Approach 2: Using Director for predefined recipes
        ComputerDirector director = new ComputerDirector();

        System.out.println("--- 2. Predefined Office PC (via Director) ---");
        Computer officePC = director.buildOfficePC();
        System.out.println(officePC);

        System.out.println("--- 3. Predefined Gaming PC (via Director) ---");
        Computer gamingPC = director.buildGamingPC();
        System.out.println(gamingPC);

        System.out.println("--- 4. Predefined Server (via Director) ---");
        Computer server = director.buildServer();
        System.out.println(server);

        // Approach 3: Practical Real-World Query Builder
        System.out.println("--- 5. Practical Example: SQL Query Builder ---");
        SqlQuery userQuery = new SqlQuery.Builder("users")
                .select("id", "username", "email", "status")
                .where("status = 'ACTIVE'")
                .where("created_at >= '2024-01-01'")
                .orderBy("username", "ASC")
                .limit(20)
                .offset(40)
                .build();

        System.out.println("Generated SQL Query:");
        System.out.println("  " + userQuery.toSql());
    }
}
