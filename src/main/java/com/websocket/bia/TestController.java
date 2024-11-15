package com.websocket.bia;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.bia.webworker.WebWorkerService;

@RestController
public class TestController {

    private final WebWorkerService webWorkerService;

    public TestController( WebWorkerService webWorkerService) {
        this.webWorkerService = webWorkerService;
    }

    @GetMapping("/startWebWorker")
    public String startWebWorker() {
        webWorkerService.runInBackground(() -> {
            System.out.println("Tarefa em segundo plano executada!");
        });
        return "WebWorker iniciado!";
    }
}
