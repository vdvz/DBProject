package controller.table;

public interface Request {

  void query();

  void checkCorrectness() throws Exception;
}
