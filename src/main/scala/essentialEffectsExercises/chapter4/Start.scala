package essentialEffectsExercises.chapter4

import cats.effect.{ExitCode, IO, IOApp}
import lib.Debug.*

/**
 * Start execution of the source suspended in the IO context
 */

object Start extends IOApp {

  def run(args: List[String]): IO[ExitCode] = for {
    _ <- task.start
    _ <- IO("task was started").debug
  } yield ExitCode.Success

  val task: IO[String] = IO("task").debug
}

/**
there is also background, to achieve a similar result with Resources.
*/
