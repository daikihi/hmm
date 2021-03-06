package net.hms.lib

trait SystemMessage extends Message

case class StartExecuteBatchMessage(appMessage: ApplicationMessage) extends SystemMessage
case class DoneMessage() extends SystemMessage