package io.catbird.finagle

import cats.Eq
import cats.data.Kleisli
import cats.instances.int._
import cats.laws.discipline._
import cats.laws.discipline.eq._
import com.twitter.conversions.time._
import com.twitter.finagle.Service
import com.twitter.util.Future
import io.catbird.util._
import org.scalatest.FunSuite
import org.typelevel.discipline.scalatest.Discipline
import scala.util.{ Failure, Success }

class ServiceSuite extends FunSuite with Discipline with
  ServiceInstances with ArbitraryInstances with EqInstances {
  implicit val eq: Eq[Service[Int, Int]] = serviceEq(1.second)

  checkAll("Service", CategoryTests[Service].compose[Int, Int, Int, Int])
  checkAll("Service", CategoryTests[Service].category[Int, Int, Int, Int])
  checkAll("Service", ProfunctorTests[Service].profunctor[Int, Int, Int, Int, Int, Int])
}
