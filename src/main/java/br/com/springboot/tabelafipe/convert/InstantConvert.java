package br.com.springboot.tabelafipe.convert;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
public class InstantConvert {

    public String convertInstantToString(final Instant dateInstant) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(Date.from(dateInstant));
    }

    public Instant convertStringToInstant(final String dateString){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            return date.toInstant();
        }catch (ParseException pe){
            throw new IllegalArgumentException("Error during date parse "+ pe.getMessage());
        }
    }
}
