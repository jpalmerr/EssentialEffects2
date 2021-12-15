import cats.effect.IO
import cats.syntax.all.*

val ohNoes = IO.raiseError[Int](new RuntimeException("oh noes!"))


val handledWith: IO[Int] = ohNoes.handleErrorWith(_ => IO(12))
val handledWithError: IO[Int] = ohNoes.handleErrorWith(t => IO.raiseError(new Exception(t)))
val handled: IO[Int] = ohNoes.handleError(_ => 12)


val transformed = ohNoes.adaptError(t => new Exception(t)) // requires throwable

/*
ohNoes
.map(i => Right(i): Either[Throwable, Int])
.handleError(t => Left(t))
*/
val attempted: IO[Either[Throwable, Int]] = ohNoes.attempt

// comment: Something I often see in real world...
ohNoes.attempt.flatMap {
  case Left(e) => ???
  case Right(s) => ???
}


