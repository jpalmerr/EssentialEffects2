package essentialEffectsExercises.chapter4

// What if instead we were only interested in the effect that completed first,

import cats.effect.*
import cats.effect.implicits.*
import cats.implicits.*
import scala.concurrent.duration.*
import lib.Debug.*


object Timeout extends IOApp {

  val task: IO[Unit] = Helper.annotatedSleep(" task", 4000.millis)
  val timeout: IO[Unit] = Helper.annotatedSleep("timeout", 8000.millis)

  def run(args: List[String]): IO[ExitCode] =
    for {
      done <- IO.race(task, timeout) // returns value of first to finish
      _ <- done match {
        case Left(_) => IO(" task: won").debug
        case Right(_) => IO("timeout: won").debug
      }
    } yield ExitCode.Success
}

/*
you receive the “winning” value along with the Fiber of the race “loser”,
so you can decide what you want to do with it
*/

object Racing extends IOApp {

  val task1: IO[Unit] = Helper.annotatedSleep(" task1", 4000.millis)
  val task2: IO[Unit] = Helper.annotatedSleep("task2", 8000.millis)

  def run(args:List[String]): IO[ExitCode] = {
    for {
      done <- IO.racePair(task1, task2)
      _ <- done match {
        case Left((a, fb)) =>
          val task1Wins = (IO.pure(a), fb.join)
          println(s"task2: ${task1Wins._2}")
          task1Wins._1.debug
        case Right((fa, b)) => (fa.join, IO.pure(b))._2.debug
      }
    } yield ExitCode.Success
  }
}

object Helper:
  def annotatedSleep(name: String, duration: FiniteDuration): IO[Unit] =
    (
      IO(s"$name: starting").debug
        *> IO.sleep(duration)
        *> IO(s"$name: done").debug
      ).onCancel(IO(s"$name: cancelled").debug.void).void