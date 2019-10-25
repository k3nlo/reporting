package demo.reporting;

public class Query {
  private String description;
  private String sql;
  private String [] columns;

  public Query() {
  }

  public Query(String description, String sql, String[] columns) {
    this.description = description;
    this.sql = sql;
    this.columns = columns;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public String[] getColumns() {
    return columns;
  }

  public void setColumns(String[] columns) {
    this.columns = columns;
  }
}
