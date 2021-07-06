package sda.projects.travelagencybackend.controller;

public class ControllerConflictException extends RuntimeException {
   public ControllerConflictException(final String message) {
      super(message);
   }
}
