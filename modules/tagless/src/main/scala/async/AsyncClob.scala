// Copyright (c) 2013-2018 Rob Norris and Contributors
// This software is licensed under the MIT License (MIT).
// For more information see LICENSE or https://opensource.org/licenses/MIT

package doobie.tagless.async

import doobie.tagless.RTS
import doobie.tagless.jdbc._
import org.slf4j.Logger
import java.io.InputStream
import java.io.OutputStream
import java.io.Reader
import java.io.Writer
import java.lang.String
import java.sql.Clob

/**
 * Implementation of `JdbcClob` that wraps a `java.sql.Clob` and lifts its operations
 * into blocking operations on `RTS[F]`, logged at `TRACE` level on `log`.
 */
@SuppressWarnings(Array("org.wartremover.warts.Overloading"))
class AsyncClob[F[_]](val value: Clob, rts: RTS[F], log: Logger) extends JdbcClob[F] {

  val id: String =
    s"${System.identityHashCode(value).toHexString.padTo(8, ' ')} Clob".padTo(28, ' ')

  val free: F[Unit] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id free()")
      value.free()
    }

  val getAsciiStream: F[InputStream] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id getAsciiStream()")
      value.getAsciiStream()
    }

  val getCharacterStream: F[Reader] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id getCharacterStream()")
      value.getCharacterStream()
    }

  def getCharacterStream(a: Long, b: Long): F[Reader] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id getCharacterStream($a, $b)")
      value.getCharacterStream(a, b)
    }

  def getSubString(a: Long, b: Int): F[String] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id getSubString($a, $b)")
      value.getSubString(a, b)
    }

  val length: F[Long] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id length()")
      value.length()
    }

  def position(a: Clob, b: Long): F[Long] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id position($a, $b)")
      value.position(a, b)
    }

  def position(a: String, b: Long): F[Long] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id position($a, $b)")
      value.position(a, b)
    }

  def setAsciiStream(a: Long): F[OutputStream] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id setAsciiStream($a)")
      value.setAsciiStream(a)
    }

  def setCharacterStream(a: Long): F[Writer] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id setCharacterStream($a)")
      value.setCharacterStream(a)
    }

  def setString(a: Long, b: String): F[Int] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id setString($a, $b)")
      value.setString(a, b)
    }

  def setString(a: Long, b: String, c: Int, d: Int): F[Int] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id setString($a, $b, $c, $d)")
      value.setString(a, b, c, d)
    }

  def truncate(a: Long): F[Unit] =
    rts.newBlockingPrimitive {
      if (log.isTraceEnabled)
        log.trace(s"$id truncate($a)")
      value.truncate(a)
    }

}
