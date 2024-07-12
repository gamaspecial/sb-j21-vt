// 以下、kotlinに変換
package vt.controller;

import org.springframework.web.bind.annotation.RestController

@RestController
class VirtualThreadsController(
    private val virtualThreadsService: VirtualThreadsService
) {

    @GetMapping("/platform/{count}")
    fun doPlatform(@PathVariable count: Int): String {
        println("start platform. count=$count")
        virtualThreadsService.execPlatformThread(count)
        return "platform"
    }

    @GetMapping("/virtual/{count}")
    fun doVirtual(@PathVariable count: Int): String {
        println("start virtual. count=$count")
        virtualThreadsService.execVirtualThread(count)
        return "virtual"
    }

    @GetMapping("sleep")
    fun sleep(): String {
        virtualThreadsService.sleep()
        return "sleep"
    }

    @GetMapping("async")
    fun async(): String {
        virtualThreadsService.sleepAsync()
        return "async"
    }

    @GetMapping("platform/task/{count}")
    fun task(@PathVariable count: Int): String {
        virtualThreadsService.task(count)
        return "task"
    }
}
