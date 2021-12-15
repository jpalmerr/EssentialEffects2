package essentialEffectsExercises.chapter3

import cats.effect.*
import cats.implicits.*
import lib.Debug.*

/**
 * It lets us combine multiple effects into one, in parallel, by specifying how to combine the outputs of the effects
 */


object ParMapN extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    par.as(ExitCode.Success)

  val hello = IO("hello").debug
  val world = IO("world").debug

  val par = (hello, world)
    .parMapN((h, w) => s"$h $w")
    .debug
}

/**
 * Notice different threads are used
 * The execution order of parallel tasks is non-deterministic
 */

/**
e.g.

[io-compute-9] hello
[io-compute-1] world
[io-compute-8] hello world
*/
