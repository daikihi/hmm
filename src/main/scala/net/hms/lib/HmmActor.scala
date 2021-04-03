package net.hms.lib

import akka.actor.{Actor, ActorLogging, ActorRef}

trait HmmActor extends Actor with ActorLogging {
  private var sent: Long = 0L
  private var isMainActor = false
  private var parentActors: Seq[ActorRef] = Seq.empty[ActorRef]

  def isMain(): Unit = isMainActor = true
  def setParentActor(ref: ActorRef): Unit = parentActors = ref +: parentActors

  def send(n: Long = 1L): Unit = sent = sent + n

  def receives(n: Long = 1L): Unit = {
    sent = sent - n
    // child actor alwasy should send DoneMessage to parent actor
    //  when a child actor finish a behavior for a received message
    if (sent == 0 && !isMainActor) {
      log.info("reply DoneMessage")
      parentActors.foreach { parent =>
        parent ! DoneMessage()
      }

    } else if (sent == 0 && isMainActor) {
      log.info("terminate .... I am main actor")
      context.system.terminate()
    }
  }
}
