package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//bunlarla PojoJsonPlace de yaptıgımıız getter ve setter, toString, paramtreli constructor, ve default constructorlar
//otomatik oluşturuluyor.

public class PojoHerSonDataClass {

    /*
  {
     "bookingid":24,
     "booking":{
         "firstname":"",
         "lastname":"",
         "totalprice": int,
         "depositpaid":boolean,
         "bookingdates":{
             "checkin":"",
             "checkout":""
                         }
         ,
         "additionalneeds":""
           }
    }
 */

    private Object bookingid;
    private PojoHerBookingClass booking;
}
