package main.java.com.bestpath.models;

public class Restaurant {
    private String name;
    private Location location;
    private double prepTime; // in minutes

    public Restaurant(String name, Location location, double prepTime) {
        this.name = name;
        this.location = location;
        this.prepTime = prepTime;
    }

    public Location getLocation() {
        return location;
    }

    public double getPrepTime() {
        return prepTime;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override public String toString() {
        final StringBuilder sb = new StringBuilder("Restaurant{");
        sb.append("name='").append(name).append('\'');
        sb.append(", location=").append(location);
        sb.append(", prepTime=").append(prepTime);
        sb.append('}');
        return sb.toString();
    }
}
