package ch.th3dennis.tourmanagerdennismiceli;


import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import ch.th3dennis.tourmanagerdennismiceli.exception.WrongDateFormatException;

class CreateTourActivityTest {


    @Test
    void getDateFromString_expectCorrectDate() throws WrongDateFormatException {
        String stringDate = "02.09.2020";
        CreateTourActivity createTourActivity = new CreateTourActivity();

        Assert.assertEquals(new Date(2020, 8, 2), createTourActivity.getDateFromString(stringDate));
    }




}