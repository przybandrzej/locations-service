package tech.przybysz.pms.locationsservice.service.exception;

public class DependencyEntityNotFoundException extends RuntimeException {

  private String entityName;
  private String dependencyName;

  public DependencyEntityNotFoundException(String entityName, String dependencyName) {
    super();
  }

  public String getEntityName() {
    return entityName;
  }

  public void setEntityName(String entityName) {
    this.entityName = entityName;
  }

  public String getDependencyName() {
    return dependencyName;
  }

  public void setDependencyName(String dependencyName) {
    this.dependencyName = dependencyName;
  }
}
