package br.com.springboot.tabelafipe.convert;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class InstantConvert {

    public String convertInstantToString(final Instant dateInstant) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(Date.from(dateInstant));
    }
}
