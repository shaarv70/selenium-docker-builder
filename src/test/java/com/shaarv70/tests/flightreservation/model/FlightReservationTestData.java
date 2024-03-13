package com.shaarv70.tests.flightreservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FlightReservationTestData(String firstName,
                                        String lastName,
                                        String email,
                                        //@JsonProperty("password") : here we will give the same name as json data file
                                        //and in the below variable we can give our own name
                                        String password,
                                        String street,
                                        String city,
                                        String zip,
                                        String passengersCount,
                                        String expectedPrice)
{
    /*So record is another type of class which comes after jdk 17 so here in record we can skip declaration
    of data members, constructors, and get methods, we just need to write the class in the above way and all these
    things are included in that after that we just have to create an object of that class and pass the values to
    its constructor and with the reference we have to call those variables :
    FlightReservationTestData fp= new FlightReservationTestData(values to be passed);
    fp.firstname();
    fp.lastName();  //its nothing but same as fp.getFirstname()
    In this way we can use record*/
}
