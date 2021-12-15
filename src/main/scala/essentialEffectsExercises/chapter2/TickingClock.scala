package essentialEffectsExercises.chapter2

import cats.effect.*
import scala.concurrent.duration.*
import cats.implicits.*

object TickingClock extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    tickingClock.as(ExitCode.Success)

  val tickingClock: IO[Unit] =
    for {
      _ <- IO(println(System.currentTimeMillis))
      _ <- IO.sleep(1.second)
      _ <- tickingClock
    } yield ()
}
