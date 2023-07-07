package fr.inrae.p2m2.format
import upickle.default._

object GCMS {
  implicit val rw: ReadWriter[GCMS] = macroRW
  object HeaderFileField extends Enumeration {
    implicit val rw: ReadWriter[HeaderFileField] = readwriter[Int].bimap[HeaderFileField](x => x.id, HeaderFileField(_))
    type HeaderFileField = Value
    val Data_File_Name, Output_Date, Output_Time = Value
  }

  object HeaderField extends Enumeration {
    implicit val rw: ReadWriter[HeaderField] = readwriter[Int].bimap[HeaderField](x => x.id, HeaderField(_))
    type HeaderField = Value
    val `ID#`,Name, `Type`, `ISTD Group#`, Mass, `Ret.Time`, `Start Time`, `End Time`,
    `A/H`, Area, Height, `Conc.`, Mode, `Peak#`, `Std.Ret.Time`, `Calibration Curve`, `3rd`, `2nd`, `1st`,
    `Constant`, `Ref.Ion Area`, `Ref.Ion Height`, `Ref.Ion Set Ratio`, `Ref.Ion Ratio`, Recovery, SI,
    `Ref.Ion1 m/z`, `Ref.Ion1 Area`, `Ref.Ion1 Height`, `Ref.Ion1 Set Ratio`, `Ref.Ion1 Ratio`,
    `Ref.Ion2 m/z`, `Ref.Ion2 Area`, `Ref.Ion2 Height`, `Ref.Ion2 Set Ratio`, `Ref.Ion2 Ratio`, `Ref.Ion3 m/z`,
    `Ref.Ion3 Area`, `Ref.Ion3 Height`, `Ref.Ion3 Set Ratio`, `Ref.Ion3 Ratio`, `Ref.Ion4 m/z`, `Ref.Ion4 Area`,
    `Ref.Ion4 Height`, `Ref.Ion4 Set Ratio`, `Ref.Ion4 Ratio`, `Ref.Ion5 m/z`, `Ref.Ion5 Area`, `Ref.Ion5 Height`,
    `Ref.Ion5 Set Ratio`, `Ref.Ion5 Ratio`,	`Ret. Index`,	`S/N`, `Unit`, Description, Threshold = Value
  }
}

/**
 * Categories management :
 * [Header] map key, value
 * [MS_Quantitative_Results] Array of results
 */

case class GCMS (
                  origin : String,
                  header : Map[GCMS.HeaderFileField.HeaderFileField,String] =
                  Map[GCMS.HeaderFileField.HeaderFileField,String](),
                  msQuantitativeResults : Seq[Map[GCMS.HeaderField.HeaderField, String]] = Seq()
                ) {

}
