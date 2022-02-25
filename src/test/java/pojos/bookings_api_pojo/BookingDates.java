package pojos.bookings_api_pojo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingDates {

    private String checkin;
    private String checkout;

}
