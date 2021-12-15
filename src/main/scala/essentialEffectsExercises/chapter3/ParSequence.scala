package essentialEffectsExercises.chapter3

/*
(par)sequence turns a nested structure “inside-out”
F[G[A]] => G[F[A]]
 */

import cats.effect.*
import cats.implicits.*
import lib.Debug.*

object ParSequence extends IOApp {
  def run(args: List[String]): IO[ExitCode] = {
    //    val x: IO[List[Int]] = tasks.parSequence
    tasks.parSequence.debug.as(ExitCode.Success)
  }

  val numTasks = 100
  val tasks: List[IO[Int]] = List.tabulate(numTasks)(task)
  def task(id: Int): IO[Int] = IO(id).debug
}

