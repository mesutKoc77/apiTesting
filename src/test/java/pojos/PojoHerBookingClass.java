package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PojoHerBookingClass {
    /*
        firstname= " ";
        lastname= " ";
        totalprice= " ";
        depositpaid= " ";
              bookingdates= DatesBooking;
        additionalneeds=" ";

     */
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private PojoHerDatesBookingClass bookingdates;
    private String additionalneeds;

}
