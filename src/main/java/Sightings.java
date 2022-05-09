import org.sql2o.Connection;

import java.util.List;

public class Sightings {

    private int animal_id;
    private String location;
    private String rangerName;
    public int id;

    public Sightings(int animal_id, String location, String rangerName) {
        this.animal_id = animal_id;
        this.location = location;
        this.rangerName = rangerName;
    }

    public int getAnimal_id() {
        return animal_id;
    }
    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }
    @Override
    public boolean equals(Object testSighting2){
        if (!(testSighting2 instanceof Sightings)) {
            return false;
        }else {
            Sightings newSighting = (Sightings) testSighting2;
            return this.getLocation().equals(newSighting.getLocation())&&
                    this.getRangerName().equals(newSighting.getRangerName())&&
                    this.getAnimal_id()==newSighting.getAnimal_id();

        }
    }
    public int save() {
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animal_id, location, rangerName) VALUES (:animal_id, :location, :rangerName)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("animal_id", this.animal_id)
                    .addParameter("location",this.location)
                    .addParameter("rangerName",this.rangerName)
                    .executeUpdate()
                    .getKey();

        }
        return id;
    }
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }
    public static Sightings find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            Sightings testSighting = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
            return testSighting;
        }
    }
}
