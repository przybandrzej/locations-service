package tech.przybysz.pms.locationsservice.service.exception;

public class EntityNotFoundException extends RuntimeException {

  private String entityName;

  public EntityNotFoundException(String entityName) {
    this.entityName = entityName;
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }
}
