public class Sightings {

    private int animal_id;
    private String location;
    private String rangerName;

    public Sightings(int animal_id, String location, String rangerName) {
        this.animal_id = animal_id;
        this.location = location;
        this.rangerName = rangerName;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }
}
