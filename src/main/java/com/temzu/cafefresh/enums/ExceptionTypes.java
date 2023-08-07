package com.temzu.cafefresh.enums;

public enum ExceptionTypes {
  ID("ID"),

  CODE("CODE"),
  LOGIN("LOGIN"),
  NAME("NAME"),
  TITLE("TITLE"),
  UUID("UUID"),
  EMAIL("EMAIL"),
  PASSWORD("PASSWORD"),
  DATE("DATE");

  private final String type;

  ExceptionTypes(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
