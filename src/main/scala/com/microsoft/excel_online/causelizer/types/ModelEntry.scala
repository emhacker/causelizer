package com.microsoft.excel_online.causelizer.types

/**
 * @description Represent an entry in the feature engineering data frame.
 * Mapping a vector of operations into a resulting event.
 */
case class ModelEntry (
  operations: Array[Int],
  eventId: Int)
