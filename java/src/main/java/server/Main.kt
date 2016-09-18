package server

/**
 * Created by Neville on 9/18/2016.
 */
fun main(args: Array<String>) {
    val server = InterchangeServer(12346)
    server.start()
    server.blockUntilShutdown()
}