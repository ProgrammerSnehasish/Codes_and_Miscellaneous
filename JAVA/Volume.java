import java.util.Scanner;

class Volume extends Main{
    // Method to calculate the volume of a sphere
    public static double getVolume(float radius) {
        double volume_of_sphere = (4.0 / 3.0) * Math.PI * radius * radius * radius;
        return volume_of_sphere;
    }

    // Method to calculate the volume of a cuboid
    public static double getVolume(float length, float height, float breadth) {
        double volume_of_cuboid = length * height * breadth;
        return volume_of_cuboid;
    }

    // Method to calculate the volume of a cone
    public static double getVolume(float radius, float height) {
        double volume_of_cone = (1.0 / 3.0) * Math.PI * radius * radius * height;
        return volume_of_cone;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Calculate and display the volume of a sphere
        System.out.print("Enter radius for sphere: ");
        float radius_sphere = scanner.nextFloat();
        double volume_sphere = Volume.getVolume(radius_sphere);
        System.out.println("The volume of the sphere is: " + volume_sphere);

        // Calculate and display the volume of a cone
        System.out.print("Enter radius for cone: ");
        float radius_cone = scanner.nextFloat();
        System.out.print("Enter height for cone: ");
        float height_cone = scanner.nextFloat();
        double volume_cone = Volume.getVolume(radius_cone, height_cone);
        System.out.println("The volume of the cone is: " + volume_cone);

        // Calculate and display the volume of a cuboid
        System.out.print("Enter length for cuboid: ");
        float length_cuboid = scanner.nextFloat();
        System.out.print("Enter height for cuboid: ");
        float height_cuboid = scanner.nextFloat();
        System.out.print("Enter breadth for cuboid: ");
        float breadth_cuboid = scanner.nextFloat();
        double volume_cuboid = Volume.getVolume(length_cuboid, height_cuboid, breadth_cuboid);
        System.out.println("The volume of the cuboid is: " + volume_cuboid);
    }
}

