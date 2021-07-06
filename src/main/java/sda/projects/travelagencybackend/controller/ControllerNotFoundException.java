package sda.projects.travelagencybackend.controller;

public class ControllerNotFoundException extends RuntimeException {
   public ControllerNotFoundException(final String message) {
      super(message);
   }
}
