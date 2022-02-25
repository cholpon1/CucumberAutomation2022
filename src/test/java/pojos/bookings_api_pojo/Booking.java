package pojos.bookings_api_pojo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {

    private String firstname;
    private String lastname;
    private Integer totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

}
