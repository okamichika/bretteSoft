package mediathequelogic;

import java.time.LocalDateTime;

public interface Document {
    int getNumero();
    boolean isReserved();
    boolean isBorrowed();
    void reserveFor(Abonne subscriber, LocalDateTime reservationEndTime) throws ReservationException;
    void borrowBy(Abonne subscriber) throws BorrowException, EmpruntException;
    void returnDocument();
    Abonne getSubscriber();
    LocalDateTime getReservationEndTime();
}
