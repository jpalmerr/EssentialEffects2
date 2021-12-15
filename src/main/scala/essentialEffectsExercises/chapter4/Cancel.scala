package essentialEffectsExercises.chapter4

import cats.effect.*
import cats.effect.implicits.*
import lib.Debug.*

/**
 * Canceling a Fiber is itself an effect. It produces a Unit value once the effect is canceled.
 *
 * Invoking cancel more than once has the same effect as invoking it once — a canceled task will continue to be canceled.
 * However, if you join after you cancel, the join will never finish, because no result will ever be produced.
 */

object Cancel extends IOApp {

  def run(args: List[String]): IO[ExitCode] = for {
    fiber <- task.onCancel(IO("i was cancelled").debug.void).start
    _ <- IO("pre-cancel").debug
    _ <- fiber.cancel
    _ <- IO("canceled").debug
  } yield ExitCode.Success

  val task: IO[String] = IO("task").debug *> IO.never // never complete
}
