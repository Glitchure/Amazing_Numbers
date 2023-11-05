// update the class
class BadRequestException extends ClassNotFoundException {
    public BadRequestException(String message) {
        super(message);
    }
}