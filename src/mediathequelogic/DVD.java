package mediathequelogic;

import java.time.LocalDateTime;

public class DVD implements Document {
    private int numero;
    private boolean isAdult;
    private boolean isReserved;
    private boolean isBorrowed;
    private Abonne subscriber;
    private LocalDateTime reservationEndTime;

    public DVD(int numero, boolean isAdult) {
        this.numero = numero;
        this.isAdult = isAdult;
        this.isReserved = false;
        this.isBorrowed = false;
        this.subscriber = null;
        this.reservationEndTime = null;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public boolean isBorrowed() {
        return isBorrowed;
    }

    @Override
    public void reserveFor(Abonne subscriber, LocalDateTime reservationEndTime) throws ReservationException {
        if (isBorrowed || (isReserved && LocalDateTime.now().isBefore(this.reservationEndTime))) {
            throw new ReservationException("This DVD is not available for reservation.");
        }
        this.subscriber = subscriber;
        this.reservationEndTime = reservationEndTime;
        this.isReserved = true;
    }

    @Override
    public void borrowBy(Abonne subscriber) throws EmpruntException {
        if (isReserved && LocalDateTime.now().isBefore(this.reservationEndTime)) {
            throw new EmpruntException("This DVD is reserved by another subscriber.");
        }
        if (isAdult && subscriber.getAge() < 16) {
            throw new EmpruntException("This DVD is for adult viewers only.");
        }
        if (isBorrowed) {
            throw new EmpruntException("This DVD is already borrowed.");
        }
        this.subscriber = subscriber;
        this.isReserved = false;
        this.isBorrowed = true;
    }

    @Override
    public void returnDocument() {
        this.subscriber = null;
        this.reservationEndTime = null;
        this.isReserved = false;
        this.isBorrowed = false;
    }

    @Override
    public Abonne getSubscriber() {
        return subscriber;
    }

    @Override
    public LocalDateTime getReservationEndTime() {
        return reservationEndTime;
    }
}
