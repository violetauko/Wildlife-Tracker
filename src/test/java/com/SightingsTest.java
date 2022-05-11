package com;

import com.DatabaseRule;
import com.Sightings;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


class SightingsTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();


    @Test
    void Sightings_instantiatesCorrectly_true() {
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        assertEquals(true,testSighting instanceof Sightings);

    }
    @Test
    void getAnimal_id() {
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        assertEquals(1,testSighting.getAnimal_id());
    }
    @Test
    void getLocation() {
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        assertEquals("Zone A",testSighting.getLocation());
    }

    @Test
    void getRangerName() {
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        assertEquals("Ellah",testSighting.getRangerName());
    }
    @Test
    void equals_returnsTrueIfSightingPropertiesAreSame() {
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        Sightings testSighting2 = new Sightings(1,"Zone A","Ellah");
        assertTrue(testSighting.equals(testSighting2));
    }
    @Test
    void save_insertObjectIntoDatabase_Sightings() {
        Sightings testSighting = Sightings.all().get(0);
        assertTrue(testSighting.id==Sightings.find(testSighting.id).id);
    }

    @Test
    public void all_returnsAllInstancesOfSightings_true() {
        Sightings testSighting = Sightings.all().get(0);
        Sightings secondTestSighting = Sightings.all().get(1);
        assertEquals(true, Sightings.find(testSighting.id).equals(testSighting));
        assertEquals(true, Sightings.find(secondTestSighting.id).equals(secondTestSighting));
    }
    @Test
    public void find_returnsSightingsWithSameId() {
        Sightings secondTestSighting = Sightings.all().get(0);
        assertEquals(Sightings.find(secondTestSighting.getId()), secondTestSighting);
    }
    @Test
    void save_recordsTimeOfSightings(){
        Sightings testSighting = new Sightings(1,"Zone A","Ellah");
        testSighting.save();
        Timestamp savedSightingTime = Sightings.find(testSighting.getId()).getSitedAt();
        Timestamp rightNow =new Timestamp(new Date().getTime());
        assertEquals(DateFormat.getDateTimeInstance().format(rightNow), DateFormat.getDateTimeInstance().format(savedSightingTime));

    }

}