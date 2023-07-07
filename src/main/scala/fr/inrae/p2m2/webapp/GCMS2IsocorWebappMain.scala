package fr.inrae.p2m2.webapp


import fr.inrae.p2m2.converter.GCMSOutputFiles2IsocorInput
import fr.inrae.p2m2.format.GCMS
import fr.inrae.p2m2.parser.GCMSParser.{parseHeader, parseMSQuantitativeResults}
import org.scalajs.dom
import org.scalajs.dom.html.Input
import org.scalajs.dom.window.alert
import org.scalajs.dom.{Event, FileReader, HTMLInputElement, window}
import scalatags.JsDom
import scalatags.JsDom.all._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future.never.onComplete
import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.scalajs.js.URIUtils.encodeURIComponent
import scala.util.{Failure, Success}

object GCMS2IsocorWebappMain {


  def readFileAsText (file : dom.File) (implicit ec: ExecutionContext) : Future[String] = {
    val p = Promise[String]()
      val fr = new FileReader()

    fr.onload = _ => {
        p.success(fr.result.toString)
    }

      fr.onerror = _ => {
        p.failure(new Exception())
      }

    fr.readAsText(file)
    p.future
  }

def main(args: Array[String]): Unit = {
/*
  window.onerror = (messageOrEvent : Event, source : String, lineno : Int, colno: Int, error : Any) => {
    val obj = dom
      .document
      .getElementById("my_console")
    alert(error.toString)
    //obj.innerText = obj.innerText  + errorMsg +"\r\n"
  }*/

  val inputTag: JsDom.TypedTag[Input] = input(
    id := "inputFiles",
    `type` := "file",
    multiple := "multiple",
    onchange := {
      (ev : dom.InputEvent) =>
        val files = ev.currentTarget.asInstanceOf[HTMLInputElement].files

        if (files.nonEmpty) {

          val resolution : Int = 2000
          val separator: String = "_"
          val verbose: Boolean = false
          val debug: Boolean = false
          val pro = GCMSOutputFiles2IsocorInput(resolution, separator)

          dom
            .document
            .getElementById("log").innerText=""

          val lFutures = Future.sequence(files.map(f => readFileAsText(f) ))

          lFutures.onComplete {
                  case Success(reportsGcmsInTextFormat : List[String]) =>
                    //println(reportsGcmsInTextFormat)
                    val header="sample\tmetabolite\tderivative\tisotopologue\tarea\tresolution\n"

                    val listGCMS : List [String] = reportsGcmsInTextFormat.flatMap {
                      case fileContent =>
                        val textByLine : List[String] = fileContent.split("\n")
                          .toList
                          .map(_.trim)
                          .filter(_.nonEmpty)
                          .filter(!_.startsWith("#"))
                        try {
                          Some(pro.transform(GCMS(
                            origin = "test",
                            header = parseHeader(textByLine),
                            msQuantitativeResults = parseMSQuantitativeResults(textByLine)
                          )))
                        } catch {
                          case e =>
                            alert(e.getMessage())
                            System.err.println(e)
                            None
                        }
                    }.flatten
                    a(
                      "IsoCor file", href := "data:text/tsv;name=isocor_gcms.tsv;charset=UTF-8,"
                        + encodeURIComponent(header+listGCMS.mkString("\n"))).render.click()

                  case Failure(e) =>
                    System.err.println("failure :"+e.getMessage)
                }
        }

    }
  )
  dom
    .document
    .getElementById("inputFilesDiv")
    .append(inputTag.render)
}
}