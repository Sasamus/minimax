package se.miun.dt015a.minimax;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import se.miun.dt015a.minimax.testutil.TestCase;
import se.miun.dt015a.minimax.testutil.TestParameters;

/**
 * @author Christoffer Fink
 */
@RunWith(value = Parameterized.class)
public class MiniMaxTest {

  private final TestCase<?,?> test;

  public MiniMaxTest(TestCase<?,?> test) {
    this.test = test;
  }

  @Test
  public void compareEvaluations() {
    test.compareEvaluations();
  }
  @Test
  public void compareExpansions() {
    test.compareExpansions();
  }
  @Test
  public void compareExpansionOrder() {
    test.compareExpansionOrder();
  }
  @Test
  public void compareAction() {
    test.compareAction();
  }

  @SuppressWarnings("rawtypes")
  @Parameters
  public static Collection<TestCase[]> parameters() {
    return TestParameters.getTests();
  }

  public static void main(String[] args) {
    JUnitCore.main("se.miun.dt015a.minimax.MiniMaxTest");
  }
}
